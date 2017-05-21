package com.example.android.outdoorapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.outdoorapp.Utilities.NetworkUtils;
import com.example.android.outdoorapp.Utilities.RiverJsonReader;
import com.example.android.outdoorapp.Utilities.WeatherDataPoint;
import com.example.android.outdoorapp.Utilities.WeatherInBackground;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class RiverConditions extends AppCompatActivity {
    public ImageView img;
    private TextView upperView;
    private TextView siteName;
    private TextView geoSpot;
    private TextView dischargeDesc;
    private TextView dischargeValue;
    private TextView gageDesc;
    private TextView gageHeight;
    private String geoLocation = "";
    private RiverLocation selectedLocation;
    public ImageButton butt;
    public double lat;
    public double lon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_river_conditions);     //Sets the XML view to this activity

        Intent intent = getIntent();        //Recieves the passed intent

        String value = intent.getStringExtra("key");    //Revieves the value

        selectedLocation = (RiverLocation)intent.getSerializableExtra("selectedLocation");//Revieves the RiverLocation

        try {
            makeRiverSearchQuery(selectedLocation.getSiteId());     //Performs the search query
        }
        catch(Exception e){System.out.println("Exception at makeRiverSearchQuery");}

        butt = (ImageButton) findViewById(R.id.weatherBtn);

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) butt.getLayoutParams();

        params.width = 250;

        params.height = 250;

        // existing height is ok as is, no need to edit it
        butt.setLayoutParams(params);

        if (lat == 0 && lon == 0) {

        }
        else{

            String[] lL = {Double.toString(lat), Double.toString(lon)};

            WeatherInBackground dbw = new WeatherInBackground();
            dbw.execute(lL);


            while (dbw.getIsFinished() == false) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            WeatherDataPoint weather2 = dbw.getWDP();


            String wthr = weather2.getIcon();

            String uri = weather2.getImage(wthr);  // where myresource (without the extension) is the file

            int id = getResources().getIdentifier("com.example.android.outdoorapp:drawable/" + uri, null, null);
            butt.setImageResource(id);



            //Picasso.with(this).load(weather2.getIconURL()).resize(250,250).into(butt);
        }



        img = (ImageView) findViewById(R.id.imageView4);        //Sets river level ImageView by R.ID
        upperView = (TextView) findViewById(R.id.location);     //Sets the upper TextView by R.ID
        siteName = (TextView) findViewById(R.id.siteName);
        geoSpot = (TextView) findViewById(R.id.geoLocation);
        geoSpot.setTextIsSelectable(true);
        dischargeDesc = (TextView) findViewById(R.id.dischargeDescription);
        dischargeValue = (TextView) findViewById(R.id.dischargeAmount);
        gageDesc = (TextView) findViewById(R.id.gageDescription);
        gageHeight = (TextView) findViewById(R.id.gageheight);


        Picasso.with(this).load(selectedLocation.getImageURLString()).into(img);    //Picasso is a tool that allows for easy online image pulling

        upperView.setText(selectedLocation.getName());      //Sets the upper view to the rivers name

        


        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RiverConditions.this, WeatherConditions.class);  //Intent from this Activity to RiverCondtions Activity
                Bundle b = new Bundle();
                b.putDouble("latitude", lat);
                b.putDouble("longitude", lon);
                myIntent.putExtras(b);
                RiverConditions.this.startActivity(myIntent);
            }
        });

    }

    /**
     * This method retrieves the search text from the EditText, constructs the
     * URL (using {@link NetworkUtils}) for the river you'd like to find, displays
     * that URL in a TextView, and finally fires off an AsyncTask to perform the GET request using
     * our {@link riverTask}
     */
    private void makeRiverSearchQuery(String siteId) throws ExecutionException, InterruptedException {

        URL riverSearchUrl = NetworkUtils.buildUrl(siteId);

        //Create a new riverQueryTask and call its execute method, passing in the url to query
        new riverTask().execute(riverSearchUrl).get(); //prevents loading progress dialog
    }

    // COMPLETED
    public class riverTask extends AsyncTask<URL, Void, String> {


        boolean isFinished = false;


        public boolean getIsFinished(){
            return isFinished;
        }
        //let put a loading symbol here
        ProgressDialog pdog;

        //for the impatient people
        @Override
        protected void onPreExecute(){

            pdog = new ProgressDialog(RiverConditions.this);
            pdog.setMessage("Loading...");
            pdog.show();

        }
        // COMPLETED (2) Override the doInBackground method to perform the query. Return the results.
        @Override
        protected String doInBackground(URL... params) {

            String riverSearchResults = ""; //string of JSON object from request

            String siteName = "";   //gauge name
            String disDesc = "";   // discharge description
            String disValue = ""; // discharge amount
            String gageHeight = "";     //gage height, current water level
            String gageDesc = "";// gage description
            StringBuilder riverCondition = new StringBuilder();
            String riverInfo;
            double latitude = 0;
            double longitude = 0;

            try {
                Thread.sleep(500);
            }
            catch (InterruptedException i){
                Log.d("error sleep", "thread error");
            }
            URL searchUrl = params[0];


            try {
                riverSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);

                //convert https response to JSON
                JSONObject jObj = new JSONObject(riverSearchResults);

                RiverJsonReader riverReader = new RiverJsonReader(jObj);

                siteName = riverReader.getSiteName();
                //siteName = getSiteName(jObj);
                //longitude = getLongitude(jObj);
                longitude = riverReader.getLongitude();
                //latitude = getLatitude(jObj);
                latitude = riverReader.getLatitude();
                //disDesc = getDischargeDescription(jObj);
                disDesc = riverReader.getDischargeDescription();
                //disValue = getDischargeAmount(jObj);
                disValue = riverReader.getDischargeAmount();
                //gageDesc = getGageDescription(jObj);
                gageDesc = riverReader.getGageDescription();
                //gageHeight = getGageHeight(jObj);
                gageHeight = riverReader.getGageHeight();



            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException j) {
                j.printStackTrace();
            }
            //set geoLocation
            lat = latitude;
            lon = longitude;
            geoLocation = String.valueOf(latitude) + "," + String.valueOf(longitude);



            riverCondition.append(siteName + "~");
            riverCondition.append(longitude + "~");
            riverCondition.append(latitude + "~");
            riverCondition.append(disDesc+ "~");
            riverCondition.append(disValue + "~");
            riverCondition.append(gageDesc + "~");
            riverCondition.append(gageHeight + "~");

            riverInfo = riverCondition.toString();

            return riverInfo;


        }

        // COMPLETED (3) Override onPostExecute to display the results in the TextView
        @Override
        protected void onPostExecute(String riverInfo) {
            isFinished = true;
            int numFields = 7;
            boolean missingData = false;
            String delims = "[~]";
            String noData = "N/A";

            String[] info = riverInfo.split(delims);
            String[] info2 = new String[numFields];

            for (int i = 0; i  < info.length; i++){
                if(info[i].equalsIgnoreCase("") || info[i].equals(null)){
                    info[i] = noData;
                }
            }

            // copy over smaller array so we can default values
            // if returned JSON Object did not contain all fields
            if(info.length < numFields) {
                missingData = true;
                for(int j = 0; j <info2.length; j++){

                    if(j < info.length - 1){
                        info2[j] = info[j];
                    }
                    else{
                        info2[j] = noData;
                    }

                }
            }

            //If we have missing data we are defaulting NA
            if(missingData) {
                siteName.setText(info2[0]);
                geoSpot.setText("Coordinates: " + info2[1] + ", " + info2[2]);
                dischargeDesc.setText("Discharge Description: " + info2[3]);
                dischargeValue.setText("Discharge Amount: " + info2[4]);
                gageDesc.setText("Gage Description: " + info2[5]);
                gageHeight.setText("Gage Height: " + info2[6]);
            }
            //Otherwise fill in returned fields
            else{
                siteName.setText(info[0]);
                geoSpot.setText("Coordinates: " + info[1] + ", " + info[2]);
                dischargeDesc.setText(info[3]);
                dischargeValue.setText("Discharge Amount: " + info[4]);
                gageDesc.setText("Gage Description: " + info[5]);
                gageHeight.setText("Gage Height: " + info[6]);

            }



            //stop our progress dialog
            if(pdog != null){
                pdog.dismiss();
            }



        }
    }

    /**
     * Code courtesy of udacity android course and has been modified
     * This method is called when the Open Location in Map button is clicked. It will open the
     * a map to the location represented by the variable addressString using implicit Intents.
     *
     * @param v Button that was clicked.
     */
    public void onClickOpenAddressButton(View v) {

        //This was not working for some reason
        //Uri.Builder builder = new Uri.Builder();
        //builder.scheme("geo")
        //      .path("0,0")
        //    .query(geoLocation);

        //My work around
        Uri addressUri;// = builder.build();
        addressUri = Uri.parse("geo:"+geoLocation);


        showMap(addressUri);
    }


    /**
     * Code courtesy of udacity Android course, has been modified
     * This method will fire off an implicit Intent to view a location on a map.
     *
     * When constructing implicit Intents, you can use either the setData method or specify the
     * URI as the second parameter of the Intent's constructor,
     *
     *
     * @param geoLocation The Uri representing the location that will be opened in the map
     */
    private void showMap(Uri geoLocation) {

        /*
         * Again, we create an Intent with the action, ACTION_VIEW because we want to VIEW the
         * contents of this Uri.
         */
        Intent intent = new Intent(Intent.ACTION_VIEW);


        /*
         * Using setData to set the Uri of this Intent has the exact same affect as passing it in
         * the Intent's constructor. This is simply an alternate way of doing this.
         */
        intent.setData(geoLocation);


        // Verify that this Intent can be launched and then call startActivity
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}
