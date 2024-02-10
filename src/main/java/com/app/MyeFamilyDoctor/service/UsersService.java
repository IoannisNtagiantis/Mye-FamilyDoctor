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

    public Users registerNewUser(Users user) throws Exception { // Εδώ αλλάξαμε τον τύπο επιστροφής από User σε Users
        // Έλεγχος αν υπάρχει ήδη χρήστης με το ίδιο username
        boolean usernameExists = usersRepository.findByUsername(user.getUsername()).isPresent();
        if (usernameExists) {
            throw new Exception("Υπάρχει ήδη χρήστης με αυτό το username.");
        }

        // Έλεγχος αν υπάρχει ήδη χρήστης με το ίδιο email
        boolean emailExists = usersRepository.findByEmail(user.getEmail()).isPresent();
        if (emailExists) {
            throw new Exception("Υπάρχει ήδη χρήστης με αυτό το email.");
        }
        // Κρυπτογράφηση του κωδικού πρόσβασης πριν την αποθήκευση
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Ορισμός του ρόλου στον νέο χρήστη
        Role userRole = roleRepository.findByName("ROLE_USER"); // Προσαρμόστε ανάλογα με τις ανάγκες σας
        user.setRoles(new HashSet<>(Set.of(userRole)));

        // Αποθήκευση του νέου χρήστη στη βάση δεδομένων
        return usersRepository.save(user);
    }

    public Users changeUserRole(Long userId, Set<Role> newRoles) throws Exception {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new Exception("Ο χρήστης δεν βρέθηκε."));
        user.setRoles(newRoles);
        return usersRepository.save(user);
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



