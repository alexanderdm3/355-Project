package com.example.android.outdoorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.outdoorapp.Utilities.HikingConditions;
import com.example.android.outdoorapp.Utilities.Place;
import java.util.ArrayList;

import static com.example.android.outdoorapp.R.layout.layout_listview;
import static com.example.android.outdoorapp.R.layout.places_list;


/**
 * Created by DevinAlexander on 4/10/17.
 */

public class PlacesList  extends AppCompatActivity {

    ListView list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(places_list);     //Sets the XML view to this activity

        Bundle bun = getIntent().getExtras();
        final ArrayList<Place> places = (ArrayList<Place>) bun.getSerializable("places");


        ArrayList<String> placesString = new ArrayList<String>();
        for (Place p : places) {
            placesString.add(p.getName().replaceAll("&amp;", "&"));
        }



        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                layout_listview, placesString);
        list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView listText = (TextView) view.findViewById(R.id.label);
                String text = listText.getText().toString();

                Place passPlace = new Place();
                for (int i = 0; i < places.size(); i++){
                    if (text.equals(places.get(i).getName())){
                        passPlace = places.get(i);
                    }
                }



                Intent intent = new Intent(view.getContext(), HikingConditions.class);
                intent.putExtra("place", passPlace);
                startActivity(intent);
            }
        });
    }



}
