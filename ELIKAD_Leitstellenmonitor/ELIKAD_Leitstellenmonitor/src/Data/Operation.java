/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author rajick
 */
public class Operation {
    private int id;
    private Date datetime;
    private String caller;
    private String description;
    private int alarmlevel;
    private String alarmReason;
    private Location location;
    private int operationTypeId;
    private ArrayList<Department> collDepartements;

    public Operation(int id, Date datetime, String caller, String description, int alarmlevel, String alarmReason, Location location, int operationTypeId)
    {
        this.setId(id);
        this.setDatetime(datetime);
        this.setCaller(caller);
        this.setDescription(description);
        this.setAlarmlevel(alarmlevel);
        this.setAlarmReason(alarmReason);
        this.setLocation(location);
        this.setOperationTypeId(operationTypeId);
        this.collDepartements = new ArrayList<Department>();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
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

    public int getAlarmlevel() {
        return alarmlevel;
    }

    public void setAlarmlevel(int Alarmlevel) {
        this.alarmlevel = Alarmlevel;
    }

    public ArrayList<Department> getCollDepartements() {
        return collDepartements;
    }

    public void setCollDepartements(ArrayList<Department> collDepartements) {
        this.collDepartements = collDepartements;
    }

    public String getAlarmReason() {
        return alarmReason;
    }

    public void setAlarmReason(String alarmReason) {
        this.alarmReason = alarmReason;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getOperationTypeId() {
        return operationTypeId;
    }

    public void setOperationTypeId(int operationTypeId) {
        this.operationTypeId = operationTypeId;
    }
    
    
}
