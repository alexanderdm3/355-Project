package com.example.android.outdoorapp.Utilities;


import java.io.Serializable;
import java.util.ArrayList;

public class Place implements Serializable{
    String city;
    String state;
    String country;
    String name;
    String parentID;
    String uniqueID;
    String directions;
    String lat;
    String lon;
    String description;
    ArrayList<PlaceActivity> activites = new ArrayList<PlaceActivity>();


    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getParentID() {
        return parentID;
    }
    public void setParentID(String parentID) {
        this.parentID = parentID;
    }
    public String getUniqueID() {
        return uniqueID;
    }
    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }
    public String getDirections() {
        return directions;
    }
    public void setDirections(String directions) {
        this.directions = directions;
    }
    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLon() {
        return lon;
    }
    public void setLon(String lon) {
        this.lon = lon;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public ArrayList<PlaceActivity> getActivites() {
        return activites;
    }
    public void setActivites(ArrayList<PlaceActivity> activites) {
        this.activites = activites;
    }



}
