package com.example.springbootdieta.controller;

import com.example.springbootdieta.dao.RoleRepository;

import com.example.springbootdieta.dao.UserRepository;
import com.example.springbootdieta.model.*;
import com.example.springbootdieta.configuration.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
//@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
//    Probablemente falte configurar algo para que no aparezca esto
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

//    @CrossOrigin(origins = "*", allowCredentials = "true")
    @PostMapping("/login")
//    @Validated cambie por que tenia validate en el otro pero este proyecto usa jakarta en vez de javax
    public ResponseEntity<?> userLogin(@Validated @RequestBody User user){
        System.out.println("AuthController -- userLogin");
        System.out.println("email ="+user.getEmail()+ "password =" + user.getPassword());
        Authentication authentication= null;
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        String token = jwtTokenUtil.generateJwtToken(authentication);
        System.out.println(token);
        SpringUser userBean = (SpringUser) authentication.getPrincipal();
        List<String> roles = userBean.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.toList());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserName(user.getUserName());
        authResponse.setRoles(roles);

        HttpCookie cookie = ResponseCookie.from("Token", token)
                .path("/")
                .httpOnly(true)
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(authResponse);

//        Falta revisar si setear la cookie funciona
//        Cookie cookie = new Cookie("Token", token);
//        Es false por que es localhost no https
//        cookie.setSecure(false);
//        cookie.setHttpOnly(true);
//        response.addCookie(cookie);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Set-Cookie", "Token="+token);//+"; HttpOnly"
//        AuthResponse authResponse = new AuthResponse();
//        authResponse.setUserName(user.getUserName());
//        authResponse.setRoles(roles);
//        return new ResponseEntity<AuthResponse>(authResponse, headers, HttpStatus.OK);
//        return ResponseEntity.ok(authResponse);
        }

    @PostMapping("/signup")
    public ResponseEntity<?> userSignup(@Validated @RequestBody SignupRequest signupRequest){
        System.out.println("Estoy dentro de signup");
        if(userRepository.existsByEmail(signupRequest.getEmail())){
            return ResponseEntity.badRequest().body("This email is already registered");
        }
        User user = new User();
        Set<Role> roles = new HashSet<>();
        user.setUserName(signupRequest.getUserName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(encoder.encode(signupRequest.getPassword()));
        user.setGender(signupRequest.isMale());
        String[] roleArr = {"user"};
        roles.add(roleRepository.findByRoleName(RoleList.ROLE_USER).get());
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok("User signed up successfully");

    }
    @PostMapping("/logout")
    public ResponseEntity userLogout(){
        HttpCookie cookie = ResponseCookie.from("Token", "")
                .path("/")
                .httpOnly(true)
                .build();
        return ResponseEntity.ok().build();
    }

}

