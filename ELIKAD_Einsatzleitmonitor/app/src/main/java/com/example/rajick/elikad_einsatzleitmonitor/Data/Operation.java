package com.example.rajick.elikad_einsatzleitmonitor.Data;

public class Operation {

    private int id;
    private String text;
    private int alarmLevel;

    public Operation(int id, String text, int alarmLevel){
        this.id = id;
        this.text = text;
        this.alarmLevel = alarmLevel;
    }

    public int getId(){
        return this.id;
    }

    public String getText(){
        return this.text;
    }

    public int getAlarmLevel(){
        return this.alarmLevel;
    }
}
