package com.example.cora.elikad_alarmierungsapp.Data;

public class Location {
    private String housenumber;
    private String street;
    private String postalcode;
    private String village;
    private String region;

    public Location(String housenumber, String street, String postalcode, String village, String region) {
        this.housenumber = housenumber;
        this.street = street;
        this.postalcode = postalcode;
        this.village = village;
        this.region = region;
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

    public void setStreet(String street) {
        this.street = street;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
