package com.example.springbootdieta.controller;

import com.example.springbootdieta.dao.UserRepository;
import com.example.springbootdieta.dao.WeightRepository;
import com.example.springbootdieta.model.User;
import com.example.springbootdieta.model.Weight;
import com.example.springbootdieta.model.Weight.WeightDto;
import com.example.springbootdieta.configuration.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
//@CrossOrigin(origins="*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    WeightRepository weightRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;



//    @GetMapping("/displayadmin")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public String displayToAdmin() {
//        return "Display only to admin";
//    }

//    @GetMapping("/allusers")
//    public String displayUsers() {
//
//        return "Display All Users, "+ SecurityContextHolder.getContext().getAuthentication().getName() +" is asking...";
//
//    }

    @GetMapping("/displayuser")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String displayToUser() {
        return "Display to both user and admin";
    }

    @GetMapping("/getweighthistory")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getWeightHistory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return ResponseEntity.ok()
                .body(weightRepository.findAllByUser_UserNameOrderByDateDesc(currentPrincipalName).stream()
                        .map(w->w.toDto())
                        .collect(Collectors.toList()));
    }
    @GetMapping("/getfirstweight")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getFirstWeight() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
//        return null;
        return ResponseEntity.ok()
                .body(weightRepository.findFirstByUser_UserNameOrderByDateDateAsc(currentPrincipalName));
    }

    @GetMapping("/getlastweight")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getLastWeight() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
//        return null;
        return ResponseEntity.ok()
                .body(weightRepository.findFirstByUser_UserNameOrderByDateDateDesc(currentPrincipalName));
    }
    @PostMapping("/postweight")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> saveNewWeight(@Validated @RequestBody WeightDto weightR) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.getUserByUserName(currentPrincipalName);
        Weight weight = Weight.fromDto(weightR);
        if(weight.getWeight().intValue() < 10){
            return ResponseEntity.badRequest().build();
        }
        weight.setUser(user);
        System.out.println("Estoy en agregar");
        weightRepository.save(weight);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/postweight/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateWeight(@Validated @RequestBody WeightDto weightR) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.getUserByUserName(currentPrincipalName);
        Weight weight = Weight.fromDto(weightR);
        weight.setUser(user);
        System.out.print(weight.toString());
        weightRepository.save(weight);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteWeight(@PathVariable("id") Integer id){
        if(id>-1) {
            weightRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }
}
