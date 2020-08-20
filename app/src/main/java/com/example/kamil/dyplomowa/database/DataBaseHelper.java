package com.example.kamil.dyplomowa.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static com.example.kamil.dyplomowa.database.DatabaseContract.*;

/**
 * Created by Kamil on 02.12.2016.
 */

public class DataBaseHelper extends SQLiteOpenHelper {


    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DataBaseHelper(Context context) {
        super(context, DBNAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = context.getDatabasePath(DBNAME).getPath();
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            return;
        }
        sqLiteDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
        }
    }
    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    public boolean copyDataBase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DBNAME);
            String pathToNewDataBase = DBLOCATION + DBNAME;
            OutputStream outputStream = new FileOutputStream(pathToNewDataBase);
            byte[]buffer = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("Info","DataBase copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

