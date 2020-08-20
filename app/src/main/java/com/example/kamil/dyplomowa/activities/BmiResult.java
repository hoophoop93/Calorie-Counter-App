package com.example.kamil.dyplomowa.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.kamil.dyplomowa.R;

import org.w3c.dom.Text;

public class BmiResult extends AppCompatActivity {

    private TextView showData;
    private TextView showBmi;
    private TextView showRatingBmi;
    private double BMI;

    //rating scale 1
    private TextView color1;
    private TextView rating1;
    private TextView rating11;
    //rating scale 2
    private TextView color2;
    private TextView rating2;
    private TextView rating22;
    //rating scale 3
    private TextView color3;
    private TextView rating3;
    private TextView rating33;
    //rating scale 4
    private TextView color4;
    private TextView rating4;
    private TextView rating44;
    //rating scale 5
    private TextView color5;
    private TextView rating5;
    private TextView rating55;
    //rating scale 6
    private TextView color6;
    private TextView rating6;
    private TextView rating66;
    //rating scale 7
    private TextView color7;
    private TextView rating7;
    private TextView rating77;
    //rating scale 8
    private TextView color8;
    private TextView rating8;
    private TextView rating88;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_result);

        initUIelements();

        Intent intent = getIntent();
        String received = intent.getStringExtra("info1");
        String received2 = intent.getStringExtra("info2");
        BMI = intent.getDoubleExtra("info3", 1);

        showBmi.setText(received2);
        showData.setText(received);

        ratingBMI();
        showRatingInfo();
    }

    public void ratingBMI() {
        if (BMI == 0) {

        } else {
            if (BMI <= 16.0) {
                showRatingBmi.setText("Wygłodzenie");
                showRatingBmi.setTextColor(getResources().getColor(R.color.dark_blue));

                rating1.setTypeface(null, Typeface.BOLD);
                rating1.setTextSize(15);
                rating11.setTypeface(null, Typeface.BOLD);
                rating11.setTextSize(16);
            }
            if (BMI > 16 && BMI < 17.0) {
                showRatingBmi.setText("Wychudzenie");
                showRatingBmi.setTextColor(getResources().getColor(R.color.blue));
                rating2.setTextSize(15);
                rating2.setTypeface(null, Typeface.BOLD);
                rating22.setTextSize(15);
                rating22.setTypeface(null, Typeface.BOLD);
            }
            if (BMI >= 17.0 && BMI < 18.5) {
                showRatingBmi.setText("Niedowaga");
                showRatingBmi.setTextColor(getResources().getColor(R.color.light_blue));
                rating3.setTypeface(null, Typeface.BOLD);
                rating3.setTextSize(15);
                rating33.setTypeface(null, Typeface.BOLD);
                rating33.setTextSize(15);
            }
            if (BMI >= 18.5 && BMI < 25.0) {
                showRatingBmi.setText("Wartość prawidłowa");
                showRatingBmi.setTextColor(getResources().getColor(R.color.colorGreen));
                rating4.setTypeface(null, Typeface.BOLD);
                rating4.setTextSize(15);
                rating44.setTypeface(null, Typeface.BOLD);
                rating44.setTextSize(15);
            }
            if (BMI >= 25.0 && BMI < 30.0) {
                showRatingBmi.setText("Nadwaga");
                showRatingBmi.setTextColor(getResources().getColor(R.color.yellow));
                rating5.setTypeface(null, Typeface.BOLD);
                rating5.setTextSize(15);
                rating55.setTypeface(null, Typeface.BOLD);
                rating55.setTextSize(15);
            }
            if (BMI >= 30.0 && BMI < 35.0) {
                showRatingBmi.setText("I stopień otyłości");
                showRatingBmi.setTextColor(getResources().getColor(R.color.orange));
                rating6.setTypeface(null, Typeface.BOLD);
                rating6.setTextSize(15);
                rating66.setTypeface(null, Typeface.BOLD);
                rating66.setTextSize(15);
            }
            if (BMI >= 35.0 && BMI < 40.0) {
                showRatingBmi.setText("II stopień otyłości");
                showRatingBmi.setTextColor(getResources().getColor(R.color.light_red));
                rating7.setTypeface(null, Typeface.BOLD);
                rating7.setTextSize(15);
                rating77.setTypeface(null, Typeface.BOLD);
                rating77.setTextSize(15);
            }
            if (BMI > 40.0) {
                showRatingBmi.setText("III stopień otyłości(otyłość skrajna)");
                showRatingBmi.setTextColor(getResources().getColor(R.color.dark_red));
                rating8.setTypeface(null, Typeface.BOLD);
                rating8.setTextSize(15);
                rating88.setTypeface(null, Typeface.BOLD);
                rating88.setTextSize(15);
            }
        }
    }

    public void initUIelements() {
        showData = (TextView) findViewById(R.id.showData);
        showBmi = (TextView) findViewById(R.id.showBmi);
        showRatingBmi = (TextView) findViewById(R.id.showRatingBmi);

        //showRatingInfo
        rating1 = (TextView) findViewById(R.id.rating1);
        rating11 = (TextView) findViewById(R.id.rating11);
        color1 = (TextView) findViewById(R.id.color1);

        rating2 = (TextView) findViewById(R.id.rating2);
        rating22 = (TextView) findViewById(R.id.rating22);
        color2 = (TextView) findViewById(R.id.color2);

        rating3 = (TextView) findViewById(R.id.rating3);
        rating33 = (TextView) findViewById(R.id.rating33);
        color3 = (TextView) findViewById(R.id.color3);

        rating4 = (TextView) findViewById(R.id.rating4);
        rating44 = (TextView) findViewById(R.id.rating44);
        color4 = (TextView) findViewById(R.id.color4);

        rating5 = (TextView) findViewById(R.id.rating5);
        rating55 = (TextView) findViewById(R.id.rating55);
        color5 = (TextView) findViewById(R.id.color5);

        rating6 = (TextView) findViewById(R.id.rating6);
        rating66 = (TextView) findViewById(R.id.rating66);
        color6 = (TextView) findViewById(R.id.color6);

        rating7 = (TextView) findViewById(R.id.rating7);
        rating77 = (TextView) findViewById(R.id.rating77);
        color7 = (TextView) findViewById(R.id.color7);

        rating8 = (TextView) findViewById(R.id.rating8);
        rating88 = (TextView) findViewById(R.id.rating88);
        color8 = (TextView) findViewById(R.id.color8);


    }

    public void showRatingInfo() {
        rating1.setText("Wygłodzenie");
        rating11.setText("<= 16.0");

        rating2.setText("Wychudzenie");
        rating22.setText("16.0 - 17.0");

        rating3.setText("Niedowaga");
        rating33.setText("17.0 - 18.5");

        rating4.setText("Wartość prawidłowa");
        rating44.setText("18.5 - 25.0");

        rating5.setText("Nadwaga");
        rating55.setText("25.0 - 30.0");

        rating6.setText("I stopień otyłości");
        rating66.setText("30.0 - 35.0");

        rating7.setText("II stopień otyłości");
        rating77.setText("35.0 - 40.0");

        rating8.setText("III stopień otyłości");
        rating88.setText("> 40");

    }
}
