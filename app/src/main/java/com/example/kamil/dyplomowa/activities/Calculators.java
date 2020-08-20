package com.example.kamil.dyplomowa.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.kamil.dyplomowa.R;

public class Calculators extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculators);

        button1 = (Button)findViewById(R.id.buttonBMI);
        button2 = (Button)findViewById(R.id.buttonCalories);
        button3 = (Button)findViewById(R.id.buttonBrock);
        button4 = (Button)findViewById(R.id.buttonRequireCalories);

        button1.performClick();
        button1.callOnClick();
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonBMI:
                intent = new Intent(getApplicationContext(),BMI.class);
                startActivity(intent);
                break;
            case R.id.buttonCalories:
                intent = new Intent(getApplicationContext(), CaloryCalculatorSearchAdd.class);
                startActivity(intent);
                break;
            case R.id.buttonBrock:
                intent = new Intent(getApplicationContext(),Broca.class);
                startActivity(intent);
                break;
            case R.id.buttonRequireCalories:
                intent = new Intent(getApplicationContext(),RequiredCalories.class);
                startActivity(intent);
                break;
        }
    }
}
