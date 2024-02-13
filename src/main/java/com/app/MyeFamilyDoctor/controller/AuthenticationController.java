package com.app.MyeFamilyDoctor.controller;

import com.app.MyeFamilyDoctor.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthenticationController {

    @Autowired
    private UsersService userService;

    @PostMapping("/login")
    public String login() {
        String role = userService.getCurrentUserRole();

        if ("ROLE_ADMIN".equals(role)) {
            return "redirect:/admin/dashboard";
        } else if ("ROLE_DOCTOR".equals(role)) {
            return "redirect:/doctor/dashboard";
        } else if ("ROLE_CITIZEN".equals(role)) {
            return "redirect:/citizen/dashboard";
        } else {
            return "redirect:/"; // default redirect for unspecified roles or non-logged in users
        }
    }

    // Other mappings
}

