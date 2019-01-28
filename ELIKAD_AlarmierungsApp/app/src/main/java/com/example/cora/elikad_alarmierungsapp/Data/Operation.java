package com.example.cora.elikad_alarmierungsapp.Data;

import java.time.DateTimeException;
import java.util.Date;

public class Operation {
    private Date datetime;
    private Enum OperationType;
    private String caller;
    private String text;
    private String alarmlevel;
    private String controlcenterName;
    private Location location;

    public Operation(int id, String text, String alarmlevel, Date datetime, String caller,
                      Enum operationType, String controlcenterName, Location location) {
        this.datetime = datetime;
        OperationType = operationType;
        this.caller = caller;
        this.text = text;
        this.alarmlevel = alarmlevel;
        this.controlcenterName = controlcenterName;
        this.location = location;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Enum getOperationType() {
        return OperationType;
    }

    public void setOperationType(Enum operationType) {
        OperationType = operationType;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAlarmlevel() {
        return alarmlevel;
    }

    public void setAlarmlevel(String alarmlevel) {
        this.alarmlevel = alarmlevel;
    }

    public String getControlcenterName() {
        return controlcenterName;
    }

    public void setControlcenterName(String controllcenter) {
        this.controlcenterName = controllcenter;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "datetime=" + datetime +
                ", OperationType=" + OperationType +
                ", caller='" + caller + '\'' +
                ", text='" + text + '\'' +
                ", alarmlevel='" + alarmlevel + '\'' +
                ", controlcenterName='" + controlcenterName + '\'' +
                ", location=" + location +
                '}';
    }
}
