package com.app.MyeFamilyDoctor.controller;

import com.app.MyeFamilyDoctor.model.AppointmentRequest;
import com.app.MyeFamilyDoctor.model.Doctor;
import com.app.MyeFamilyDoctor.service.AppointmentService;
import com.app.MyeFamilyDoctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citizens")
public class CitizenController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentService appointmentService;
    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> findDoctorsByPostalCode(@RequestParam int postalCode) {
        List<Doctor> doctors = doctorService.findDoctorsByPostalCode(postalCode);
        return ResponseEntity.ok(doctors);
    }

    @PostMapping("/appointments")
    public ResponseEntity<?> createAppointment(@RequestParam Long citizenId, @RequestParam Long doctorId) {
        try {
            AppointmentRequest appointmentRequest = appointmentService.createAppointmentRequest(citizenId, doctorId);
            return ResponseEntity.status(HttpStatus.CREATED).body(appointmentRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentRequest>> getAppointmentsByCitizen(@RequestParam Long citizenId) {
        List<AppointmentRequest> appointments = appointmentService.findAppointmentsByCitizenIdOrderByDate(citizenId);
        return ResponseEntity.ok(appointments);
    }


}


