/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author rajick
 */
public class Location {
    private int id;
    private String housenumber;
    private String street;
    private String postalcode;
    private String village;
    
    public Location(int id, String housenumber, String street, String postalcode, String village)
    {
        this.setId(id);
        this.setHousenumber(housenumber);
        this.setStreet(street);
        this.setPostalcode(postalcode);
        this.setVillage(village);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String steet) {
        this.street = steet;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }
    
    
    
}
