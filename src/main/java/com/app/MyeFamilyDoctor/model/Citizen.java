package com.app.MyeFamilyDoctor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Citizen  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // Προσθέστε τα απαραίτητα πεδία
    private String lastName;
    private String email;
    private int familyMembers;
    private int postalCode;


    public Citizen(Long id, String name, String lastName, String email, int familyMembers, int postalCode) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.familyMembers = familyMembers;
        this.postalCode = postalCode;
    }

    public Citizen() {

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

    public int getFamilyMembers() {
        return familyMembers;
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

    public void setFamilyMembers(int familyMembers) {
        this.familyMembers = familyMembers;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

}
