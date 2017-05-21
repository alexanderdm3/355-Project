package com.example.android.outdoorapp;


import android.content.res.AssetManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RiverFragment extends Fragment implements View.OnClickListener {


    private List<RiverLocation> list;
    private ImageButton riverConfirm;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_river, container, false);

        riverConfirm = (ImageButton) rootView.findViewById(R.id.riverConfirm);

        /*
        The asset manager allows for the reading of the .txt input file. It is locate in /main/assets/
        The Manager reads the file and passes it to an InputStream. The InputStream is passed
        to class RiverFileReader and an array is returned containing the sorted rivers.
         */


        AssetManager assManager = getActivity().getApplicationContext().getAssets();

        InputStream is = null;
        try {
            is = assManager.open("rivers2.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RiverFileReader rfr = new RiverFileReader();
        list = rfr.readRiverFile(is);

        /*
        This ArrayList was created to hold Strings rather than RiverLocations.
         */
        ArrayList<String> string = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++){
            string.add(list.get(i).toString());
        }

        //This list was created to be passed to the spinner object.
        List<String> rivers = string;


        /*
        The array adapter sets up the conditions for the spinner to be used as a drop-down menu.
         */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_dropdown_item_1line, string);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       /*
        final Spinner spinner = (Spinner) findViewById(R.id.riverSpinner);
        spinner.setAdapter(adapter);

        */
        final AutoCompleteTextView textView = (AutoCompleteTextView) rootView.findViewById(R.id.aCTextView);
        textView.setThreshold(1);
        textView.setAdapter(adapter);

        /*
        The onClickListener for the Button.
         */
        riverConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //String river = spinner.getSelectedItem().toString();
                String river = textView.getText().toString();
                RiverLocation selectedRiver = null;

                // Choosing the RiverLocation based off of the toString in the auto-complete box
                for (int i = 0; i < list.size(); i++){
                    if (river.equals(list.get(i).toString())) {
                        selectedRiver = list.get(i);

                    }
                }

                if (selectedRiver == null) {
                    Toast.makeText(getActivity().getApplicationContext(), "Please select a river", Toast.LENGTH_SHORT).show(); // Making toast upon button click
                }else {
                    Intent myIntent = new Intent(RiverFragment.this.getActivity(), RiverConditions.class);  //Intent from this Activity to RiverCondtions Activity
                    myIntent.putExtra("selectedLocation", selectedRiver); //Send RiverLocation to next Activity    //need to check if this is being sent
                    myIntent.putExtra("key", river);                     //Intent with the River's toString
                    RiverFragment.this.getActivity().startActivity(myIntent);
                }
            }
        });

        return rootView;


    }

    @Override
    public void onClick(View v) {

    }
}
