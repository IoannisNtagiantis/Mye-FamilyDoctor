package com.app.MyeFamilyDoctor.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class AppointmentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Citizen citizen;

    @ManyToOne
    private Doctor doctor;

    private LocalDateTime appointmentDateTime;

    private String status; // Θα μπορούσε να είναι 'pending', 'accepted', 'rejected'

    @PrePersist
    protected void onCreate() {
        this.appointmentDateTime = LocalDateTime.now(); // Ορίζει την τρέχουσα ημερομηνία και ώρα κατά τη δημιουργία
    }

    // Constructors, Getters, Setters

    public AppointmentRequest(Long id, Citizen citizen, Doctor doctor, String status) {
        this.id = id;
        this.citizen = citizen;
        this.doctor = doctor;
        this.status = status;
    }

    public AppointmentRequest() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }
}

