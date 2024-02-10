package com.app.MyeFamilyDoctor.controller;

import com.app.MyeFamilyDoctor.model.Users;
import com.app.MyeFamilyDoctor.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @Autowired
    private UsersService userService;

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody Users user) throws Exception { // Διορθωμένος τύπος στην υπογραφή της μεθόδου
        Users registeredUser = userService.registerNewUser(user); // Διορθωμένος τύπος της μεταβλητής
        return ResponseEntity.ok(registeredUser);
    }
}
