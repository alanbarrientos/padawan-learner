package com.example.springbootdieta.configuration;

import com.example.springbootdieta.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = getTokenFromRequest(request);
            System.out.println("Token--" + token);
            if(token != null && jwtTokenUtil.validateJwtToken(token)){
                String username = jwtTokenUtil.getUserNameFromJwtToken(token);
                System.out.println("User Name--JwtTokenFilter-- " + username);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                System.out.println("Authorities--JwtTokenFilter-- " + userDetails.getAuthorities());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception e){
            throw new RuntimeException("Cannot set user authentication" + e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null){
            for(Cookie c: cookies) {
                if (c.getName().equals("Token")) {
                    token = c.getValue();
                }
            }
        }
        System.out.println("Token from Request-- " + token);
        return token;
    }
}
