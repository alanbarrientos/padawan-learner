package balan.codes.crazylist.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @GetMapping("/hello-world")
    public String helloWorld(){
        return "hello world";
    }
}
