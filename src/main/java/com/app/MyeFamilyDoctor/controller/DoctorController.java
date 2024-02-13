package com.app.MyeFamilyDoctor.controller;

import com.app.MyeFamilyDoctor.model.AppointmentRequest;
import com.app.MyeFamilyDoctor.service.AppointmentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private AppointmentRequestService appointmentService;

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentRequest>> getAppointmentsByDoctor(@RequestParam Long doctorId) {
        List<AppointmentRequest> appointments = appointmentService.findAppointmentsByDoctorIdOrderByDate(doctorId);
        return ResponseEntity.ok(appointments);
    }

    @PutMapping("/appointments/{requestId}/accept")
    public ResponseEntity<AppointmentRequest> acceptAppointment(@PathVariable Long requestId) {
        try {
            AppointmentRequest updatedRequest = appointmentService.acceptAppointmentRequest(requestId);
            return ResponseEntity.ok(updatedRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/appointments/{requestId}/reject")
    public ResponseEntity<AppointmentRequest> rejectAppointment(@PathVariable Long requestId) {
        try {
            AppointmentRequest updatedRequest = appointmentService.rejectAppointmentRequest(requestId);
            return ResponseEntity.ok(updatedRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
