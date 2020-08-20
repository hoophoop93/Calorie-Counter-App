package com.example.kamil.dyplomowa.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamil.dyplomowa.R;
import com.example.kamil.dyplomowa.utils.Singleton;
import com.example.kamil.dyplomowa.adapters.SpinnerNothingSelectedAdapter;

public class RequiredCalories extends AppCompatActivity {

    private TextView genderTV;
    private Spinner gender;
    private Spinner lifeStyle;
    private TextView labelSpiner;
    private EditText ageET;
    private EditText heightET;
    private EditText weightET;
    private Button buttonRC;
    private TextView resultTV;
    private Double lifeStyleValue;
    public RelativeLayout activity_required_calories;
    Singleton singleton = new Singleton();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_required_calories);

        initUIelements();

        ageET.requestFocusFromTouch();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender_array,
                R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);

        gender.setAdapter(
                new SpinnerNothingSelectedAdapter(
                        adapter,
                        R.layout.spinner_row_title,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.life_style, R.layout.multiline_spinner_dropdown_item);
        adapter2.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);

        lifeStyle.setAdapter(
                new SpinnerNothingSelectedAdapter(
                        adapter2,
                        R.layout.lifestyle_row_title,
                        this));

        buttonRC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requiredCalories();

            }
        });

        activity_required_calories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            }
        });

    }

    public void requiredCalories() {

        if (gender.getSelectedItemPosition() == 0) {
            Toast.makeText(getApplicationContext(), "Wybierz płeć", Toast.LENGTH_SHORT).show();
            TextView errorText = (TextView) gender.getSelectedView();
            errorText.setPadding(10, 0, 40, 0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Wybierz płeć");
            return;
        }

        if (lifeStyle.getSelectedItemPosition() == 0) {
            Toast.makeText(getApplicationContext(), "Wybierz płeć", Toast.LENGTH_SHORT).show();
            TextView errorText2 = (TextView) lifeStyle.getSelectedView();
            errorText2.setPadding(10, 0, 40, 0);
            errorText2.setError("");
            errorText2.setTextColor(Color.RED);//just to highlight that this is an error
            errorText2.setText("Poziom aktywności fizycznej");
            return;


        }
        if ("".equals(ageET.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Podaj wiek", Toast.LENGTH_SHORT).show();
            //ageET.requestFocus();
            ageET.setError("Podaj wiek");
            return;
        }
        if ("".equals(heightET.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Podaj wzrost", Toast.LENGTH_SHORT).show();
            //heightET.requestFocus();

            heightET.setError("Podaj wzrost");
            return;
        }
        if ("".equals(weightET.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Podaj wage", Toast.LENGTH_SHORT).show();
            //weightET.requestFocus();
            weightET.setError("Podaj wage");
            return;
        }

        double weight = Double.parseDouble(weightET.getText().toString());
        double height = Double.parseDouble(heightET.getText().toString());
        double age = Double.parseDouble(ageET.getText().toString());

        if (gender.getSelectedItem().equals("Kobieta")) {

            if (lifeStyle.getSelectedItemPosition() == 1) {
                lifeStyleValue = 1.0;
            }
            if (lifeStyle.getSelectedItemPosition() == 2) {
                lifeStyleValue = 1.2;
            }
            if (lifeStyle.getSelectedItemPosition() == 3) {
                lifeStyleValue = 1.4;
            }
            if (lifeStyle.getSelectedItemPosition() == 4) {
                lifeStyleValue = 1.6;
            }
            if (lifeStyle.getSelectedItemPosition() == 5) {
                lifeStyleValue = 1.75;
            }
            if (lifeStyle.getSelectedItemPosition() == 6) {
                lifeStyleValue = 2.0;
            }
            double metabolism = 665.09 + (9.56 * weight) + (1.85 * height) - (4.67 * age);
            double result = metabolism * lifeStyleValue;

            Double adouble = new Double(result);
            java.text.DecimalFormat df = new java.text.DecimalFormat();
            df.setMaximumFractionDigits(2);
            //String info ="Potrzebujesz około " + df.format(adouble) +  "kcal/dzień.";
            singleton.addRequired(result);
            //System.out.println(singleton.getListRequired());

            resultTV.setText(Html.fromHtml("Potrzebujesz około " + "<br />" + "<b>" + df.format(adouble) + "</b>" + " kcal/dzień."));


        }
        if (gender.getSelectedItem().equals("Mężczyzna")) {

            if (lifeStyle.getSelectedItemPosition() == 1) {
                lifeStyleValue = 1.0;
            }
            if (lifeStyle.getSelectedItemPosition() == 2) {
                lifeStyleValue = 1.2;
            }
            if (lifeStyle.getSelectedItemPosition() == 3) {
                lifeStyleValue = 1.4;
            }
            if (lifeStyle.getSelectedItemPosition() == 4) {
                lifeStyleValue = 1.6;
            }
            if (lifeStyle.getSelectedItemPosition() == 5) {
                lifeStyleValue = 1.75;
            }
            if (lifeStyle.getSelectedItemPosition() == 6) {
                lifeStyleValue = 2.0;
            }
            double metabolismMan = 66.47 + (13.75 * weight) + (5 * height) - (6.75 * age);
            double resultMan = metabolismMan * lifeStyleValue;

            Double adouble = new Double(resultMan);
            java.text.DecimalFormat df = new java.text.DecimalFormat();
            df.setMaximumFractionDigits(2);
            //String info ="Potrzebujesz około " + df.format(adouble) +  "kcal/dzień.";

            singleton.addRequired(resultMan);
            //System.out.println(singleton.getListRequired());
            resultTV.setText(Html.fromHtml("Potrzebujesz około " + "<br />" + "<b>" + df.format(adouble) + "</b>" + " kcal/dzień."));

        }
    }

    public void initUIelements() {
        gender = (Spinner) findViewById(R.id.spinner2);
        ageET = (EditText) findViewById(R.id.editText1RC);
        heightET = (EditText) findViewById(R.id.editText2RC);
        weightET = (EditText) findViewById(R.id.editText3RC);
        buttonRC = (Button) findViewById(R.id.buttonRC);
        genderTV = (TextView) findViewById(R.id.genderLabel);
        resultTV = (TextView) findViewById(R.id.textViewRC);
        labelSpiner = (TextView) findViewById(R.id.lifeStyleLable);
        lifeStyle = (Spinner) findViewById(R.id.spinnerLifeStyle);
        activity_required_calories = (RelativeLayout) findViewById(R.id.activity_required_calories);
    }

}
