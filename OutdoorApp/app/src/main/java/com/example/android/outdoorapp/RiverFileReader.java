package com.example.android.outdoorapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;


public class RiverFileReader {

    /*
        The method that parses the river file.
        Input: InputStream
        Output: ArrayList<RiverLocation>
     */
    public static ArrayList<RiverLocation> readRiverFile(InputStream stream) {
        ArrayList<RiverLocation> array = new ArrayList<RiverLocation>();
        try {
            //Initializing the InputStreamReader and BufferedStreamReader
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader buffRead = new BufferedReader(reader);

            String line = buffRead.readLine();  //Assign line to String
            while (line != null) {      // While line not null
                if (!line.isEmpty()) {      //If line not empty
                    String[] split = line.split(",");       //Split on delimiter ','. This creates an array.
                    String nameLocationSplit[];

                    /*
                        New RiverLocations are created in the conditionals below based off of oberservations
                        made from examining the input files.
                     */
                    if (split.length == 4) {
                        array.add(new RiverLocation(split[0].trim() + " River", split[1].trim().replaceAll("[()]", ""),
                                new URL(split[2].trim()), split[3].trim()));
                    } else if (split.length == 5) {
                        array.add(new RiverLocation(split[0].trim() + " River", split[1].trim(),
                                split[2].trim().replaceAll("[()]", ""), new URL(split[3].trim()), split[4].trim()));

                    }
                    else if (split.length == 3) {
                        split[1].trim().replaceAll("\"", "");
                        nameLocationSplit = split[1].split("NEAR |ABOVE |AT |NR");

                        RiverLocation river = new RiverLocation(split[0].trim(), nameLocationSplit[0].trim().replaceAll("\"", ""), nameLocationSplit[1].trim(), split[2].trim().replaceAll("\"", ""));
                        //System.out.println(split[0].trim() + " " + nameLocationSplit[0].trim() + " " + nameLocationSplit[1].trim()+ " " + split[2].trim().replaceAll("\"", ""));
                        array.add(river);
                    }

                }
                line = buffRead.readLine();     //Get next Line
            }

        } catch (Exception e) {
            System.out.println("File not Found!");
        }
        Collections.sort(array);        //Sort the array
        return array;
    }
}
