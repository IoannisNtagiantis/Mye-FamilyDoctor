package com.app.MyeFamilyDoctor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.app.MyeFamilyDoctor.model.Role;
import com.app.MyeFamilyDoctor.model.Users;
import com.app.MyeFamilyDoctor.repository.RoleRepository;
import com.app.MyeFamilyDoctor.repository.UsersRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Δημιουργία ρόλων
        Role roleCitizen = roleRepository.save(new Role("ROLE_CITIZEN"));
        Role roleDoctor = roleRepository.save(new Role("ROLE_DOCTOR"));
        Role roleAdmin = roleRepository.save(new Role("ROLE_ADMIN"));

        // Δημιουργία χρηστών
        createUser("defaultCitizen", "12345678", "citizen@example.com", new HashSet<>(Set.of(roleCitizen)));
        createUser("doctorUser", "12345678", "doctor@example.com", new HashSet<>(Set.of(roleDoctor)));
        createUser("adminUser", "12345678", "admin@example.com", new HashSet<>(Set.of(roleAdmin)));
    }

    private void createUser(String username, String password, String email, Set<Role> roles) {
        if (usersRepository.findByUsername(username).isEmpty()) {
            Users newUser = new Users();
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setEmail(email);
            newUser.setRoles(roles);
            usersRepository.save(newUser);
        }
    }
}
