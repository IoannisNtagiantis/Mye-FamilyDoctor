package com.app.MyeFamilyDoctor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Citizen extends Users{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Το όνομα είναι απαραίτητο")
    private String name;
    @NotNull(message = "To Α.Μ.Κ.Α. είναι απαραίτητο")
    private String amka;

    @NotNull(message = "Το επώνυμο είναι απαραίτητο")
    private String lastName;

    @Email(message = "Μη έγκυρη διεύθυνση email")
    private String email;

    @Min(value = 1, message = "Ο αριθμός των μελών της οικογένειας πρέπει να είναι τουλάχιστον 1")
    private int familyMembers;

    private int postalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;



    public Citizen(String name, String lastName, String amka, int familyMembers, int postalCode) {
        this.name = name;
        this.lastName = lastName;
        this.familyMembers = familyMembers;
        this.postalCode = postalCode;
        this.amka = amka;
    }

    public Citizen() {

    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public void setUser(Users user) {
        this.user = user;
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

    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
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
