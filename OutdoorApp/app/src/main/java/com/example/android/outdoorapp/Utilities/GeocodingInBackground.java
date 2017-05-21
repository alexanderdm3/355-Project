package com.example.android.outdoorapp.Utilities;

import android.os.AsyncTask;

/**
 * Created by DevinAlexander on 4/8/17.
 */

public class GeocodingInBackground extends AsyncTask<String, Void, GeoLookup> {


    GeoLookup geo = null;
    boolean isFinished = false;
    public boolean getIsFinished(){
        return isFinished;
    }
    public GeoLookup getGLU(){
        return geo;
    }

    protected GeoLookup doInBackground(String... city) {

        JSonReader read = new JSonReader();
        GeoLookup gLU = null;
        try{
            GeoLookup geoThis = read.geoLookupData("http://api.wunderground.com/api/933deb4295610296/geolookup/q/" + city[1] + "/" + city[0] + ".json");
            gLU = geoThis;
        }catch(Exception e){
            e.printStackTrace();
        }
        geo = gLU;
        isFinished = true;
        return gLU;
    }


    protected void onPostExecute(GeoLookup gLU){
        geo = gLU;
        //isFinished = true;
    }
}
