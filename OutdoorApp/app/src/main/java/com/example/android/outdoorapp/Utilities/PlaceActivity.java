package com.example.android.outdoorapp.Utilities;

import java.io.Serializable;

public class PlaceActivity implements Serializable{
    String name;
    String uniqueID;
    String placeID;
    String activityTypeID;
    String activityTypeName;
    String url;
    String description;
    String length;
    String thumbnail;
    String rating;
    String rank;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUniqueID() {
        return uniqueID;
    }
    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }
    public String getPlaceID() {
        return placeID;
    }
    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }
    public String getActivityTypeID() {
        return activityTypeID;
    }
    public void setActivityTypeID(String activityTypeID) {
        this.activityTypeID = activityTypeID;
    }
    public String getActivityTypeName() {
        return activityTypeName;
    }
    public void setActivityTypeName(String activityTypeName) {
        this.activityTypeName = activityTypeName;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLength() {
        return length;
    }
    public void setLength(String length) {
        this.length = length;
    }
    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }




}
