package com.example.cora.elikad_alarmierungsapp.Data;

public class Department {
    private String name;
    private String passw;

    public Department(String name, String passw) {
        this.name = name;
        this.passw = passw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }
}
