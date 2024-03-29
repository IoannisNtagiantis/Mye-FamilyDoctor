package com.app.MyeFamilyDoctor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Doctor extends Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;

    @Email(message = "Μη έγκυρη διεύθυνση email")
    private String email;

    @Min(value = 1, message = "Η χωρητικότητα πρέπει να είναι τουλάχιστον 1")
    private int capacity;

    private int postalCode;


    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Citizen> citizens = new HashSet<>();



    public Doctor(String name, String lastName, int capacity, int postalCode) {
        this.name = name;
        this.lastName = lastName;
        this.capacity = capacity;
        this.postalCode = postalCode;
    }


    public Doctor() {

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
