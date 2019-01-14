package com.example.cora.elikad_alarmierungsapp.Data;

public class LoginData {
    private String telNr;
    private String passw;

    public LoginData(String telNr, String passw) {
        this.telNr = telNr;
        this.passw = passw;
    }

    public String getTelNr() {
        return telNr;
    }

    public void setTelNr(String telNr) {
        this.telNr = telNr;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "telNr='" + telNr + '\'' +
                ", passw='" + passw + '\'' +
                '}';
    }
}
