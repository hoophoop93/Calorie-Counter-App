package com.example.kamil.dyplomowa.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.kamil.dyplomowa.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BMI extends AppCompatActivity {


    private final int REQUEST_CODE = 1;
    public EditText weight;
    public EditText height;
    public Button buttonBMI;
    private double BMI;
    public String heightInfo;
    public String weightInfo;
    public String info;
    public RelativeLayout activity_bmi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        initUIelements();
        weight.requestFocus();

        buttonBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ("".equals(weight.getText().toString()) || "".equals(height.getText().toString())) {
                    validate();
                } else {
                    calculateBMI();
                    //height.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    Intent intent = new Intent(getApplicationContext(), BmiResult.class);
                    intent.putExtra("info1", weightInfo + "\n" + heightInfo);
                    intent.putExtra("info2", info);
                    intent.putExtra("info3", BMI);
                    startActivity(intent);
                }
            }
        });
        //hide soft keyboard after clicking activity Layout
        activity_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            }
        });


    }

    private void validate() {
        if ("".equals(weight.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Podaj wagę", Toast.LENGTH_SHORT).show();
            weight.requestFocus();
            weight.setError("Podaj wagę");
            return;
        }
        if ("".equals(height.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Podaj wzrost", Toast.LENGTH_SHORT).show();
            height.requestFocus();
            height.setError("Podaj wzrost");
            return;
        }

    }

    private void calculateBMI() {

        double weight = Double.parseDouble(this.weight.getText().toString());
        double height = Double.parseDouble(this.height.getText().toString()) / 100;

        BMI = weight / (height * height);
        Double adouble = new Double(BMI);
        java.text.DecimalFormat df = new java.text.DecimalFormat();
        df.setMaximumFractionDigits(2);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());

        info = "W dniu: " + strDate + "\n,twoje BMI wynosi " + df.format(adouble);
        weightInfo = "Twoja waga: " + this.weight.getText().toString() + " kg";
        heightInfo = "Twój wzrost: " + this.height.getText().toString() + " cm";
    }


    public void initUIelements() {
        activity_bmi = (RelativeLayout) findViewById(R.id.activity_bmi);
        weight = (EditText) findViewById(R.id.weight);
        height = (EditText) findViewById(R.id.height);
        buttonBMI = (Button) findViewById(R.id.buttonBMI);

    }


}
