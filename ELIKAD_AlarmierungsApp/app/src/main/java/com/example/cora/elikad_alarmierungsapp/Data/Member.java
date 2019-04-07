package com.example.cora.elikad_alarmierungsapp.Data;

public class Member {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;
    private int idDepartment;
    private String dateOfBirth;
    private String dateOfEntry;
    private boolean isCommanda;
    private String svNr;
    private String pin;
    private String gender;

    public Member(int id, String firstname, String lastname, String email, String phonenumber, int idDepartment, String dateOfBirth, String dateOfEntry, String svNr, String pin, String gender) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.idDepartment = idDepartment;
        this.dateOfBirth = dateOfBirth;
        this.dateOfEntry = dateOfEntry;
        this.svNr = svNr;
        this.pin = pin;
        this.gender = gender;
    }

    public Member (String firstname, String lastname, String email, String phonenumber, int idDepartment, String dateOfBirth, String dateOfEntry, String svNr, String pin, String gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.idDepartment = idDepartment;
        this.dateOfBirth = dateOfBirth;
        this.dateOfEntry = dateOfEntry;
        this.svNr = svNr;
        this.pin = pin;
        this.gender = gender;
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

    public boolean getIsCommanda() {
        return isCommanda;
    }

    public void setIsCommanda(boolean commanda) {
        isCommanda = commanda;
    }

    public String getSvNr() {
        return svNr;
    }

    public void setSvNr(String svNr) {
        this.svNr = svNr;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", idDepartment=" + idDepartment +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", dateOfEntry='" + dateOfEntry + '\'' +
                ", isCommanda=" + isCommanda +
                ", svNr='" + svNr + '\'' +
                ", pin='" + pin + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
