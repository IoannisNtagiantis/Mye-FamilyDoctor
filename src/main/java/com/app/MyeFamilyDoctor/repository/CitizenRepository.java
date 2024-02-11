package com.app.MyeFamilyDoctor.repository;

import com.app.MyeFamilyDoctor.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {
    // Εδώ μπορείτε να προσθέσετε προσαρμοσμένες μεθόδους ερωτημάτων, αν χρειάζεται.
    List<Citizen> findByEmail(String email);
    List<Citizen> findByPostalCode(int postalCode);
    List<Citizen> findByAmka(int amka);

}
