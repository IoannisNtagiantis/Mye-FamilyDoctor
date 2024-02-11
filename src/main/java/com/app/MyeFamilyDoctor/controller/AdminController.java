package com.app.MyeFamilyDoctor.controller;

import com.app.MyeFamilyDoctor.model.Users;
import com.app.MyeFamilyDoctor.service.UsersService; // Βεβαιωθείτε ότι έχετε το σωστό import για την UsersService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // Μην ξεχάσετε αυτή την annotation για να δηλώσετε ότι αυτή η κλάση είναι ένας Controller
public class AdminController {

    @Autowired
    private UsersService usersService; // Αυτόματη ενσωμάτωση της UsersService

    @PutMapping("/users/{userId}/changeRole")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> changeUserRole(@PathVariable Long userId, @RequestParam String roleName) {
        try {
            Users updatedUser = usersService.changeUserRole(userId, roleName);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
