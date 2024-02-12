package com.app.MyeFamilyDoctor.dto;

public class UnifiedRegistrationDto {
    private String username;
    private String password;
    private String email;
    private String role; // "DOCTOR" ή "CITIZEN"
    private String name;
    private String lastName;
    // Πεδία για Doctor
    private Integer capacity;
    private Integer doctorPostalCode;
    // Πεδία για Citizen
    private String amka;
    private Integer familyMembers;
    private Integer citizenPostalCode;

    // Constructors, Getters, and Setters

    public UnifiedRegistrationDto(String username, String password, String email, String role, String name, String lastName, Integer capacity, Integer doctorPostalCode, String amka, Integer familyMembers, Integer citizenPostalCode) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.name = name;
        this.lastName = lastName;
        this.capacity = capacity;
        this.doctorPostalCode = doctorPostalCode;
        this.amka = amka;
        this.familyMembers = familyMembers;
        this.citizenPostalCode = citizenPostalCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getDoctorPostalCode() {
        return doctorPostalCode;
    }

    public void setDoctorPostalCode(Integer doctorPostalCode) {
        this.doctorPostalCode = doctorPostalCode;
    }

    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    public Integer getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(Integer familyMembers) {
        this.familyMembers = familyMembers;
    }

    public Integer getCitizenPostalCode() {
        return citizenPostalCode;
    }

    public void setCitizenPostalCode(Integer citizenPostalCode) {
        this.citizenPostalCode = citizenPostalCode;
    }
}
