package com.example.android.outdoorapp.Utilities;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by DevinAlexander on 4/15/17.
 */

public class CityInBackground extends AsyncTask<String, Void, ArrayList<USCity>> {



        ArrayList<USCity> city = null;
        boolean isFinished = false;

public boolean getIsFinished(){
        return isFinished;
        }

public ArrayList<USCity> getCityList(){
        return city;
        }

protected void onPreExecute(){

        }

@Override
protected ArrayList<USCity> doInBackground(String... state) {


        JSonReader read = new JSonReader();
        ArrayList<USCity> cityOne = null;
        try{
            cityOne = read.cityData(state[0]);

        }catch(Exception e){
        e.printStackTrace();
        }
        city = cityOne;
        isFinished = true;
        return cityOne;
        }


protected void onPostExecute(ArrayList<USCity> c){
        city = c;
        isFinished = true;
        }


        }