package com.example.kamil.dyplomowa.models;


/**
 * Created by Kamil on 14.12.2016.
 */

public class Activity {

    public int ID;
    public String name;
    public double distance;
    public String time;
    public String date;

    public Activity(int ID, String name, double distance, String time, String date) {
        this.ID = ID;
        this.name = name;
        this.distance = distance;
        this.time = time;
        this.date = date;
    }
    public Activity(){

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

