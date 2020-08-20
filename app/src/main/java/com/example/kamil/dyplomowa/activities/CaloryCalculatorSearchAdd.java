package com.example.kamil.dyplomowa.activities;


import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kamil.dyplomowa.R;
import com.example.kamil.dyplomowa.adapters.CustomAdapter;
import com.example.kamil.dyplomowa.database.DataBaseHelper;
import com.example.kamil.dyplomowa.database.DataBaseOperation;
import com.example.kamil.dyplomowa.database.DatabaseContract;

import java.io.File;

public class CaloryCalculatorSearchAdd extends AppCompatActivity {


    public static Context context;

    private CustomAdapter customAdapter;
    public static ListView listView;
    Cursor cursor;
    DataBaseOperation dataBaseOperation;
    DataBaseHelper dataBaseHelper;
    private final static String TAG = CaloryCalculatorSearchAdd.class.getName().toString();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calory_calculator_search_add);
        context = this;
        dataBaseOperation = new DataBaseOperation(this);
        dataBaseHelper = new DataBaseHelper(this);
        //Check exists database
        File database = getApplicationContext().getDatabasePath(DatabaseContract.DBNAME);
        if (false == database.exists()) {
            dataBaseHelper.getReadableDatabase();
            //Copy db
            if (dataBaseHelper.copyDataBase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        cursor = dataBaseOperation.getProductList();
        customAdapter = new CustomAdapter(this, cursor, 1, getSupportFragmentManager());
        listView = (ListView) findViewById(R.id.listViewAllProduct);
        listView.setAdapter(customAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options_menu, menu);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
            search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));


            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String s) {
                    Log.d(TAG, "onQueryTextSubmit ");
                    cursor = dataBaseOperation.getProductListByKeyword(s);
                    if (cursor == null) {
                        Toast.makeText(CaloryCalculatorSearchAdd.this, "Nie znaleziono rekordu!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(CaloryCalculatorSearchAdd.this, cursor.getCount() + "Znaleziono rekord!", Toast.LENGTH_LONG).show();
                    }
                    customAdapter.swapCursor(cursor);

                    return false;

                }

                @Override
                public boolean onQueryTextChange(String s) {
                    Log.d(TAG, "onQueryTextChange ");
                    cursor = dataBaseOperation.getProductListByKeyword(s);
                    if (cursor != null) {
                        customAdapter.swapCursor(cursor);
                    }
                    return false;
                }

            });

        }

        return true;

    }

}
