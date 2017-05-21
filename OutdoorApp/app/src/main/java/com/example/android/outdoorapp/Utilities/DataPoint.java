package com.example.android.outdoorapp.Utilities;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class DataPoint {
    private GregorianCalendar date;
    private double waterLevel;
    private double flow;

    public DataPoint(GregorianCalendar date, double waterLevel, double flow) {
        this.date = date;
        this.waterLevel = waterLevel;
        this.flow = flow;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public double getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(double waterLevel) {
        this.waterLevel = waterLevel;
    }

    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow) {
        this.flow = flow;
    }

    public String toString(){
        NumberFormat formatter = new DecimalFormat("#00.00");
        return format(date) + " - - - " + formatter.format(this.getWaterLevel()) + "ft" + " - - - " + this.getFlow() + "kcfs";
    }

    public static String format(GregorianCalendar calendar) {
        SimpleDateFormat fmt = new SimpleDateFormat("MMM dd yyyy - HH:mm");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        return dateFormatted;
    }

    public String getTimeString(){
        SimpleDateFormat fmt = new SimpleDateFormat("MM/dd-HH:mm");
        fmt.setCalendar(date);
        return fmt.format(date.getTime());
    }

}
