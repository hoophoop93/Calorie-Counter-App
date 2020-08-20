package com.example.kamil.dyplomowa.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamil.dyplomowa.R;
import com.example.kamil.dyplomowa.database.DataBaseHelper;
import com.example.kamil.dyplomowa.database.DataBaseOperation;
import com.example.kamil.dyplomowa.database.DatabaseContract;
import com.example.kamil.dyplomowa.models.Product;

import java.io.File;

public class AddProduct extends AppCompatActivity {

    public Context context;
    public EditText nameET;
    public EditText kcalET;
    public EditText proteinET;
    public EditText fatET;
    public EditText carbsET;
    public EditText fiberET;
    public Button saveProduct;
    public RelativeLayout activityLayout;
    String received;
    public TextView infoAdding;
    DataBaseHelper dataBaseHelper;
    String nameTable;
    long result;
    DataBaseOperation dataBaseOperation = null;
    Product product = new Product();
    public String tableName[] = {"Produkty zbożowe", "Produkty mleczne", "Alkohole", "Grzyby", "Jaja", "Mięsa", "Orzechy",
            "Owoce", "Przyprawy", "Ryby", "Slodycze", "Napoje", "Tłuszcze", "Warzywa", "Wędliny"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        Intent intent = getIntent();
        received = intent.getStringExtra("tableName");
        initUIelements();

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

        nameET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        saveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nameTableForAllProduct = "wszystkie";

                if (nameET.getText().toString().equals("") || kcalET.getText().toString().equals("") || proteinET.getText().toString().equals("")
                        || fatET.getText().toString().equals("") || carbsET.getText().toString().equals("") || fiberET.getText().toString().equals("")) {
                    validateFields();
                } else {
                    if (!"".equals(nameET.getText().toString())) {
                        String getNameProduct = nameET.getText().toString();
                        String nameProduct = getNameProduct.substring(0, 1).toUpperCase() + getNameProduct.substring(1);
                        if (dataBaseOperation.searchProduct(nameProduct, received) == true) {
                            Toast.makeText(getApplicationContext(), "Taki produkt już jest w bazie", Toast.LENGTH_SHORT).show();
                            //ageET.requestFocus();
                            nameET.setError("Podaj inną nazwe");
                            return;
                        } else {
                            nameET.setError(null);
                            setProductDataModel();
                            result = dataBaseOperation.saveProduct(product, received);
                            dataBaseOperation.saveProduct(product, nameTableForAllProduct);
                            Log.d("result", Long.toString(result));
                            searchWhetherAdd();

                            nameET.setText(null);
                            kcalET.setText(null);
                            proteinET.setText(null);
                            fatET.setText(null);
                            carbsET.setText(null);
                            fiberET.setText(null);


                        }
                    }
                }
            }
        });

        //hide soft keyboard after clicking activity Layout
        activityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            }
        });

    }


    public void setProductDataModel() {

        String getNameProduct = nameET.getText().toString();

        String nameProduct = getNameProduct.substring(0, 1).toUpperCase() + getNameProduct.substring(1);
        System.out.println(nameProduct);

        product.setName(nameProduct);
        product.setKcal((Double.parseDouble(kcalET.getText().toString())));
        product.setProtein(Double.parseDouble(proteinET.getText().toString()));
        product.setFat(Double.parseDouble(fatET.getText().toString()));
        product.setCarbs(Double.parseDouble(carbsET.getText().toString()));
        product.setFiber(Double.parseDouble(fiberET.getText().toString()));
    }

    public void searchWhetherAdd() {
        if (received.equals("zbozowe")) {
            nameTable = tableName[0];
        }
        if (received.equals("mleczne")) {
            nameTable = tableName[1];
        }
        if (received.equals("alkohole")) {
            nameTable = tableName[2];
        }
        if (received.equals("grzyby")) {
            nameTable = tableName[3];
        }
        if (received.equals("jaja")) {
            nameTable = tableName[4];
        }
        if (received.equals("mieso")) {
            nameTable = tableName[5];
        }
        if (received.equals("orzechy")) {
            nameTable = tableName[6];
        }
        if (received.equals("owoce")) {
            nameTable = tableName[7];
        }
        if (received.equals("przyprawy")) {
            nameTable = tableName[8];
        }
        if (received.equals("ryby")) {
            nameTable = tableName[9];
        }
        if (received.equals("slodycze")) {
            nameTable = tableName[10];
        }
        if (received.equals("napoje")) {
            nameTable = tableName[11];
        }
        if (received.equals("tluszcze")) {
            nameTable = tableName[12];
        }
        if (received.equals("warzywa")) {
            nameTable = tableName[13];
        }
        if (received.equals("wedliny")) {
            nameTable = tableName[14];
        }

        if (result > 0) {
            infoAdding.setText("Produkt '" + nameET.getText() + "' został dodany do tabeli '" + nameTable + "'");
            infoAdding.postDelayed(new Runnable() {
                public void run() {
                    infoAdding.setVisibility(View.INVISIBLE);
                }
            }, 4000);
        } else {
            infoAdding.setText("Produkt nie został dodany.Spróbuj ponownie");
            infoAdding.postDelayed(new Runnable() {
                public void run() {
                    infoAdding.setVisibility(View.INVISIBLE);
                }
            }, 4000);
        }

    }

    public void validateFields() {


        if ("".equals(nameET.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Podaj nazwe", Toast.LENGTH_SHORT).show();
            //ageET.requestFocus();
            nameET.setError("Podaj nazwe");
            return;
        }
        if ("".equals(kcalET.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Podaj kalorie", Toast.LENGTH_SHORT).show();
            //heightET.requestFocus();

            kcalET.setError("Podaj kalorie");
            return;
        }
        if ("".equals(proteinET.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Podaj wartość białka", Toast.LENGTH_SHORT).show();
            //weightET.requestFocus();
            proteinET.setError("Podaj wartość białka");
            return;
        }
        if ("".equals(fatET.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Podaj wartość tłuszczu", Toast.LENGTH_SHORT).show();
            //ageET.requestFocus();
            fatET.setError("Podaj wartość tłuszczu");
            return;
        }
        if ("".equals(carbsET.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Podaj wartość węgl.", Toast.LENGTH_SHORT).show();
            //heightET.requestFocus();

            carbsET.setError("Podaj wartość węgl.");
            return;
        }
        if ("".equals(fiberET.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Podaj wartość błonnika", Toast.LENGTH_SHORT).show();
            //weightET.requestFocus();
            fiberET.setError("Podaj wartość błonnika");
            return;
        }
    }

    public void initUIelements() {
        nameET = (EditText) findViewById(R.id.productName);
        kcalET = (EditText) findViewById(R.id.productKcal);
        proteinET = (EditText) findViewById(R.id.productProtein);
        fatET = (EditText) findViewById(R.id.productFat);
        carbsET = (EditText) findViewById(R.id.productCarbs);
        fiberET = (EditText) findViewById(R.id.productFiber);
        infoAdding = (TextView) findViewById(R.id.infoAdding);
        saveProduct = (Button) findViewById(R.id.addProductButton);
        activityLayout = (RelativeLayout) findViewById(R.id.activity_add_product2);
    }

    /**
     * Created by Kamil on 09.12.2016.
     */

}
