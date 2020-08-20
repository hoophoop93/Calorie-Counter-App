package com.example.kamil.dyplomowa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.kamil.dyplomowa.models.Activity;
import com.example.kamil.dyplomowa.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil on 04.12.2016.
 */

public class DataBaseOperation {

    public Context context;
    public DataBaseHelper dataBaseHelper;
    public SQLiteDatabase sqLiteDatabase;

    public DataBaseOperation(Context context) {
        this.context = context;
        dataBaseHelper = new DataBaseHelper(context);
    }

    public List<Product> getListProduct(String nameTable) {
        Product product = null;
        List<Product> productList = new ArrayList<>();
        dataBaseHelper.openDatabase();
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + nameTable + " order by " + DatabaseContract.PRODUCT_COLUMN_NAME
                + " COLLATE NOCASE ASC", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            product = new Product(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3),
                    cursor.getDouble(4), cursor.getDouble(5), cursor.getDouble(6));
            productList.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        dataBaseHelper.closeDatabase();
        return productList;
    }

    public List<Product> getProductInfo(String nameProduct) {
        Product product = null;
        List<Product> productList = new ArrayList<>();
        dataBaseHelper.openDatabase();
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " +
                DatabaseContract.PRODUCT_ALL_TABLE_NAME + " where " +
                DatabaseContract.PRODUCT_COLUMN_NAME + "='" + nameProduct + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            product = new Product(
                    cursor.getInt(0), cursor.getString(1),
                    cursor.getDouble(2), cursor.getDouble(3),
                    cursor.getDouble(4), cursor.getDouble(5),
                    cursor.getDouble(6));
            productList.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        dataBaseHelper.closeDatabase();
        return productList;
    }

    public boolean searchProduct(String nameProduct, String tableName) {
        dataBaseHelper.openDatabase();
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT count(produkt) FROM " + tableName + " where produkt ='" + nameProduct + "'", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        System.out.println(count + "count");
        cursor.close();
        dataBaseHelper.closeDatabase();
        if ((count) > 0) {
            return true;
        } else {
            return false;

        }
    }

    public long saveProduct(Product product, String tableName) {
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.PRODUCT_COLUMN_NAME, product.getName());
        contentValues.put(DatabaseContract.PRODUCT_COLUMN_KCAL, product.getKcal());
        contentValues.put(DatabaseContract.PRODUCT_COLUMN_PROTEIN, product.getProtein());
        contentValues.put(DatabaseContract.PRODUCT_COLUMN_FAT, product.getFat());
        contentValues.put(DatabaseContract.PRODUCT_COLUMN_CARBS, product.getCarbs());
        contentValues.put(DatabaseContract.PRODUCT_COLUMN_FIBER, product.getFiber());

        return sqLiteDatabase.insert(tableName, null, contentValues);
    }

    public Cursor getProductListByKeyword(String search) {
        //Open connection to read only
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        String selectQuery = "SELECT rowid as " +
                DatabaseContract.PRODUCT_KEY_ROWID + "," +
                DatabaseContract.PRODUCT_COLUMN_ID + "," +
                DatabaseContract.PRODUCT_COLUMN_NAME + "," +
                DatabaseContract.PRODUCT_COLUMN_KCAL + "," +
                DatabaseContract.PRODUCT_COLUMN_PROTEIN + "," +
                DatabaseContract.PRODUCT_COLUMN_FAT + "," +
                DatabaseContract.PRODUCT_COLUMN_CARBS + "," +
                DatabaseContract.PRODUCT_COLUMN_FIBER +
                " FROM " + DatabaseContract.PRODUCT_ALL_TABLE_NAME +
                " WHERE " + DatabaseContract.PRODUCT_COLUMN_NAME
                + "  LIKE  '%" + search + "%' order by "+ DatabaseContract.PRODUCT_COLUMN_NAME  +
                " COLLATE NOCASE ASC";

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }

    public Cursor getProductList() {
        //Open connection to read only
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        String selectQuery = "SELECT rowid as " +
                DatabaseContract.PRODUCT_KEY_ROWID + "," +
                DatabaseContract.PRODUCT_COLUMN_ID + "," +
                DatabaseContract.PRODUCT_COLUMN_NAME + "," +
                DatabaseContract.PRODUCT_COLUMN_KCAL + "," +
                DatabaseContract.PRODUCT_COLUMN_PROTEIN + "," +
                DatabaseContract.PRODUCT_COLUMN_FAT + "," +
                DatabaseContract.PRODUCT_COLUMN_CARBS + "," +
                DatabaseContract.PRODUCT_COLUMN_FIBER +
                " FROM " + DatabaseContract.PRODUCT_ALL_TABLE_NAME
                + " order by " + DatabaseContract.PRODUCT_COLUMN_NAME +
                " COLLATE NOCASE ASC";


        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;

    }

    //Activity DataBaseOperation
    public long saveDataActivity(Activity activity) {
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.ACTIVITY_COLUMN_NAME, activity.getName());
        contentValues.put(DatabaseContract.ACTIVITY_DISTANCE, activity.getDistance());
        contentValues.put(DatabaseContract.ACTIVITY_TIME, String.valueOf(activity.getTime()));
        contentValues.put(DatabaseContract.ACTIVITY_DATE, String.valueOf(activity.getDate()));

        return sqLiteDatabase.insert(DatabaseContract.ACTIVITY_TABLE_NAME, null, contentValues);
    }

    public List<Activity> getActivityList() {
        Activity activity = null;
        List<Activity> activityList = new ArrayList<>();
        dataBaseHelper.openDatabase();
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM aktywnosc", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            activity = new Activity(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getString(3),
                    cursor.getString(4));
            activityList.add(activity);
            cursor.moveToNext();
        }
        cursor.close();
        dataBaseHelper.closeDatabase();
        return activityList;
    }

    public List<Activity> getAcitivityAfterDate(String date) {
        Activity activity = null;
        List<Activity> activityList = new ArrayList<>();
        dataBaseHelper.openDatabase();
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM aktywnosc where " + DatabaseContract.ACTIVITY_DATE + "='" + date + "'", null);
        System.out.println("SELECT * FROM aktywnosc where " + DatabaseContract.ACTIVITY_DATE + "='" + date + "'");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            activity = new Activity(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getString(3),
                    cursor.getString(4));
            activityList.add(activity);
            cursor.moveToNext();
        }
        cursor.close();
        dataBaseHelper.closeDatabase();
        return activityList;
    }

    public boolean searchActivity(String date) {
        dataBaseHelper.openDatabase();
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT count(nazwa) FROM aktywnosc where " + DatabaseContract.ACTIVITY_DATE + "='" + date + "'", null);
        System.out.println("SELECT count(nazwa) FROM aktywnosc where " + DatabaseContract.ACTIVITY_DATE + "='" + date + "'");
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        System.out.println(count + "count");
        cursor.close();
        dataBaseHelper.closeDatabase();
        if ((count) > 0) {
            return true;
        } else {
            return false;

        }
    }


}
