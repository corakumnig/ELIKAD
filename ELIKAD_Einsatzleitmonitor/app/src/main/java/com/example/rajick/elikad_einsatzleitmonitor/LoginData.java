package com.example.rajick.elikad_einsatzleitmonitor;

public class LoginData {
    private String TelNr;
    private String Code;

    public LoginData(String TelNr, String Code){
        this.TelNr =TelNr;
        this.Code = TelNr;
    }

    public String getTelNr() {
        return TelNr;
    }

    public void setTelNr(String telNr) {
        TelNr = telNr;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "TelNr='" + TelNr + '\'' +
                ", Code='" + Code + '\'' +
                '}';
    }
}
