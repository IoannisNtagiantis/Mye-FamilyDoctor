package com.app.MyeFamilyDoctor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The role name must not be blank")
    @Column(unique = true, nullable = false) // Διασφαλίζει ότι το όνομα του ρόλου είναι μοναδικό και μη null στη βάση δεδομένων
    private String name;


    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
