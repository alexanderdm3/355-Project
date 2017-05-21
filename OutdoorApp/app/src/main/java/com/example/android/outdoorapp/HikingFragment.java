package com.example.android.outdoorapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.outdoorapp.Utilities.CityInBackground;
import com.example.android.outdoorapp.Utilities.GeoLookup;
import com.example.android.outdoorapp.Utilities.GeocodingInBackground;
import com.example.android.outdoorapp.Utilities.HikingInBackground;
import com.example.android.outdoorapp.Utilities.JSonReader;
import com.example.android.outdoorapp.Utilities.USCity;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class HikingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public String stateCode = "";
    ImageButton bttn;
    EditText radField;
    Spinner spin;
    TextView select;
    TextView stateVw;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_hiking, container, false);

        spin = (Spinner) rootView.findViewById(R.id.stateSpin);
        select = (TextView) rootView.findViewById(R.id.selection);
        stateVw = (TextView) rootView.findViewById(R.id.stateTitle);


        final ArrayList<String> states = new ArrayList<String>();
        final ArrayList<String> cities = new ArrayList<String>();
        final Spinner spinner = (Spinner) rootView.findViewById(R.id.citySpin);
        spinner.setVisibility(View.GONE);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getActivity().getAssets().open("states.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                states.add(mLine.trim());
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        select.setText("Choose a State:");
        states.add(0, "<States>");
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_layout, states);
        stateAdapter.setDropDownViewResource(R.layout.spinner_dd_layout);

        spin.setAdapter(stateAdapter);
        spin.setSelection(0, false);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                           @Override
                                           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                               spinner.setVisibility(View.GONE);
                                               String convert = getStateCode(spin.getSelectedItem().toString().trim());
                                               stateCode = convert;
                                               String url = "http://api.sba.gov/geodata/city_links_for_state_of/" + convert + ".json";
                                               String[] state = {url};
                                               ArrayList<USCity> city = new ArrayList<USCity>();
                                               CityInBackground cIB = new CityInBackground();
                                               cIB.execute(state);

                                               while (cIB.getIsFinished() == false){
                                                   try {
                                                       Thread.sleep(5);
                                                   } catch (InterruptedException e) {
                                                       e.printStackTrace();
                                                   }
                                               }

                                               city = cIB.getCityList();


                                               for (USCity c : city){
                                                   cities.add(c.getCity());
                                               }
                                               Collections.sort(cities, String.CASE_INSENSITIVE_ORDER);

                                               ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_layout, cities);

                                               adapter.setDropDownViewResource(R.layout.spinner_dd_layout);

                                               stateVw.setText(spin.getSelectedItem().toString());
                                               spinner.setAdapter(adapter);
                                               select.setText("Choose the nearest city:");
                                               spin.setVisibility(View.GONE);
                                               spinner.setVisibility(View.VISIBLE);

                                           }

                                           @Override
                                           public void onNothingSelected(AdapterView<?> parent) {
                                                spinner.setVisibility(View.GONE);
                                           }

        }
        );

        bttn = (ImageButton) rootView.findViewById(R.id.btnSearch);
        radField = (EditText) rootView.findViewById(R.id.radiusField);

        bttn.setBackgroundResource(android.R.color.transparent);




        //Setting the onClickListener
        bttn.setOnClickListener(new View.OnClickListener() {

            String[] args = new String[3];
            String[] city = new String[2];
            public void onClick(View v) {
                if (radField.getText().toString().matches("")){
                    Toast.makeText(getActivity().getApplicationContext(), "Please enter a search radius", Toast.LENGTH_SHORT).show();
                }else if(cities == null || cities.size() == 0){
                    Toast.makeText(getActivity().getApplicationContext(), "Choose a State", Toast.LENGTH_SHORT).show();
                }
                else{
                    JSonReader jR = new JSonReader();
                    city[0] = spinner.getSelectedItem().toString().trim().replaceAll(" ", "_");
                    city[1] = stateCode.toUpperCase();
                    String lat;
                    String lon;




                    GeocodingInBackground gIB = new GeocodingInBackground();
                    gIB.execute(city);


                    while (gIB.getIsFinished() == false) {
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    GeoLookup geo = gIB.getGLU();
                    lat = geo.getLat();
                    lon = geo.getLon();
                        args[0] = lat;
                        args[1] = lon;
                        args[2] = radField.getText().toString();
                    HikingInBackground hIB = new HikingInBackground();
                    hIB.execute(args);

                    while(hIB.getIsFinished() == false){
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    Intent myIntent = new Intent(HikingFragment.this.getActivity(), PlacesList.class);  //Intent from this Activity to RiverCondtions Activity
                    myIntent.putExtra("places", hIB.getPlaces()); //Send RiverLocation to next Activity    //need to check if this is being sent//Intent with the River's toString
                    HikingFragment.this.getActivity().startActivity(myIntent);

                }
            }
        });





        return rootView;
    }


    public String getStateCode(String st){
        String code = "";
        switch (st) {
            case "Alabama":
                code = "al";
                break;
            case "Alaska":
                code = "ak";
                break;
            case "Arizona":
                code = "az";
                break;
            case "Arkansas":
                code = "ar";
                break;
            case "California":
                code = "ca";
                break;
            case "Colorado":
                code = "co";
                break;
            case "Connecticut":
                code = "ct";
                break;
            case "Delaware":
                code = "de";
                break;
            case "Florida":
                code = "fl";
                break;
            case "Georgia":
                code = "ga";
                break;
            case "Hawaii":
                code = "hi";
                break;
            case "Idaho":
                code = "id";
                break;
            case "Illinois":
                code = "il";
                break;
            case "Indiana":
                code = "in";
                break;
            case "Iowa":
                code = "ia";
                break;
            case "Kansas":
                code = "ks";
                break;
            case "Kentucky":
                code = "ky";
                break;
            case "Louisiana":
                code = "la";
                break;
            case "Maine":
                code = "me";
                break;
            case "Maryland":
                code = "md";
                break;
            case "Massachusetts":
                code = "ma";
                break;
            case "Michigan":
                code = "mi";
                break;
            case "Minnesota":
                code = "mn";
                break;
            case "Mississippi":
                code = "ms";
                break;
            case "Missouri":
                code = "mo";
                break;
            case "Montana":
                code = "mt";
                break;
            case "Nebraska":
                code = "ne";
                break;
            case "Nevada":
                code = "nv";
                break;
            case "New Hampshire":
                code = "nh";
                break;
            case "New Jersey":
                code = "nj";
                break;
            case "New Mexico":
                code = "nm";
                break;
            case "New York":
                code = "ny";
                break;
            case "North Carolina":
                code = "nc";
                break;
            case "North Dakota":
                code = "nd";
                break;
            case "Ohio":
                code = "oh";
                break;
            case "Oklahoma":
                code = "ok";
                break;
            case "Oregon":
                code = "or";
                break;
            case "Pennsylvania":
                code = "pa";
                break;
            case "Rhode Island":
                code = "ri";
                break;
            case "South Carolina":
                code = "sc";
                break;
            case "South Dakota":
                code = "sd";
                break;
            case "Tennessee":
                code = "tn";
                break;
            case "Texas":
                code = "tx";
                break;
            case "Utah":
                code = "ut";
                break;
            case "Vermont":
                code = "vt";
                break;
            case "Virginia":
                code = "va";
                break;
            case "Washington":
                code = "wa";
                break;
            case "West Virginia":
                code = "wv";
                break;
            case "Wisconsin":
                code = "wi";
                break;
            case "Wyoming":
                code = "wy";
                break;

        }
        return code;
    }
}
