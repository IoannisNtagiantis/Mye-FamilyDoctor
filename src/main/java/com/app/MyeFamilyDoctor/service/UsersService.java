package com.app.MyeFamilyDoctor.service;

import com.app.MyeFamilyDoctor.model.Role;
import com.app.MyeFamilyDoctor.model.Users;
import com.app.MyeFamilyDoctor.repository.RoleRepository;
import com.app.MyeFamilyDoctor.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Set;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users registerNewUser(Users user) throws Exception {
        // Έλεγχος αν υπάρχει ήδη χρήστης με το ίδιο username
        if (usersRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new Exception("Υπάρχει ήδη χρήστης με αυτό το username.");
        }

        // Έλεγχος αν υπάρχει ήδη χρήστης με το ίδιο email
        if (usersRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Υπάρχει ήδη χρήστης με αυτό το email.");
        }

        // Κρυπτογράφηση του κωδικού πρόσβασης πριν την αποθήκευση
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Ορισμός του ρόλου στον νέο χρήστη
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new Exception("Role not found.")); // Διαχειρίζεται το Optional με orElseThrow

        user.setRoles(new HashSet<>(Set.of(userRole)));

        // Αποθήκευση του νέου χρήστη στη βάση δεδομένων
        return usersRepository.save(user);
    }


    public Users changeUserRole(Long userId, String roleName) throws Exception {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found."));
        Role newRole = roleRepository.findByName(roleName)
                .orElseThrow(() -> new Exception("Role not found."));

        user.setRoles(new HashSet<>(Set.of(newRole))); // Θέτει τον νέο ρόλο
        return usersRepository.save(user); // Αποθηκεύει τις αλλαγές
    }


    public Users updateUserDetails(Long userId, Users userDetails) throws Exception {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new Exception("Ο χρήστης δεν βρέθηκε."));

        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        // Προσθέστε εδώ οποιεσδήποτε άλλες ενημερώσεις χρειάζεστε
        // Προσοχή: Μην αντικαταστήσετε τον κωδικό πρόσβασης εδώ χωρίς κρυπτογράφηση

        return usersRepository.save(user);
    }


}



