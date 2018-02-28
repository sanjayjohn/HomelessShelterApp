package com.example.rob.shelterfinder;

import java.security.PublicKey;

/**
 * Created by sanjayjohn on 2/27/18.
 */

public class ShelterData {
    private String uniqueKey;
    private String shelterName;
    private String Capacity;
    private String Restrictions;
    private String Longitude;
    private String Latitude;
    private String phone;
    private String Address;
    private String specialNotes;

    public ShelterData() {

    }

    public ShelterData(String address, String capacity, String latitude, String longitude, String phone, String restrictions, String shelterName, String specialNotes, String uniqueKey) {
        this.uniqueKey = uniqueKey;
        this.shelterName = shelterName;
        this.Capacity = capacity;
        this.Restrictions = restrictions;
        this.Longitude = longitude;
        this.Latitude = latitude;
        this.phone = phone;
        this.Address = address;
        this.specialNotes = specialNotes;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public String getShelterName() {
        return shelterName;
    }

    public String getRestrictions() {
        return Restrictions;
    }

    public String getCapacity() {
        return Capacity;
    }

    public String getLongitude() {
        return Longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return Address;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

    public void setUniqueKey(String k) {
        this.uniqueKey = k;
    }

    public void setShelterName(String n) {
        this.shelterName = n;
    }

    public void setRestrictions(String r) {
        this.Restrictions = r;
    }

    public void setCapacity(String c) {
        this.Capacity = c;
    }

    public void setLongitude(String l) {
        this.Longitude = l;
    }

    public void setLatitude(String l) {
        this.Latitude = l;
    }

    public void setPhone(String p) {
        this.phone = p;
    }

    public void setAddress(String a) {
        this.Address = a;
    }

    public void setSpecialNotes(String n) {
        this.specialNotes = n;
    }

    public String toString() {
        return shelterName;
    }


}
