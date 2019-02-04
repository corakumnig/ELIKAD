package com.example.rajick.elikad_einsatzleitmonitor.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Operation {

    private int id;
    private String text;
    private int alarmLevel;
    private Date datetime;
    private String caller;
    private String operationType;
    private String controlcenterName;


    public Operation(int id, String text, int alarmLevel, String datetime, String caller, String operationType, String controlcenterName)throws Exception{
        this.id = id;
        this.text = text;
        this.alarmLevel = alarmLevel;
        setDatetime(datetime);
        this.caller = caller;
        this.operationType = operationType;
        this.controlcenterName = controlcenterName;
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

    public String getDatetime() {
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        return simpleDateFormatter.format(this.datetime);
    }

    public String getCaller() { return this.caller; }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAlarmLevel(int alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public void setDatetime(String datetime) throws Exception{
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        this.datetime = simpleDateFormatter.parse(datetime);
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getControlcenterName() {
        return controlcenterName;
    }

    public void setControlcenterName(String controlcenterName) {
        this.controlcenterName = controlcenterName;
    }
}
