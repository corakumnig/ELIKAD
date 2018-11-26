package com.example.rajick.elikad_einsatzleitmonitor.Data;

public class Department {
    private static int id;
    private static String name;

    public static String getName(){
        return name;
    }

    public static void setName(String DepName){
        name = DepName;
    }

    public static int getId(){
        return id;
    }

    public static void setId(int DepId){
        id = DepId;
    }

}
