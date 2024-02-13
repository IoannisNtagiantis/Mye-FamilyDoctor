package com.app.MyeFamilyDoctor.controller;

import com.app.MyeFamilyDoctor.model.AppointmentRequest;
import com.app.MyeFamilyDoctor.service.AppointmentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentRequestController {

    @Autowired
    private AppointmentRequestService appointmentRequestService;

    // Endpoint to create a new appointment request
    @PostMapping("/request")
    public ResponseEntity<AppointmentRequest> createAppointmentRequest(@RequestParam Long citizenId, @RequestParam Long doctorId) {
        try {
            AppointmentRequest appointmentRequest = appointmentRequestService.createAppointmentRequest(citizenId, doctorId);
            return ResponseEntity.ok(appointmentRequest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint to accept an appointment request
    @PostMapping("/accept/{requestId}")
    public ResponseEntity<AppointmentRequest> acceptAppointmentRequest(@PathVariable Long requestId) {
        try {
            AppointmentRequest appointmentRequest = appointmentRequestService.acceptAppointmentRequest(requestId);
            return ResponseEntity.ok(appointmentRequest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint to reject an appointment request
    @PostMapping("/reject/{requestId}")
    public ResponseEntity<AppointmentRequest> rejectAppointmentRequest(@PathVariable Long requestId) {
        try {
            AppointmentRequest appointmentRequest = appointmentRequestService.rejectAppointmentRequest(requestId);
            return ResponseEntity.ok(appointmentRequest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint to get appointments by citizen ID
    @GetMapping("/citizen/{citizenId}")
    public ResponseEntity<List<AppointmentRequest>> getAppointmentsByCitizen(@PathVariable Long citizenId) {
        List<AppointmentRequest> appointments = appointmentRequestService.findAppointmentsByCitizenIdOrderByDate(citizenId);
        return ResponseEntity.ok(appointments);
    }

    // Endpoint to get appointments by doctor ID
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentRequest>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        List<AppointmentRequest> appointments = appointmentRequestService.findAppointmentsByDoctorIdOrderByDate(doctorId);
        return ResponseEntity.ok(appointments);
    }
}
