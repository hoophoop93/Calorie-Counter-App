package com.example.kamil.dyplomowa.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamil.dyplomowa.R;
import com.example.kamil.dyplomowa.adapters.SpinnerNothingSelectedAdapter;

public class Broca extends AppCompatActivity {

    private Spinner gender;
    private EditText ageET;
    private EditText heightET;
    private Button resultBrockButton;
    private TextView genderTV;
    private TextView brockResultInfo;
    public RelativeLayout activity_broca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broca);

        initUIelements();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender_array,
                R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);

        gender.setAdapter(
                new SpinnerNothingSelectedAdapter(
                        adapter,
                        R.layout.spinner_row_title,
                        this));

        resultBrockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBrock();
                heightET.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });

        //hide soft keyboard after clicking activity Layout
        activity_broca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            }
        });

    }

    private void calculateBrock() {
        if (gender.getSelectedItemPosition() == 0) {
            Toast.makeText(getApplicationContext(), "Wybierz płeć", Toast.LENGTH_SHORT).show();
            TextView errorText = (TextView) gender.getSelectedView();
            errorText.setPadding(10, 0, 40, 0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Wybierz płeć");
            return;
        }
        if ("".equals(ageET.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Podaj wiek", Toast.LENGTH_SHORT).show();
            ageET.requestFocus();
            ageET.setError("Podaj wiek");
            return;
        }
        if ("".equals(heightET.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Podaj wzrost", Toast.LENGTH_SHORT).show();
            heightET.requestFocus();
            heightET.setError("Podaj wzrost");
            return;
        }
        double height = Double.parseDouble(this.heightET.getText().toString());
        if (gender.getSelectedItem().equals("Kobieta")) {
            double BrockForWoman = (height - 100) * 0.85;
            Double adouble = new Double(BrockForWoman);
            java.text.DecimalFormat df = new java.text.DecimalFormat();
            df.setMaximumFractionDigits(2);
            String info = "Idealna waga dla Ciebie to " + df.format(adouble) + " kg.";
            ;
            brockResultInfo.setText(info);

        }
        if (gender.getSelectedItem().equals("Mężczyzna")) {
            double BrockForMan = (height - 100) * 0.9;
            Double adouble = new Double(BrockForMan);
            java.text.DecimalFormat df = new java.text.DecimalFormat();
            df.setMaximumFractionDigits(2);
            String info = "Idealna waga dla Ciebie to " + df.format(adouble) + " kg.";
            brockResultInfo.setText(Html.fromHtml("Idealna waga dla Ciebie to " +"<font color='#238E23'>"+ df.format(adouble)+"</font>" + " kg."));
        }


    }

    public void initUIelements() {
        gender = (Spinner) findViewById(R.id.spinner);
        ageET = (EditText) findViewById(R.id.editText);
        heightET = (EditText) findViewById(R.id.editText2);
        resultBrockButton = (Button) findViewById(R.id.resultBrock);
        genderTV = (TextView) findViewById(R.id.genderLabel);
        brockResultInfo = (TextView) findViewById(R.id.brockResultInfo);
        activity_broca = (RelativeLayout) findViewById(R.id.activity_broca);
    }
}
