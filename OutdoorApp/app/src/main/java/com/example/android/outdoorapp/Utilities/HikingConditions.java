package com.example.android.outdoorapp.Utilities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.android.outdoorapp.R;
import com.example.android.outdoorapp.WeatherConditions;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by DevinAlexander on 4/11/17.
 */

public class HikingConditions extends AppCompatActivity {

    TextView title;
    TextView descrip;
    TextView singleAct;
    TextView length;
    TextView choose;
    Spinner spinner;
    ArrayList<String> strActs;

    String geoLocation;

    Place place = new Place();
    ImageButton butt;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hiking_conditions);

        Intent intent = getIntent();
        place = (Place) intent.getSerializableExtra("place");

        String[] lL = {place.getLat(), place.getLon()};

        WeatherInBackground dbw = new WeatherInBackground();
        dbw.execute(lL);


        length = (TextView) findViewById(R.id.trailLn);
        spinner = (Spinner) findViewById(R.id.actspin);
        singleAct = (TextView) findViewById(R.id.actTV);
        descrip = (TextView) findViewById(R.id.describe);
        title = (TextView) findViewById(R.id.title);
        choose = (TextView)findViewById(R.id.chAct);
        title.setText(place.getName());


        while (dbw.getIsFinished() == false){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        geoLocation = place.getLat() + "," + place.getLon();

        WeatherDataPoint wDP = dbw.getWDP();
        String wthr = wDP.getIcon();


        ArrayList<PlaceActivity> acts = place.getActivites();

        descrip.setMovementMethod(new ScrollingMovementMethod());
        if (acts.size() > 0) {
            descrip.setText(android.text.Html.fromHtml(acts.get(0).getDescription()).toString().replaceAll("<br />", "").replaceAll("&amp;#39;", "'"));
        }

        if (place.getActivites().size() > 1) {
            choose.setVisibility(View.VISIBLE);
            strActs = new ArrayList<>();


            for (PlaceActivity p : acts) {
                if (p.getActivityTypeName().equals("hiking")) {
                    p.setActivityTypeName("Hiking");
                } else if (p.getActivityTypeName().equals("mountain biking")) {
                    p.setActivityTypeName("Mountain Biking");
                }else if (p.getActivityTypeName().equals("camping")) {
                    p.setActivityTypeName("Camping");
                }else if (p.getActivityTypeName().equals("caving")) {
                    p.setActivityTypeName("Caving");
                }else if (p.getActivityTypeName().equals("snow sports")) {
                    p.setActivityTypeName("Snow Sports");
                }
                strActs.add(p.getActivityTypeName());

            }

            Collections.sort(strActs);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, strActs);
            adapter.setDropDownViewResource(R.layout.spinner_dd_layout);

            spinner.setVisibility(View.VISIBLE);
            spinner.setAdapter(adapter);
            if (!(acts.get(0).getLength().equals("0.0"))) {
                length.setText("Trail Length: " + acts.get(0).getLength());
            }

        }
        else if (place.getActivites().size() == 1){
            choose.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
            if (acts.get(0).getActivityTypeName().equals("hiking")) {
                acts.get(0).setActivityTypeName("Hiking");
            } else if (acts.get(0).getActivityTypeName().equals("mountain biking")) {
                acts.get(0).setActivityTypeName("Mountain Biking");
            }else if (acts.get(0).getActivityTypeName().equals("camping")) {
                acts.get(0).setActivityTypeName("Camping");
            }else if (acts.get(0).getActivityTypeName().equals("caving")) {
                acts.get(0).setActivityTypeName("Caving");
            }else if (acts.get(0).getActivityTypeName().equals("snow sports")) {
                acts.get(0).setActivityTypeName("Snow Sport");
            }
            singleAct.setText(acts.get(0).getActivityTypeName());
            if (!(acts.get(0).getLength().equals("0.0"))) {
                length.setText("Trail Length: " + acts.get(0).getLength());
            }
        }else{
            choose.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
            singleAct.setText("No Trail Data Available");
            length.setText("");
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = spinner.getSelectedItem().toString();
                for (int i = 0; i < strActs.size(); i++){
                    if(place.getActivites().get(i).getActivityTypeName().toLowerCase().equals(selected.toLowerCase())){
                        if(!(place.getActivites().get(i).getDescription() == "" || place.getActivites().get(i).getDescription() == null)) {
                            descrip.setText(android.text.Html.fromHtml(place.getActivites().get(i).getDescription()).toString().replaceAll("<br />", "").replaceAll("&amp;#39;", "'"));
                            if (!(place.getActivites().get(i).getLength().equals("") || place.getActivites().get(i).getLength() == null || place.getActivites().get(i).getLength().equals("0.0"))){
                                length.setVisibility(View.VISIBLE);
                                length.setText("Trail Length: " + place.getActivites().get(i).getLength());
                            }
                            else{
                                length.setVisibility(View.GONE);
                            }
                        }
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        String uri = wDP.getImage(wthr);
        int id = getResources().getIdentifier("com.example.android.outdoorapp:drawable/" + uri, null, null);
        butt = (ImageButton) findViewById(R.id.wthr);
        butt.setImageResource(id);


        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), WeatherConditions.class);  //Intent from this Activity to RiverCondtions Activity
                Bundle b = new Bundle();
                b.putDouble("latitude", Double.parseDouble(place.getLat()));
                b.putDouble("longitude", Double.parseDouble(place.getLon()));
                myIntent.putExtras(b);
                startActivity(myIntent);
            }
        });




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
        addressUri = Uri.parse("geo:"+ geoLocation);


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


