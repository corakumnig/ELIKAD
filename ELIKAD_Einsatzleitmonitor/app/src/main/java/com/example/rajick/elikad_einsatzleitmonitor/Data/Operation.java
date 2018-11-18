package com.example.rajick.elikad_einsatzleitmonitor.Data;

import java.util.Date;

public class Operation {

    private int id;
    private String text;
    private int alarmLevel;
    private Date datetime;
    private String caller;

    public Operation(int id, String text, int alarmLevel, Date datetime, String caller){
        this.id = id;
        this.text = text;
        this.alarmLevel = alarmLevel;
        this.datetime = datetime;
        this.caller = caller;
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

    public Date getDatetime() { return this.datetime; }

    public String getCaller() { return this.caller; }
}
