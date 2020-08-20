package com.example.kamil.dyplomowa.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kamil.dyplomowa.R;
import com.example.kamil.dyplomowa.activities.CaloryCalculator;
import com.example.kamil.dyplomowa.adapters.CustomAdapter;
import com.example.kamil.dyplomowa.database.DataBaseHelper;
import com.example.kamil.dyplomowa.database.DataBaseOperation;
import com.example.kamil.dyplomowa.database.DatabaseContract;
import com.example.kamil.dyplomowa.models.Product;
import com.example.kamil.dyplomowa.utils.Singleton;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Kamil on 06.12.2016.
 */

public class CaloryCalculatorSearchAddDialog extends DialogFragment {

    public Context context;
    public TextView dialogTV;
    public Button exit;
    public static Button addProduct;
    private DataBaseOperation dataBaseOperation;
    private List<Product> productList;
    private DataBaseHelper dataBaseHelper;
    public View rootView;
    public TextView kcalTV, proteinTV, fatTV, fiberTV, carbsTV;
    public static EditText howGrams;
    double kcal;
    double protein;
    double fat;
    double carbs;
    double fiber;
    public static double kcalResult;
    public static double proteinResult;
    public static double fatResult;
    public static double carbsResult;
    public static double fiberResult;
    public static int pressed = 0;

    public static List<String> productNameList;
    public ArrayList<HashMap<String, String>> list;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_item, container, false);
        getDialog().setTitle("Dodaj produkt");
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        //Initialize
        initUIelements();


        dialogTV.setText(CustomAdapter.itemListViewName);

        dataBaseOperation = new DataBaseOperation(getContext());
        dataBaseHelper = new DataBaseHelper(getContext());
        //Check exists database
        File database = getContext().getDatabasePath(DatabaseContract.DBNAME);
        if (false == database.exists()) {
            dataBaseHelper.getReadableDatabase();
            //Copy db
            if (dataBaseHelper.copyDataBase(getContext())) {

            } else {

            }
        }
        List<Product> productInfo = dataBaseOperation.getProductInfo(CustomAdapter.itemListViewName);

        kcal = productInfo.get(0).getKcal();
        protein = productInfo.get(0).getProtein();
        fat = productInfo.get(0).getFat();
        carbs = productInfo.get(0).getCarbs();
        fiber = productInfo.get(0).getFiber();


        howGrams.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (howGrams.getText().toString().equals("")) {

                } else {
                    DecimalFormat df = new DecimalFormat("#.##");

                    kcalResult = kcal * Double.parseDouble(howGrams.getText().toString()) / 100;
                    String kcalResultString = df.format(kcalResult);

                    proteinResult = protein * Double.parseDouble(howGrams.getText().toString()) / 100;
                    String proteinResultString = df.format(proteinResult);

                    fatResult = fat * Double.parseDouble(howGrams.getText().toString()) / 100;
                    String fatResultString = df.format(fatResult);

                    carbsResult = carbs * Double.parseDouble(howGrams.getText().toString()) / 100;
                    String carbsResultString = df.format(carbsResult);

                    fiberResult = fiber * Double.parseDouble(howGrams.getText().toString()) / 100;
                    String fiberResultString = df.format(fiberResult);

                    kcalTV.setText(kcalResultString);
                    proteinTV.setText(proteinResultString);
                    fatTV.setText(fatResultString);
                    carbsTV.setText(carbsResultString);
                    fiberTV.setText(fiberResultString);
                }
                if (howGrams.getText().toString().equals("")) {
                    kcalTV.setText("0");
                    proteinTV.setText("0");
                    fatTV.setText("0");
                    carbsTV.setText("0");
                    fiberTV.setText("0");
                }
                return false;
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (howGrams.getText().toString().equals("")) {
                    howGrams.setError("Podaj gramy");
                } else {
                    pressed++;
                    System.out.println("pressed " + pressed);
                    Intent intent = new Intent(getActivity(), CaloryCalculator.class);
                    startActivity(intent);
                    productNameList = new ArrayList<>();
                    productNameList.add(CustomAdapter.itemListViewName);

                    Singleton singleton = Singleton.getInstance();


                    singleton.addElementList(productNameList.get(0), howGrams.getText().toString());


                }
            }
        });

        return rootView;
    }

    public void initUIelements() {

        exit = (Button) rootView.findViewById(R.id.cancelButtonDialog);
        addProduct = (Button) rootView.findViewById(R.id.addButtonDialog);
        dialogTV = (TextView) rootView.findViewById(R.id.nameProductDialog);
        kcalTV = (TextView) rootView.findViewById(R.id.kcalInfo);
        proteinTV = (TextView) rootView.findViewById(R.id.proteinInfo);
        fatTV = (TextView) rootView.findViewById(R.id.fatInfo);
        carbsTV = (TextView) rootView.findViewById(R.id.carbsInfo);
        fiberTV = (TextView) rootView.findViewById(R.id.fiberInfo);
        howGrams = (EditText) rootView.findViewById(R.id.howMuchET);

    }

}
