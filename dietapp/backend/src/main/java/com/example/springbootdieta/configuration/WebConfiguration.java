package com.example.springbootdieta.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(final ViewControllerRegistry registry){
        Arrays.asList(
                "/login",
                "/signup",
                "/landing",
                "/weight-history"
        ).forEach(u-> registry.addViewController(u).setViewName("forward:index.html") );
    }

}
