package com.example.cora.elikad_alarmierungsapp.Data;

import java.time.DateTimeException;
import java.util.Date;

public class Operation {
    private int id;
    private String text;
    private String alarmlevel;
    private Date startDatetime;
    private Date endDatetime;
    private String caller;
    private Enum OperationType;
    private String controlcenterName;
    private Location location;

    public Operation(int id, String text, String alarmlevel, Date startDatetime, Date endDatetime, String caller, Enum operationType, String controlcenterName, Location location) {
        this.id = id;
        this.text = text;
        this.alarmlevel = alarmlevel;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
        this.caller = caller;
        OperationType = operationType;
        this.controlcenterName = controlcenterName;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public Enum getOperationType() {
        return OperationType;
    }

    public void setOperationType(Enum operationType) {
        OperationType = operationType;
    }

    public String getControlcenterName() {
        return controlcenterName;
    }

    public void setControlcenterName(String controlcenterName) {
        this.controlcenterName = controlcenterName;
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
                "id=" + id +
                ", text='" + text + '\'' +
                ", alarmlevel='" + alarmlevel + '\'' +
                ", startDatetime=" + startDatetime +
                ", endDatetime=" + endDatetime +
                ", caller='" + caller + '\'' +
                ", OperationType=" + OperationType +
                ", controlcenterName='" + controlcenterName + '\'' +
                ", location=" + location +
                '}';
    }
}
