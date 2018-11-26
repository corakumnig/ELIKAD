package com.example.cora.elikad_alarmierungsapp.Data;

import java.time.DateTimeException;
import java.util.Date;

public class Operation {
    private Date datetime;
    private Enum OperationType;
    private String caller;
    private String description;
    private String alarmlevel;
    private Location location;
    private String controllcenter;

    public Operation(Date datetime, Enum operationType, String caller, String description,
                     String alarmlevel, Location location, String controllcenter) {
        this.datetime = datetime;
        OperationType = operationType;
        this.caller = caller;
        this.description = description;
        this.alarmlevel = alarmlevel;
        this.location = location;
        this.controllcenter = controllcenter;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlarmlevel() {
        return alarmlevel;
    }

    public void setAlarmlevel(String alarmlevel) {
        this.alarmlevel = alarmlevel;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getControllcenter() {
        return controllcenter;
    }

    public void setControllcenter(String controllcenter) {
        this.controllcenter = controllcenter;
    }
}
