package com.example.kamil.dyplomowa.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.kamil.dyplomowa.R;
import com.example.kamil.dyplomowa.adapters.ListViewAdapter;
import com.example.kamil.dyplomowa.database.DataBaseHelper;
import com.example.kamil.dyplomowa.database.DataBaseOperation;
import com.example.kamil.dyplomowa.database.DatabaseContract;
import com.example.kamil.dyplomowa.models.Product;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Kamil on 22.12.2016.
 */

public class CaloryCalculatorDialog extends DialogFragment {

    public View rootView;
    public Button cancel;
    public TextView nameTV;
    public TextView howGramsTV;
    public TextView kcalTV;
    public TextView fatTV;
    public TextView proteinTV;
    public TextView carbsTV;
    public TextView fiberTV;
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
    private DataBaseOperation dataBaseOperation;
    private DataBaseHelper dataBaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.calory_calculator_dialog_item, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        initUIelements();

        nameTV.setText(ListViewAdapter.nameProduct);
        howGramsTV.setText(ListViewAdapter.grams+" g");

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
        List<Product> productInfo = dataBaseOperation.getProductInfo(ListViewAdapter.nameProduct);

        kcal = productInfo.get(0).getKcal();
        protein = productInfo.get(0).getProtein();
        fat = productInfo.get(0).getFat();
        carbs = productInfo.get(0).getCarbs();
        fiber = productInfo.get(0).getFiber();

        DecimalFormat df = new DecimalFormat("#.##");

        kcalResult = kcal * Double.parseDouble(ListViewAdapter.grams) / 100;
        String kcalResultString = df.format(kcalResult);

        proteinResult = protein * Double.parseDouble(ListViewAdapter.grams) / 100;
        String proteinResultString = df.format(proteinResult);

        fatResult = fat * Double.parseDouble(ListViewAdapter.grams) / 100;
        String fatResultString = df.format(fatResult);

        carbsResult = carbs * Double.parseDouble(ListViewAdapter.grams) / 100;
        String carbsResultString = df.format(carbsResult);

        fiberResult = fiber * Double.parseDouble(ListViewAdapter.grams) / 100;
        String fiberResultString = df.format(fiberResult);

        kcalTV.setText(kcalResultString);
        proteinTV.setText(proteinResultString);
        fatTV.setText(fatResultString);
        carbsTV.setText(carbsResultString);
        fiberTV.setText(fiberResultString);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });



        return rootView;
    }

    public void initUIelements(){
        cancel = (Button) rootView.findViewById(R.id.cancelCaloryCalculator);
        nameTV = (TextView) rootView.findViewById(R.id.nameCaloryCalculatorDialog);
        kcalTV = (TextView) rootView.findViewById(R.id.kcalInfoCaloryCalculator);
        fatTV = (TextView) rootView.findViewById(R.id.fatInfoCaloryCalculator);
        proteinTV = (TextView) rootView.findViewById(R.id.proteinInfoCaloryCalculator);
        carbsTV = (TextView) rootView.findViewById(R.id.carbsInfoCaloryCalculator);
        fiberTV = (TextView) rootView.findViewById(R.id.fiberInfoCaloryCalculator);
        howGramsTV = (TextView) rootView.findViewById(R.id.howMuchCaloryCalculator);
    }
}
