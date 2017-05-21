package com.example.android.outdoorapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.outdoorapp.Utilities.Astronomy;
import com.example.android.outdoorapp.Utilities.AstronomyInBackground;
import com.example.android.outdoorapp.Utilities.WeatherDataPoint;
import com.example.android.outdoorapp.Utilities.WeatherInBackground;

import static com.example.android.outdoorapp.R.id.imageView;


public class WeatherConditions extends AppCompatActivity {


    TextView txt;

    TextView weather;
    TextView tempTxt;
    TextView tempVal;
    TextView windTxt;
    TextView windVal;
    TextView heat;
    TextView heatVal;
    TextView vis;
    TextView visVal;
    TextView cond;
    TextView condVal;
    TextView loc;
    TextView sunsetVal;
    TextView sunriseVal;

    ImageView img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_conditions);     //Sets the XML view to this activity

        Bundle bun = getIntent().getExtras();
        double latitude = bun.getDouble("latitude");
        double longitude = bun.getDouble("longitude");


        weather = (TextView) findViewById(R.id.location);
        tempTxt = (TextView) findViewById(R.id.temp);
        tempVal = (TextView) findViewById(R.id.tempVal);
        windTxt = (TextView) findViewById(R.id.wind);
        windVal = (TextView) findViewById(R.id.windVal);
        heat = (TextView) findViewById(R.id.heatIn);
        heatVal = (TextView) findViewById(R.id.hiVal);
        vis = (TextView) findViewById(R.id.visib);
        visVal = (TextView) findViewById(R.id.visVal);
        cond = (TextView)  findViewById(R.id.cond);
        condVal = (TextView) findViewById(R.id.condVal);
        loc = (TextView) findViewById(R.id.location);
        sunsetVal = (TextView) findViewById(R.id.sunsetVal);
        sunriseVal = (TextView) findViewById(R.id.sunriseVal);

        img = (ImageView) findViewById(imageView);
        String[] lL = {Double.toString(latitude), Double.toString(longitude)};

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) img.getLayoutParams();
        params.width = 280;
        params.height = 280;

        // existing height is ok as is, no need to edit it
        img.setLayoutParams(params);


        WeatherInBackground dbw = new WeatherInBackground();
        AstronomyInBackground ast = new AstronomyInBackground();
        dbw.execute(lL);


        while (dbw.getIsFinished() == false){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        ast.execute(lL);
        while (ast.getIsFinished() == false){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        WeatherDataPoint weather2 = dbw.getWDP();
        Astronomy astro = ast.getADP();

        sunsetVal.setText(((Integer.parseInt(astro.getSunsetHour()) - 12) + ":" + astro.getSunsetMinute() + "p.m."));
        sunriseVal.setText(astro.getSunriseHour() + ":" + astro.getSunriseMinute() + "a.m.");


        txt = (TextView) findViewById(R.id.wind);


        //weather.setText(weather2.getWeather());
        loc.setText(weather2.getFullLocation());
        tempVal.setText(weather2.getTempF() + " F");
        windVal.setText(weather2.getWind());
        heatVal.setText(weather2.getHeatIndexF() + " F");
        visVal.setText(weather2.getVisibilityMI() + "MI");
        condVal.setText(weather2.getWeather());


        String wthr = weather2.getIcon();



        String uri = weather2.getImage(wthr);  // where myresource (without the extension) is the file



        int id = getResources().getIdentifier("com.example.android.outdoorapp:drawable/" + uri, null, null);
        img.setImageResource(id);

        //Picasso.with(this).load(weather2.getIconURL()).into(img);
    }









}
