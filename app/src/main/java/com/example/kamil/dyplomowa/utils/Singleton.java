package com.example.kamil.dyplomowa.utils;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.kamil.dyplomowa.database.DatabaseContract.Constants.FIRST_COLUMN;
import static com.example.kamil.dyplomowa.database.DatabaseContract.Constants.SECOND_COLUMN;

/**
 * Created by Kamil on 12.12.2016.
 */

public class Singleton {

    public static Singleton mInstance = null;
    private ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    public static ArrayList<Double> listRequired;


    public static Singleton getInstance() {
        if (mInstance == null) {
            mInstance = new Singleton();

        }
        return mInstance;
    }

    public ArrayList<Double> getListRequired() {
        return listRequired;
    }
    public void addRequired(double value) {

        listRequired = new ArrayList<Double>();
        listRequired.add(value);

    }

    public ArrayList<HashMap<String, String>> getList() {
        return list;
    }

    public void addElementList(String name,String grams) {

        HashMap<String, String> temp = new HashMap<String, String>();
        temp.put(FIRST_COLUMN, name);
        temp.put(SECOND_COLUMN, grams + " g");
        list.add(temp);
    }
}
