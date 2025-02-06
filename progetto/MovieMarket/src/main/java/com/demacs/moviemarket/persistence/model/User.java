package com.demacs.moviemarket.persistence.model;

import java.util.Date;

public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private String password;
    private Boolean admin;

    // Getter e setter per id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter e setter per firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter e setter per lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter per name che concatena firstName e lastName
    public String getName() {
        return (firstName + " " + lastName);
    }

    // Getter e setter per email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter e setter per password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter e setter per dob
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    // Modifica del setName per separare il nome e il cognome
    public void setName(String name) {
        if (name != null && name.contains(" ")) {
            String[] parts = name.split(" ", 2); // Separare il nome in due parti (nome e cognome)
            this.firstName = parts[0];
            this.lastName = parts[1];
        }
    }

    public Boolean getAdmin() {
        return admin;
    }
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }


}
