package com.example.kamil.dyplomowa.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kamil.dyplomowa.R;
import com.example.kamil.dyplomowa.activities.CaloryCalculator;
import com.example.kamil.dyplomowa.activities.MainActivity;
import com.example.kamil.dyplomowa.activities.Meals;
import com.example.kamil.dyplomowa.utils.Singleton2;

import java.text.DecimalFormat;

/**
 * Created by Kamil on 29.12.2016.
 */

public class CaloryCalculatorAddMealDialog extends DialogFragment {

    public View rootView;
    public Button saveMeal;
    public Button exitDialog;
    public EditText nameMeal;

    public static double sumKcalMeal = 0;
    public static double sumProteinMeal = 0;
    public static double sumFatMeal = 0;
    public static double sumCarbsMeal = 0;
    public static double sumFiberMeal = 0;
    public static int countAddMeals = 0 ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.calory_calculator_add_meal_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        initUIelements();

        nameMeal.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        exitDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        saveMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(nameMeal.getText().toString().equals("")){
                    nameMeal.setError("Wpisz posiłek");

                }else {

                    countAddMeals++;
                    MainActivity.countClickHistoryMeals=0;
                    Meals.sumKcal=0;
                    String name = nameMeal.getText().toString();
                    Singleton2 singleton2 = Singleton2.getInstance();

                    singleton2.addMeals(name);
                    sumKcalMeal = CaloryCalculator.sumKcal;
                    sumProteinMeal = CaloryCalculator.sumProtein;
                    sumFatMeal = CaloryCalculator.sumFat;
                    sumCarbsMeal = CaloryCalculator.sumCarbs;
                    sumFiberMeal = CaloryCalculator.sumFiber;


                    System.out.println(sumKcalMeal + " " + sumProteinMeal + " " + sumFatMeal + " " + sumCarbsMeal + " " + sumFiberMeal);

                    DecimalFormat df = new DecimalFormat("#.##");
                    String kcalResultString = df.format(sumKcalMeal);
                    String proteinResultString = df.format(sumProteinMeal);
                    String fatResultString = df.format(sumFatMeal);
                    String carbsResultString = df.format(sumCarbsMeal);
                    String fiberResultString = df.format(sumFiberMeal);

                    singleton2.addValue(kcalResultString, proteinResultString, fatResultString, carbsResultString, fiberResultString);


                    for (int i = 0; i < singleton2.getValue().size(); i++) {
                        System.out.println(singleton2.getValue().get(i));
                    }


                    for (int i = 0; i < singleton2.getListMeals().size(); i++) {
                        System.out.println(singleton2.getListMeals().get(i));
                    }

                    Toast.makeText(getActivity(), "Dodano do posiłków", Toast.LENGTH_SHORT).show();
                    getDialog().dismiss();
                }

            }
        });

        return rootView;
    }

    public void initUIelements() {

        saveMeal = (Button) rootView.findViewById(R.id.addMealButton);
        exitDialog = (Button) rootView.findViewById(R.id.exitDialog);
        nameMeal = (EditText) rootView.findViewById(R.id.nameMeal);
    }
}
