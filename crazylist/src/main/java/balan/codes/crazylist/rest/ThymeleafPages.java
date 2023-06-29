package balan.codes.crazylist.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafPages {
    @GetMapping("/")
    public String landing(Model model) {
        return "landing";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/login-crazylist")
    public String login(Model model) {
        return "login";
    }



//    @GetMapping("/logout")
//    public String logout(Model model) {
//        return "logout";
//    }

//    @GetMapping("/showError")
//    public String showError(Exception e, Model model) {
//        String error = e.getMessage();
//        model.addAttribute("message", error);
//        return "home-app";
//    }
}

