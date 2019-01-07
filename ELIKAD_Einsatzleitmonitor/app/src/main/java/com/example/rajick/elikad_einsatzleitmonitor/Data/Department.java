package com.example.rajick.elikad_einsatzleitmonitor.Data;

public class Department {
    private int id;
    private String name;

    public Department(int Id, String name){
        this.id = Id;
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setName(String DepName){
        name = DepName;
    }

    public int getId(){
        return id;
    }

    public void setId(int DepId){
        id = DepId;
    }


}
