package com.example.android.outdoorapp;

import java.io.Serializable;
import java.net.URL;

/*
    This class forms an abstraction of a River Location. It contains a URL, a name,
    a fork, a location, a picture code, and a site ID, all of which a parseble from
    the input file. RiverLocations will be contained in a list and passed to the
    selection box. The data may also be passed to other for further functionality.
 */

/*
    The class implements serializable so that its data can be passed between classes
    via intent. It implements the comparable interface so that the list can be sorted.
 */
public class RiverLocation implements Serializable, Comparable<RiverLocation>{
    private URL url;
    private String name;
    private String fork;
    private String location;
    private String siteId;
    private String state;



    public RiverLocation(String code, String name, String location, String state){
        this.url = null;
        this.fork = "";
        this.siteId = code;
        this.name = name;
        this.location = location;
        this.state = state;
    }


    /*
        Constructor with a name, location, URL, and picture code.
     */
    public RiverLocation(String name, String location, URL url, String code){
        this.name = name;
        this.location = location;
        this.url = url;
        this.siteId = "";
    }

    /*
        Constructor with a name, fork, location, URL, and picture code.
     */
    public RiverLocation(String name, String fork, String location, URL url, String code){
        this.name = name;
        this.fork = fork;
        this.location = location;
        this.url = url;
        this.siteId = "";
    }

    /*
        Constructor with a siteID, name, and state.
     */
    public RiverLocation(String siteId, String name, String state){
        this.url = null;
        this.fork = "";
        this.siteId = siteId;
        this.name = name;
        this.location = state;
    }


    /*
        Getters for the instance variables.
     */
    public URL getUrl() {
        return url;
    }
    public String getName() {
        return name;
    }
    public String getFork() {
        return fork;
    }
    public String getSiteId() {return siteId;}
    public String getLocation(){
        return location;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /*
            Setters for the instance variables.
         */
    public void setUrl(URL url) {
        this.url = url;
    }
    public void setSiteId() {this.siteId = siteId;}
    public void setName(String name) {
        this.name = name;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public void setFork(String fork) {
        this.fork = fork;
    }




    /*
        The overridden toString method.
     */
    public String toString(){
        return name + " (" + location + ")";

    }

    /*
        This is a funciton that returns the image query URL based on the object's
        picture code.
     */
    public String getImageURLString(){
        return "https://waterdata.usgs.gov/nwisweb/graph?agency_cd=USGS&site_no="+ getSiteId() +"&parm_cd=00065&period=7";
    }


    /*
        The compareTO function, which is required by the interface Comparable.
        This implementation will allow for sorting alpabetically by river name.
     */
    public int compareTo(RiverLocation river){
        if (this.name.compareTo(river.getName()) < 0){
            return -1;
        }
        else if (this.name.compareTo(river.getName()) > 0){
            return 1;
        }
        else {
            if (this.getLocation().compareTo(river.getLocation()) < 0){
                return -1;
            }
            else if (this.getLocation().compareTo(river.getLocation()) > 0){
                return 1;
            }
            else {
                return 0;
            }
        }

    }

}
