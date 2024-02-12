package com.app.MyeFamilyDoctor.repository;

import com.app.MyeFamilyDoctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // Επιστρέφει μια λίστα γιατρών βάσει επωνύμου
    List<Doctor> findByLastName(String lastName);
    Optional<Doctor> findByUserId(Long userId);


    // Επιστρέφει μια λίστα γιατρών βάσει ταχυδρομικού κώδικα
    List<Doctor> findByPostalCode(Integer postalCode);
}
