package com.example.android.outdoorapp.Utilities;

import android.app.ProgressDialog;
import android.os.AsyncTask;


public class WeatherInBackground extends AsyncTask<String, Void, WeatherDataPoint> {


    ProgressDialog pdog;
    WeatherDataPoint dataPoint = null;
    boolean isFinished = false;

    public boolean getIsFinished(){
        return isFinished;
    }

    public WeatherDataPoint getWDP(){
        return dataPoint;
    }

    protected void onPreExecute(){

    }

    @Override
    protected WeatherDataPoint doInBackground(String... latLon) {

        String url = "http://api.wunderground.com/api/933deb4295610296/geolookup/q/" + latLon[0] + "," + latLon[1] + ".json";
        String url2 = "http://api.wunderground.com/api/933deb4295610296/conditions/q/";
        JSonReader read = new JSonReader();
        WeatherDataPoint wDP = null;
        try{
            GeoLookup geo = read.geoLookupData(url);


            String convert = geo.getRequestURL().substring(3, geo.getRequestURL().length() -5) + ".json";

            wDP = read.createDataPoint(url2 + convert);

        }catch(Exception e){
            e.printStackTrace();
        }
        dataPoint = wDP;
        isFinished = true;
        return wDP;
    }


    protected void onPostExecute(WeatherDataPoint wDP){

        dataPoint = wDP;
        //isFinished = true;
    }


}