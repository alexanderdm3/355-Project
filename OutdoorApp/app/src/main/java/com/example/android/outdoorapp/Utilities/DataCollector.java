package com.example.android.outdoorapp.Utilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class DataCollector {
    private static ArrayList<DataPoint> dataPoints = new ArrayList<DataPoint>();
    private static ArrayList<DataPoint> forecast = new ArrayList<DataPoint>();
    private static boolean isHtml;
    private static String url;

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        DataCollector.url = url;
    }

    public DataCollector(String URL){
        this.url = URL;
    }

    public static void collectData(){
        boolean isObserved = true;
        int counter = 0;
        String level;
        String flow;
        StringBuilder collector = new StringBuilder();
        try{
            URL url = new URL(getUrl());
            Scanner scan = new Scanner(url.openStream());

            while(scan.hasNextLine()){
                StringBuilder strbld = new StringBuilder();
                String line = scan.nextLine();
                if (line.contains("Forecast  Data")){
                    isObserved = false;
                };
                for (int i = 0; i < line.length(); i++){

                    if (line.charAt(i) == '<' || line.charAt(i) == '{'){
                        isHtml = true;
                    }

                    if (line.charAt(i) == '>' || line.charAt(i) == '}'){
                        isHtml = false;
                        continue;
                    }

                    if (!isHtml){
                        strbld.append(line.charAt(i));
                    }


                }
                if (!(strbld.toString().trim().isEmpty()) && Character.isDigit(strbld.toString().trim().charAt(0))){
                    counter++;


                    if (counter <= 3){
                        collector.append(strbld.toString().trim() + ",");
                    }
                    if (counter == 3){
                        String[] splitStr = collector.toString().split(",");
                        counter = 0;
                        String[] dateTimeSplit = splitStr[0].split("\\s");   //Splits the date from the time
                        String[] dateSplit = dateTimeSplit[0].split("/");   //Splits the month from the day
                        String[] timeSplit = dateTimeSplit[1].split(":");  //Splits the hour from the minute
                        level = splitStr[1].substring(0, splitStr[1].length()-2); //Removes the 'ft' from level
                        flow = splitStr[2].substring(0, splitStr[2].length()-4); //Removes the 'kcfs' from flow

                        Calendar now = Calendar.getInstance();
                        int year = now.get(Calendar.YEAR);

                        GregorianCalendar date = new GregorianCalendar(year, Integer.parseInt(dateSplit[0]) - 1, Integer.parseInt(dateSplit[1]), Integer.parseInt(timeSplit[0]), Integer.parseInt(timeSplit[1]));

                        if (isObserved){
                            dataPoints.add(new DataPoint(date, Double.parseDouble(level), Double.parseDouble(flow)));
                        }else {
                            forecast.add(new DataPoint(date, Double.parseDouble(level), Double.parseDouble(flow)));
                        }


                        collector.delete(0, collector.length());
                    }
                }
            }

        }catch(MalformedURLException e){System.out.println(e);}
        catch(IOException i){};

    }

    public ArrayList getList(){
        return dataPoints;
    }

    public ArrayList getForecastList(){
        return forecast;
    }

}
