package com.example.cora.elikad_alarmierungsapp.Data;

import java.time.LocalDate;

public class Member {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;
    private String group;
    private int idDepartment;
    private String dateOfBirth;
    private String dateOfEntry;

    public Member(int id, String firstname, String lastname, String email, String phonenumber, String group, int idDepartment, String dateOfBirth, String dateOfEntry) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.group = group;
        this.idDepartment = idDepartment;
        this.dateOfBirth = dateOfBirth;
        this.dateOfEntry = dateOfEntry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String function) {
        this.group = group;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String birthDate) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(String entryDate) {
        this.dateOfEntry = dateOfEntry;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", group='" + group + '\'' +
                ", idDepartment=" + idDepartment +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfEntry=" + dateOfEntry +
                '}';
    }
}
