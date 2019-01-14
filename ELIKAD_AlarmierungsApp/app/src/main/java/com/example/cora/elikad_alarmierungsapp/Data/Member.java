package com.example.cora.elikad_alarmierungsapp.Data;

import java.util.Date;

public class Member {
    private int svnr;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Date dateOfEntry;
    private String telNr;
    private String email;
    private Department department;
    private String gender;

    public Member(int svnr, String firstName, String lastName, Date dateOfBirth, Date dateOfEntry, String telNr, String email, Department department, String gender) {
        this.svnr = svnr;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfEntry = dateOfEntry;
        this.telNr = telNr;
        this.email = email;
        this.department = department;
        this.gender = gender;
    }

    public int getSvnr() {
        return svnr;
    }

    public void setSvnr(int svnr) {
        this.svnr = svnr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public String getTelNr() {
        return telNr;
    }

    public void setTelNr(String telNr) {
        this.telNr = telNr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
