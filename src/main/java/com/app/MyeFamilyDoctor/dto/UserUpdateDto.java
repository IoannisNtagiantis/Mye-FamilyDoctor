package com.app.MyeFamilyDoctor.dto;

public class UserUpdateDto {
    private String name; // Προσθέστε όλα τα πεδία που επιτρέπετε να ενημερώνονται
    private String email;
    private int postalCode;
    private String password;
    private  String lastName;
    private int familyMembers;
    private int capacity;
    private String amka;
    private String role; // "DOCTOR" ή "CITIZEN"

    // Constructors, Getters, and Setters

    public UserUpdateDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(int familyMembers) {
        this.familyMembers = familyMembers;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
