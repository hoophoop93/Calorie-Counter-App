package com.example.kamil.dyplomowa.database;

/**
 * Created by Kamil on 04.12.2016.
 */

public class DatabaseContract {


    public static final String DBNAME = "produkty.sqlite";
    public static final String DBLOCATION = "/data/data/com.example.kamil.dyplomowa/databases/";
    public static final int DATABASE_VERSION = 1;
    public static final String PRODUCT_KEY_ROWID = "_id";
    public static final String PRODUCT_COLUMN_ID = "ID";
    public static final String PRODUCT_COLUMN_NAME = "produkt";
    public static final String PRODUCT_COLUMN_KCAL = "kalorycznosc";
    public static final String PRODUCT_COLUMN_PROTEIN = "bialko";
    public static final String PRODUCT_COLUMN_FAT = "tluszcz";
    public static final String PRODUCT_COLUMN_CARBS = "weglowodany";
    public static final String PRODUCT_COLUMN_FIBER = "blonnik";
    public static final String PRODUCT_ALL_TABLE_NAME = "wszystkie";

    //Activity table
    public static final String ACTIVITY_COLUMN_ID = "ID";
    public static final String ACTIVITY_DISTANCE = "dystans";
    public static final String ACTIVITY_COLUMN_NAME = "nazwa";
    public static final String ACTIVITY_TIME = "czas";
    public static final String ACTIVITY_DATE = "data";
    public static final String ACTIVITY_TABLE_NAME ="aktywnosc";


    public static class Constants {

        public static final String FIRST_COLUMN = "First";
        public static final String SECOND_COLUMN = "Second";
    }


}
