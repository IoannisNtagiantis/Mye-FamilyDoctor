package com.app.MyeFamilyDoctor.controller;

import com.app.MyeFamilyDoctor.dto.UnifiedRegistrationDto;
import com.app.MyeFamilyDoctor.dto.UserUpdateDto;
import com.app.MyeFamilyDoctor.model.Users;
import com.app.MyeFamilyDoctor.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {

    @Autowired
    private UsersService userService;

    @PostMapping("/register")
    public ResponseEntity<Users> registerUser(@RequestBody UnifiedRegistrationDto registrationDto) throws Exception {
        Users registeredUser = userService.registerNewUser(registrationDto);
        return ResponseEntity.ok(registeredUser);
    }
    @PutMapping("/users/{userId}")
    public ResponseEntity<Users> updateUser(@PathVariable Long userId, @RequestBody UserUpdateDto userUpdateDto) {
        try {
            Users updatedUser = userService.updateUserDetails(userId, userUpdateDto); // Διασφαλίστε ότι η userService.updateUserDetails επιστρέφει το δικό σας μοντέλο Users
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
