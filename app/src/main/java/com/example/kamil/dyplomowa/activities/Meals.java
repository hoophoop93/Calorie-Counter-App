package com.example.kamil.dyplomowa.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamil.dyplomowa.fragments.CaloryCalculatorAddMealDialog;
import com.example.kamil.dyplomowa.R;
import com.example.kamil.dyplomowa.utils.Singleton;
import com.example.kamil.dyplomowa.utils.Singleton2;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class Meals extends AppCompatActivity {

    public ListView listViewMeals;
    public Singleton2 singleton2;
    public Singleton singleton = new Singleton();
    ;
    public TextView kcal;
    public TextView protein;
    public TextView fat;
    public TextView fiber;
    public TextView carbs;
    public TextView sumKcalMeals;
    public TextView sumFatMeals;
    public TextView sumProteinMeals;
    public TextView sumCarbsMeals;
    public TextView sumFiberMeals;

    public TextView breakKcalSum;
    public TextView breakKcalSum1;
    public TextView breakKcalSum2;
    public TextView breakKcalSum3;
    public TextView breakKcalSum4;

    public static double sumKcal = 0;
    public static double sumProtein = 0;
    public static double sumFat = 0;
    public static double sumCarbs = 0;
    public static double sumFiber = 0;
    public double[] kcalTab = new double[20];
    public double[] fatTab = new double[20];
    public double[] proteinTab = new double[20];
    public double[] carbsTab = new double[20];
    public double[] fiberTab = new double[20];

    public TextView requiredTV;
    public TextView kcalOneMeals;
    public TextView titleRequired;
    public TextView breakTV;
    public ProgressBar progressBar;
    double sumKcalDouble;
    static int progressBarStatus = 0;
    public static int procentProgressBar = 0;

    public TextView procentValueCalory;
    public TextView procentCalory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);

        initUIelements();
        sumKcalMeals.setText("0");

        singleton2 = Singleton2.getInstance();
        if (singleton2.getListMeals() == null) {


        } else {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    R.layout.textview_meals,
                    singleton2.getListMeals());
            listViewMeals.setAdapter(arrayAdapter);

           // System.out.println("rozmiar listy posiłkow: " + singleton2.getListMeals().size());


            if (MainActivity.countClickHistoryMeals == 1) {
                for (int i = 0; i < singleton2.getListMeals().size(); i++) {
                    kcalTab[i] = Double.parseDouble(singleton2.getValue().get(i).get("kcal").replace(",", "."));
                    sumKcal += kcalTab[i];

                    fatTab[i] = Double.parseDouble(singleton2.getValue().get(i).get("fat").replace(",", "."));
                    sumFat += fatTab[i];

                    proteinTab[i] = Double.parseDouble(singleton2.getValue().get(i).get("protein").replace(",", "."));
                    sumProtein += proteinTab[i];

                    carbsTab[i] = Double.parseDouble(singleton2.getValue().get(i).get("carbs").replace(",", "."));
                    sumCarbs += carbsTab[i];

                    fiberTab[i] = Double.parseDouble(singleton2.getValue().get(i).get("fiber").replace(",", "."));
                    sumFiber += fiberTab[i];

                }
            }

            DecimalFormat df = new DecimalFormat("#.##");
            String sumKcalInfo = df.format(sumKcal);
            String sumProteinInfo = df.format(sumProtein);
            String sumFatInfo = df.format(sumFat);
            String sumCarbsInfo = df.format(sumCarbs);
            String sumFiberInfo = df.format(sumFiber);


            sumKcalMeals.setText(sumKcalInfo);
            sumFatMeals.setText(sumFatInfo);
            sumProteinMeals.setText(sumProteinInfo);
            sumFiberMeals.setText(sumFiberInfo);
            sumCarbsMeals.setText(sumCarbsInfo);


            if (listViewMeals.getAdapter().isEmpty()) {

                ArrayList<String> empty = new ArrayList<String>();
                empty.add("Brak");

                ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, R.layout.textview_meals, empty);
                listViewMeals.setAdapter(arrayAdapter2);
                listViewMeals.setEnabled(false);

                MainActivity.countClickHistoryMeals = 0;
            }

            listViewMeals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    System.out.println(position);

                    kcal.setText(singleton2.getValue().get(position).get("kcal"));
                    protein.setText(singleton2.getValue().get(position).get("protein"));
                    fat.setText(singleton2.getValue().get(position).get("fat"));
                    fiber.setText(singleton2.getValue().get(position).get("fiber"));
                    carbs.setText(singleton2.getValue().get(position).get("carbs"));
                }
            });

        }

        titleRequired.setVisibility(TextView.INVISIBLE);
        kcalOneMeals.setVisibility(TextView.INVISIBLE);
        requiredTV.setVisibility(TextView.INVISIBLE);
        breakTV.setVisibility(TextView.INVISIBLE);
        progressBar.setVisibility(TextView.INVISIBLE);
        procentValueCalory.setVisibility(TextView.INVISIBLE);
        procentCalory.setVisibility(TextView.INVISIBLE);

        if (singleton.getListRequired() == null || singleton2.getListMeals() == null) {
            requiredTV.setText("-");
            kcalOneMeals.setText("-");

        } else {
            if (singleton.getListRequired().size() == 1 && (singleton2.getListMeals().size() == 1 || singleton2.getListMeals().size() > 1)) {

                titleRequired.setVisibility(TextView.VISIBLE);
                kcalOneMeals.setVisibility(TextView.VISIBLE);
                requiredTV.setVisibility(TextView.VISIBLE);
                breakTV.setVisibility(TextView.VISIBLE);
                progressBar.setVisibility(TextView.VISIBLE);
                procentValueCalory.setVisibility(TextView.VISIBLE);
                procentCalory.setVisibility(TextView.VISIBLE);

                double value = singleton.getListRequired().get(0);
                Double adouble = new Double(value);
                java.text.DecimalFormat df = new java.text.DecimalFormat();
                df.setMaximumFractionDigits(2);
                kcalOneMeals.setText(sumKcalMeals.getText().toString());
                requiredTV.setText(df.format(adouble));

                progressBar.setProgress(0);
                progressBar.setMax((int) value);
                String sumKcal = sumKcalMeals.getText().toString();
                System.out.println(sumKcal);
                String newSumKcal = sumKcal.replace(",", ".");
                System.out.println(newSumKcal + "sumkcal");
                sumKcalDouble = Double.parseDouble(newSumKcal);

                final int totalProgress = progressBar.getMax();
                System.out.println("totalProgress" + totalProgress);

                System.out.println(progressBarStatus + " progress Barss status przed");
                progressBar.setProgress(progressBarStatus);
                procentValueCalory.setText(Integer.toString(procentProgressBar));

                if (CaloryCalculatorAddMealDialog.countAddMeals == 1) {
                    CaloryCalculatorAddMealDialog.countAddMeals = 0;
                    final Thread t = new Thread() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void run() {


                            if (progressBar.getProgress() <= totalProgress) {
                                try {
                                    progressBarStatus = (int)sumKcalDouble;

                                    progressBar.setProgress(progressBarStatus);
                                    procentProgressBar = (progressBarStatus*100)/ totalProgress;
                                    System.out.println(procentProgressBar+"procent progress");
                                    procentValueCalory.setText(Integer.toString(procentProgressBar));
                                    if(progressBarStatus >= totalProgress){
                                        Notification notification = new Notification.Builder(getApplicationContext())
                                                .setContentText("Twoje dzienne zapotrzebowanie kaloryczne zostało osiągnięte!")
                                                .setContentTitle("Info")
                                                .setSmallIcon(R.mipmap.logo_bez_tla)
                                                .build();

                                        NotificationManager notificationManager =
                                                (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                                        notificationManager.notify(1,notification);
                                    }
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        }

                    };
                    t.start();
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == android.R.id.home) {
            for (int i = 0; i < kcalTab.length; i++) {
                kcalTab[i] = 0;
                fatTab[i] = 0;
                proteinTab[i] = 0;
                carbsTab[i] = 0;
                fiberTab[i] = 0;
            }
        }
        if (id == R.id.clearAll) {

            System.out.println(singleton2.getListMeals().size() + "size lista");
            if (singleton2.getListMeals().size() == 0) {
                Toast.makeText(getApplicationContext(), "Brak posiłków do usunięcia", Toast.LENGTH_SHORT).show();

            } else {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("Czy na pewno chcesz usunąć posiłki?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Tak",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                sumKcal = 0;
                                sumProtein = 0;
                                sumFat = 0;
                                sumCarbs = 0;
                                sumFiber = 0;
                                procentProgressBar =0;
                                ArrayList<String> empty = new ArrayList<String>();
                                empty.add("Brak");

                                ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getApplicationContext(), R.layout.textview_meals, empty);
                                listViewMeals.setAdapter(arrayAdapter2);

                                kcal.setText("0");
                                protein.setText("0");
                                fat.setText("0");
                                carbs.setText("0");
                                fiber.setText("0");
                                sumKcalMeals.setText("0");
                                sumFatMeals.setText("0");
                                sumProteinMeals.setText("0");
                                sumFiberMeals.setText("0");
                                sumCarbsMeals.setText("0");
                                procentValueCalory.setVisibility(TextView.INVISIBLE);
                                procentCalory.setVisibility(TextView.INVISIBLE);
                                kcalOneMeals.setText("-");
                                requiredTV.setText("-");
                                progressBar.setProgress(0);
                                titleRequired.setVisibility(TextView.INVISIBLE);
                                kcalOneMeals.setVisibility(TextView.INVISIBLE);
                                requiredTV.setVisibility(TextView.INVISIBLE);
                                breakTV.setVisibility(TextView.INVISIBLE);
                                progressBar.setVisibility(TextView.INVISIBLE);
                                breakKcalSum.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
                                breakKcalSum1.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
                                breakKcalSum2.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
                                breakKcalSum3.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
                                breakKcalSum4.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
                                listViewMeals.setEnabled(false);

                                Singleton2.listValue.clear();
                                Singleton2.listMeals.clear();
                            }
                        });

                builder1.setNegativeButton(
                        "Nie",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }


    public void initUIelements() {
        listViewMeals = (ListView) findViewById(R.id.listMeals);
        kcal = (TextView) findViewById(R.id.kcalInfoMeals);
        protein = (TextView) findViewById(R.id.proteinInfoMeal);
        fat = (TextView) findViewById(R.id.fatInfoMeals);
        fiber = (TextView) findViewById(R.id.fiberInfoMeals);
        carbs = (TextView) findViewById(R.id.carbsInfoMeals);
        sumKcalMeals = (TextView) findViewById(R.id.sumKcalInfoMeals);
        sumFatMeals = (TextView) findViewById(R.id.fatTotalInfoMeals);
        sumProteinMeals = (TextView) findViewById(R.id.proteinTotalInfoMeal);
        sumFiberMeals = (TextView) findViewById(R.id.fiberTotalInfoMeals);
        sumCarbsMeals = (TextView) findViewById(R.id.carbsTotalInfoMeals);

        breakKcalSum = (TextView) findViewById(R.id.breakKcalSum);
        breakKcalSum1 = (TextView) findViewById(R.id.breakKcalSum2);
        breakKcalSum2 = (TextView) findViewById(R.id.breakKcalSum3);
        breakKcalSum3 = (TextView) findViewById(R.id.breakKcalSum4);
        breakKcalSum4 = (TextView) findViewById(R.id.breakKcalSum5);
        requiredTV = (TextView) findViewById(R.id.setRequired);
        progressBar = (ProgressBar) findViewById(R.id.progressBar3);
        kcalOneMeals = (TextView) findViewById(R.id.textView26);

        titleRequired = (TextView) findViewById(R.id.textView25);
        breakTV  = (TextView) findViewById(R.id.textView27);

        procentValueCalory = (TextView) findViewById(R.id.caloryProcentValue);
        procentCalory = (TextView) findViewById(R.id.caloryProcent);
    }
}
