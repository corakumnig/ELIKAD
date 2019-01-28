package com.example.rajick.elikad_einsatzleitmonitor.Data;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Member {

    private int id;
    private String svNr;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private Date dateOfEntry;
    private String phonenumber;
    private String email;
    private String gender;
    private String functionName;
    private int idDepartement;

    public Member(int id, String svNr, String firstname, String lastname, String dateOfBirth, String dateOfEntry, String phonenumber, String email, int idDepartement, String functionName) throws Exception{
        this.setFirstName(firstname);
        this.setLastName(lastname);
        this.setDateOfBirth(dateOfBirth);
        this.setDateOfEntry(dateOfEntry);
        this.setPhoneNumber(phonenumber);
        this.seteMail(email);
        this.setSvNr(svNr);
        this.functionName = functionName;
        this.id = id;
        this.idDepartement = idDepartement;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Date getDateOfEntry() {
        return dateOfEntry;
    }

    public String getPhoneNumber() {
        return phonenumber;
    }

    public String geteMail() {
        return email;
    }

    public String getSvNr() {
        return svNr;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) throws Exception{
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        this.dateOfBirth = simpleDateFormatter.parse(dateOfBirth);
    }

    public void setDateOfEntry(String dateOfEntry) throws Exception{
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        this.dateOfBirth = simpleDateFormatter.parse(dateOfEntry);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phonenumber = phoneNumber;
    }

    public void seteMail(String eMail) {
        this.email = eMail;
    }

    public void setSvNr(String svNr) {
        this.svNr = svNr;
    }

    public String getFunction(){
        return functionName;
    }

    public void setFunctionName(String function){
        this.functionName = functionName;
    }
}
