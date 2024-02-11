package com.app.MyeFamilyDoctor.repository;

import com.app.MyeFamilyDoctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // Επιστρέφει μια λίστα γιατρών βάσει επωνύμου
    List<Doctor> findByLastName(String lastName);

    // Επιστρέφει μια λίστα γιατρών βάσει ταχυδρομικού κώδικα
    List<Doctor> findByPostalCode(Integer postalCode);
}
