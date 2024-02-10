package com.app.MyeFamilyDoctor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // Προσθέστε τα απαραίτητα πεδία
    private String lastName;
    private String email;
    private int capacity;
    private int postalCode;

    public Doctor(Long id, String name, String lastName, String email, int capacity, int postalCode) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.capacity = capacity;
        this.postalCode = postalCode;
    }

    public Doctor() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
