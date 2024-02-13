package com.app.MyeFamilyDoctor.config;

import com.app.MyeFamilyDoctor.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.app.MyeFamilyDoctor.model.Role;
import com.app.MyeFamilyDoctor.repository.RoleRepository;
import com.app.MyeFamilyDoctor.repository.UsersRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final UsersRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(UsersRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Ensure roles are created only if they don't already exist
        Role roleCitizen = roleRepository.findByName("ROLE_CITIZEN")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_CITIZEN")));
        Role roleDoctor = roleRepository.findByName("ROLE_DOCTOR")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_DOCTOR")));
        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));

        // Create users with roles if they don't already exist
        createUserIfNotExists("adminUsername", "adminPassword", "admin@example.com", new HashSet<>(Set.of(roleAdmin)));
        createUserIfNotExists("doctorUsername", "doctorPassword", "doctor@example.com", new HashSet<>(Set.of(roleDoctor)));
        createUserIfNotExists("citizenUsername", "citizenPassword", "citizen@example.com", new HashSet<>(Set.of(roleCitizen)));
    }

    private void createUserIfNotExists(String username, String password, String email, Set<Role> roles) {
        boolean userExists = userRepository.findByUsername(username).isPresent() || userRepository.findByEmail(email).isPresent();
        if (!userExists) {
            Users newUser = new Users();
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setEmail(email);
            newUser.setRoles(roles);
            userRepository.save(newUser);
        }
    }


    public void logAllRoles() {
        List<Role> roles = roleRepository.findAll();
        roles.forEach(role -> logger.info("Found role: {}", role.getName()));
    }



}
