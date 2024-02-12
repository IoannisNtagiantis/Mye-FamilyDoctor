package com.app.MyeFamilyDoctor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.app.MyeFamilyDoctor.model.Role;
import com.app.MyeFamilyDoctor.model.Users;
import com.app.MyeFamilyDoctor.repository.RoleRepository;
import com.app.MyeFamilyDoctor.repository.UsersRepository;
import com.app.MyeFamilyDoctor.repository.CitizenRepository;
import com.app.MyeFamilyDoctor.repository.DoctorRepository;
import com.app.MyeFamilyDoctor.model.Citizen;
import com.app.MyeFamilyDoctor.model.Doctor;

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

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public void run(String... args) throws Exception {
        Role roleCitizen = roleRepository.save(new Role("ROLE_CITIZEN"));
        Role roleDoctor = roleRepository.save(new Role("ROLE_DOCTOR"));
        Role roleAdmin = roleRepository.save(new Role("ROLE_ADMIN"));

        // Δημιουργία γιατρού
        createUserWithDetails("defaultDoctor", "password123", "doctor@example.com", new HashSet<>(Set.of(roleDoctor)), "Ioannis", "Ntagiantis", "", 0, 50, 19004);

        // Δημιουργία πολίτη
        createUserWithDetails("defaultCitizen", "password123", "citizen@example.com", new HashSet<>(Set.of(roleCitizen)), "Panos", "Marinos", "24100300236", 4, 0, 54321);

        // Δημιουργία διαχειριστή
        createUser("adminUsername", "adminPassword", "admin@example.com", new HashSet<>(Set.of(roleAdmin)));
    }

    private void createUser(String username, String password, String email, Set<Role> roles) {
        if (!usersRepository.findByUsername(username).isPresent()) {
            Users newUser = new Users();
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setEmail(email);
            newUser.setRoles(roles);
            usersRepository.save(newUser);
        }
    }

    private void createUserWithDetails(String username, String password, String email, Set<Role> roles, String name, String lastName, String amka, int familyMembers, int capacity, int postalCode) {
        Users newUser = usersRepository.findByUsername(username).orElseGet(() -> {
            Users user = new Users();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setRoles(roles);
            return usersRepository.save(user);
        });

        if (roles.stream().anyMatch(role -> "ROLE_CITIZEN".equals(role.getName()))) {
            Citizen citizen = new Citizen();
            citizen.setName(name);
            citizen.setLastName(lastName);
            citizen.setAmka(amka);
            citizen.setFamilyMembers(familyMembers);
            citizen.setPostalCode(postalCode);
            citizenRepository.save(citizen);
        } else if (roles.stream().anyMatch(role -> "ROLE_DOCTOR".equals(role.getName()))) {
            Doctor doctor = new Doctor();
            doctor.setName(name);
            doctor.setLastName(lastName);
            doctor.setCapacity(capacity);
            doctor.setPostalCode(postalCode);
            doctorRepository.save(doctor);
        }
    }
}
