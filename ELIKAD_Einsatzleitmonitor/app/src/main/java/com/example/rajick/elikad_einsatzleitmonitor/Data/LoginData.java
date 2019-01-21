package com.example.rajick.elikad_einsatzleitmonitor.Data;

public class LoginData {
    private String name;
    private String password;

    public LoginData(String TelNr, String Code){
        this.name =TelNr;
        this.password = Code;
    }

    public String getName() {
        return name;
    }

    public void setName(String telNr) {
        name = telNr;
    }

    public String getCode() {
        return password;
    }

    public void setCode(String code) {
        password = code;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "Name='" + name + '\'' +
                ", Password='" + password + '\'' +
                '}';
    }
}
