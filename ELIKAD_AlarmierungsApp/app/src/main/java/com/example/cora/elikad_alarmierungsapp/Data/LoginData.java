package com.example.cora.elikad_alarmierungsapp.Data;

public class LoginData {
    private String phonenumber;
    private String pin;

    public LoginData(String phonenumber, String pin) {
        this.phonenumber = phonenumber;
        this.pin = pin;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "telNr='" + phonenumber + '\'' +
                ", passw='" + pin + '\'' +
                '}';
    }
}
