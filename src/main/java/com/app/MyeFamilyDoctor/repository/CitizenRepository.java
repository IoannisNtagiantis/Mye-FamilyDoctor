package com.app.MyeFamilyDoctor.repository;

import com.app.MyeFamilyDoctor.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {
    // Εδώ μπορείτε να προσθέσετε προσαρμοσμένες μεθόδους ερωτημάτων, αν χρειάζεται.

}
