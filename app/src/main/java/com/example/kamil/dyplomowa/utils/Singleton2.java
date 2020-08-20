package com.example.kamil.dyplomowa.utils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kamil on 29.12.2016.
 */

public class Singleton2 {

    public static Singleton2 mInstance = null;
    public static ArrayList<String> listMeals = new ArrayList< String>();
    public static ArrayList<HashMap<String, String>> listValue = new ArrayList<HashMap<String, String>>();
    public static HashMap<String, String> temp;


    public static Singleton2 getInstance() {
        if (mInstance == null) {
            mInstance = new Singleton2();

        }
        return mInstance;
    }

    public ArrayList<String> getListMeals() {
        return listMeals;
    }

    public void addMeals(String name) {

        listMeals.add(name);

    }

    public ArrayList<HashMap<String, String>> getValue() {
        return listValue;
    }

    public void addValue(String kcal,String protein,String fat,String carbs,String fiber) {

        temp = new HashMap<String, String>();
        temp.put("kcal", kcal);
        temp.put("protein", protein);
        temp.put("fat", fat);
        temp.put("carbs", carbs);
        temp.put("fiber", fiber);
        listValue.add(temp);

    }
}
