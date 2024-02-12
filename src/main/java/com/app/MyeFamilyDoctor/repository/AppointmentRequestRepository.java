package com.app.MyeFamilyDoctor.repository;

import com.app.MyeFamilyDoctor.model.AppointmentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequest, Long> {
    List<AppointmentRequest> findByCitizenIdOrderByAppointmentDateTimeDesc(Long citizenId);
    List<AppointmentRequest> findByDoctorIdOrderByAppointmentDateTimeDesc(Long doctorId);

}

