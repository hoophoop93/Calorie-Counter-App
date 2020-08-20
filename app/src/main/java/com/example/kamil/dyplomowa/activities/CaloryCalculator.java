package com.example.kamil.dyplomowa.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamil.dyplomowa.fragments.CaloryCalculatorAddMealDialog;
import com.example.kamil.dyplomowa.fragments.CaloryCalculatorSearchAddDialog;
import com.example.kamil.dyplomowa.R;
import com.example.kamil.dyplomowa.utils.Singleton;
import com.example.kamil.dyplomowa.adapters.ListViewAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;


public class CaloryCalculator extends AppCompatActivity {

    public static ListView listView;
    public ImageButton imageButton;
    public ImageButton addMeal;
    public static TextView kcal;
    public static TextView fat;
    public static TextView protein;
    public static TextView carbs;
    public static TextView fiber;
    public static ListViewAdapter adapter;
    public Singleton singleton;
    Context context;
    public static int count = 0;
    public static double sumKcal = 0;
    public static double sumProtein = 0;
    public static double sumFat = 0;
    public static double sumCarbs = 0;
    public static double sumFiber = 0;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calory_calculator);

        initUIelemetns();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorGreen)));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CaloryCalculatorSearchAdd.class);
                startActivity(intent);

            }
        });
        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(singleton.getList().size() == 0){
                    Toast.makeText(getApplicationContext(), "Nie można dodać posiłku - brak produktów", Toast.LENGTH_SHORT).show();
                }else {
                    openDialogFragment();
                }

            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                sumKcal = 0;
                sumProtein = 0;
                sumFat = 0;
                sumCarbs = 0;
                sumFiber = 0;
                singleton.getList().clear();
                adapter = new ListViewAdapter(CaloryCalculator.this, singleton.getList());
                listView.setAdapter(adapter);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        singleton = Singleton.getInstance();


        listView = (ListView) findViewById(R.id.caloryCaltulatorListView);



        for (int i = 0; i < singleton.getList().size(); i++) {
            System.out.println(singleton.getList().get(i));
        }


        adapter = new ListViewAdapter(this, singleton.getList(),getSupportFragmentManager());
        listView.setAdapter(adapter);


        double[] kcalTab = new double[100];
        double[] proteinTab = new double[100];
        double[] fatTab = new double[100];
        double[] carbsTab = new double[100];
        double[] fiberTab = new double[100];

        if (CaloryCalculatorSearchAddDialog.pressed >= 1) {

            System.out.println("sumkcal " + sumKcal);

            kcalTab[count] = CaloryCalculatorSearchAddDialog.kcalResult;
            sumKcal += kcalTab[count];

            proteinTab[count] = CaloryCalculatorSearchAddDialog.proteinResult;
            sumProtein += proteinTab[count];

            fatTab[count] = CaloryCalculatorSearchAddDialog.fatResult;
            sumFat += fatTab[count];

            carbsTab[count] = CaloryCalculatorSearchAddDialog.carbsResult;
            sumCarbs += carbsTab[count];

            fiberTab[count] = CaloryCalculatorSearchAddDialog.fiberResult;
            sumFiber += fiberTab[count];


            System.out.println("sum kacal po dodaniu " + sumKcal);
            System.out.println(kcalTab[count]);
            count++;


        }
        DecimalFormat df = new DecimalFormat("#.##");
        String kcalResultString = df.format(sumKcal);
        kcal.setText(kcalResultString);

        String proteinResultString = df.format(sumProtein);
        protein.setText(proteinResultString);

        String fatResultString = df.format(sumFat);
        fat.setText(fatResultString);

        String carbsResultString = df.format(sumCarbs);
        carbs.setText(carbsResultString);

        String fiberResultString = df.format(sumFiber);
        fiber.setText(fiberResultString);


    }

    public void initUIelemetns() {
        kcal = (TextView) findViewById(R.id.kcalInfoCC);
        fat = (TextView) findViewById(R.id.fatInfoCC);
        protein = (TextView) findViewById(R.id.proteinInfoCC);
        carbs = (TextView) findViewById(R.id.carbsInfoCC);
        fiber = (TextView) findViewById(R.id.fiberInfoCC);
        imageButton = (ImageButton) findViewById(R.id.imageButtonHome);
        addMeal = (ImageButton) findViewById(R.id.addMeal);

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        count = 0;
        sumKcal = 0;
        sumProtein = 0;
        sumFat = 0;
        sumCarbs = 0;
        sumFiber = 0;
        singleton.getList().clear();
        adapter = new ListViewAdapter(CaloryCalculator.this, singleton.getList());
        listView.setAdapter(adapter);
        Intent intent = new Intent(getApplicationContext(), Calculators.class);
        startActivity(intent);
        return true;
    }

    public void openDialogFragment() {


        CaloryCalculatorAddMealDialog myDialog = new CaloryCalculatorAddMealDialog();
        myDialog.show(getSupportFragmentManager(), "Tag");


    }
}
