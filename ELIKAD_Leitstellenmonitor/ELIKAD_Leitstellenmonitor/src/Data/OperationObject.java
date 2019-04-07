/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.Date;

/**
 *
 * @author rajick
 */
public class OperationObject {
    private int idControllcenter;
    private Date datetime;
    private String caller;
    private String description;
    private int alarmlevel;
    private int idLocation;

    public OperationObject(int idControllcenter, Date datetime, String caller, String description, int alarmlevel, int idLocation) {
        this.idControllcenter = idControllcenter;
        this.datetime = datetime;
        this.caller = caller;
        this.description = description;
        this.alarmlevel = alarmlevel;
        this.idLocation = idLocation;
    }

    public int getIdControllcenter() {
        return idControllcenter;
    }

    public void setIdControllcenter(int idControllcenter) {
        this.idControllcenter = idControllcenter;
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

    public void setAlarmlevel(int alarmlevel) {
        this.alarmlevel = alarmlevel;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }
    
    
    
}
