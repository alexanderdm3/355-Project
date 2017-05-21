package com.example.android.outdoorapp.Utilities;

import android.os.AsyncTask;

public class AstronomyInBackground extends AsyncTask<String, Void, Astronomy> {

    Astronomy dataPoint = null;
    boolean isFinished = false;

    public boolean getIsFinished(){
        return isFinished;
    }



    public Astronomy getADP(){
        return dataPoint;
    }

    protected void onPreExecute(){

    }


    @Override
    protected Astronomy doInBackground(String... latLon) {

        String url = "http://api.wunderground.com/api/933deb4295610296/astronomy/q/" + latLon[0] + "," + latLon[1] + ".json";
        JSonReader read = new JSonReader();
        Astronomy ast = null;
        try{

            ast = read.astronomyData(url);

        }catch(Exception e){
            e.printStackTrace();
        }
        dataPoint = ast;
        isFinished = true;
        return ast;
    }


    protected void onPostExecute(Astronomy ast){
        dataPoint = ast;
        //isFinished = true;
    }


}