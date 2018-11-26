package com.example.rajick.elikad_einsatzleitmonitor.Data;

import java.time.LocalDate;

public class Member {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private LocalDate dateOfEntry;
    private String phoneNumber;
    private String eMail;
    private String svNr;
    private MemberFunctions function;

    public Member(String firstName, String lastName, LocalDate dateOfBirth, LocalDate dateOfEntry, String phoneNumber, String eMail, String svNr, MemberFunctions function){
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setDateOfBirth(dateOfBirth);
        this.setDateOfEntry(dateOfEntry);
        this.setPhoneNumber(phoneNumber);
        this.seteMail(eMail);
        this.setSvNr(svNr);
        this.setFunction(function);
    }

    public Member(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getDateOfEntry() {
        return dateOfEntry;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public String getSvNr() {
        return svNr;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfEntry(LocalDate dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setSvNr(String svNr) {
        this.svNr = svNr;
    }

    public MemberFunctions getFunction(){
        return function;
    }

    public void setFunction(MemberFunctions function){
        this.function = function;
    }
}
