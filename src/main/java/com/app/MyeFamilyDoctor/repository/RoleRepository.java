package com.app.MyeFamilyDoctor.repository;

import com.app.MyeFamilyDoctor.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
