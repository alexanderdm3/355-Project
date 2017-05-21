package com.example.android.outdoorapp.Utilities;

/**
 * This class can be populated using the JSonReader class and geoLookupData method.
 * It allows you to enter a latitude and longitude in the form of a url, and get a
 * more useful json url.
 *
 * URL form: http://api.wunderground.com/api/933deb4295610296/geolookup/q/37.5407,-77.4360.json
 *
 * My wunderground API code: 933deb4295610296
 */

public class GeoLookup {
    private String type;
    private String country;
    private String country_iso;
    private String countryName;
    private String state;
    private String city;
    private String tz_short;
    private String tz_Long;
    private String lat;
    private String lon;
    private String zip;
    private String magic;
    private String wmo;
    private String l;
    private String requestURL;
    private String wuiUrl;


    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCountry_iso() {
        return country_iso;
    }
    public void setCountry_iso(String country_iso) {
        this.country_iso = country_iso;
    }
    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getTz_short() {
        return tz_short;
    }
    public void setTz_short(String tz_short) {
        this.tz_short = tz_short;
    }
    public String getTz_Long() {
        return tz_Long;
    }
    public void setTz_Long(String tz_Long) {
        this.tz_Long = tz_Long;
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
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }
    public String getMagic() {
        return magic;
    }
    public void setMagic(String magic) {
        this.magic = magic;
    }
    public String getWmo() {
        return wmo;
    }
    public void setWmo(String wmo) {
        this.wmo = wmo;
    }
    public String getL() {
        return l;
    }
    public void setL(String l) {
        this.l = l;
    }
    public String getRequestURL() {
        return requestURL;
    }
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }
    public String getWuiUrl() {
        return wuiUrl;
    }
    public void setWuiUrl(String wuiUrl) {
        this.wuiUrl = wuiUrl;
    }

}
