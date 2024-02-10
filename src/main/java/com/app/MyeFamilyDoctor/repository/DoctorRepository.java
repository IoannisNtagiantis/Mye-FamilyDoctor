package com.app.MyeFamilyDoctor.repository;

import com.app.MyeFamilyDoctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // Παραδείγματα προσαρμοσμένων μεθόδων ερωτημάτων:
    Doctor findByLastName(String lastName);
}
