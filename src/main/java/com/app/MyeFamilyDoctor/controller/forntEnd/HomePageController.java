package com.app.MyeFamilyDoctor.controller.forntEnd;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomePageController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/homeDoctor")
    public String homeDoctor() {
        return "homeDoctor";
    }

    @GetMapping("/application")
    public String application(){
        return "appplication";
    }
}
