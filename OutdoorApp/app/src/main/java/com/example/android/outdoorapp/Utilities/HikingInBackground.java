package com.example.android.outdoorapp.Utilities;

import android.os.AsyncTask;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class HikingInBackground extends AsyncTask<String, Void, ArrayList<Place>> {


    boolean isFinished = false;
    public boolean getIsFinished(){
        return isFinished;
    }

    ArrayList<Place> places = new ArrayList<Place>();
    public ArrayList<Place> getPlaces(){
        return places;
    }
    @Override
    protected ArrayList<Place> doInBackground(String... params) {
        /*
        double lat = 37.5407;
        double lon = -77.4360;
        int radius = 50;
        */


        String[] args = params;
        String lat = args[0];
        String lon = args[1];
        String radius = args[2];




        try {
            //"https://trailapi-trailapi.p.mashape.com/?q[city_cont]=Richmond&q[state_cont]=Virginia&radius=25"
            //"https://trailapi-trailapi.p.mashape.com/?lat=" + lat + "&lon=" + lon + "&q&radius=" + radius
            HttpResponse<String> response = Unirest.get("https://trailapi-trailapi.p.mashape.com/?lat=" + lat + "&lon=" + lon + "&q&radius=" + radius)
                    .header("X-Mashape-Key", "HkvlYSyFeYmshBuNMUpfNu5D6tGep1xXBRwjsn5BCghtznu7Gv")
                    .header("Accept", "text/plain")
                    .asString();


            String JSONresult = response.getBody();

            JSONObject src = new JSONObject(JSONresult);
            JSONArray arr = src.getJSONArray("places");

            for (int i = 0; i < arr.length(); i++) {
                Place place = new Place();
                place.setName(arr.getJSONObject(i).get("name").toString());
                place.setState(arr.getJSONObject(i).get("state").toString());
                place.setCountry(arr.getJSONObject(i).get("country").toString());
                place.setCity(arr.getJSONObject(i).get("city").toString());
                place.setDirections(arr.getJSONObject(i).get("directions").toString());
                place.setLat(arr.getJSONObject(i).get("lat").toString());
                place.setLon(arr.getJSONObject(i).get("lon").toString());
                place.setDescription(arr.getJSONObject(i).get("description").toString());
                JSONObject acts = arr.getJSONObject(i);
                JSONArray activities = acts.getJSONArray("activities");
                for (int j = 0; j < activities.length(); j++) {
                    PlaceActivity pA = new PlaceActivity();
                    pA.setName(activities.getJSONObject(j).get("name").toString());
                    pA.setActivityTypeName(activities.getJSONObject(j).get("activity_type_name").toString());
                    pA.setUrl(activities.getJSONObject(j).get("url").toString());
                    pA.setDescription(activities.getJSONObject(j).get("description").toString());
                    pA.setLength(activities.getJSONObject(j).get("length").toString());

                    place.getActivites().add(pA);

                }
                places.add(place);
            }


        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        isFinished = true;
        return places;
    }

}
