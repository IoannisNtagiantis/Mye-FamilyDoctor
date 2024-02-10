package com.app.MyeFamilyDoctor.config;

import com.app.MyeFamilyDoctor.model.Role;
import com.app.MyeFamilyDoctor.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        createRoleIfNotFound("ROLE_CITIZEN");
        createRoleIfNotFound("ROLE_DOCTOR");
        createRoleIfNotFound("ROLE_ADMIN");
    }

    private void createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
    }
}

