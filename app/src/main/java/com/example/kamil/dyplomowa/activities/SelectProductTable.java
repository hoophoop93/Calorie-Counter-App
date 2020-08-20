package com.example.kamil.dyplomowa.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamil.dyplomowa.R;
import com.example.kamil.dyplomowa.adapters.SpinnerNothingSelectedAdapter;

public class SelectProductTable extends AppCompatActivity {

    private Spinner tableNameSpinner;
    private Button addProductButton;
    public String tableName[] = {"zbozowe","mleczne", "alkohole", "grzyby", "jaja", "miesa" , "orzechy",
            "owoce", "przyprawy", "ryby", "slodycze","napoje", "tluszcze", "warzywa", "wedliny"};
    public String nameTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_product_table);

        tableNameSpinner = (Spinner) findViewById(R.id.spinnerAddProduct);
        addProductButton = (Button) findViewById(R.id.selectTableProductButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.table_name,
                R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);

        tableNameSpinner.setAdapter(
                new SpinnerNothingSelectedAdapter(
                        adapter,
                        R.layout.spinner_table_name_title,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tableNameSpinner.getSelectedItemPosition() == 0 ) {
                    validation();
                }else{
                    executeOperation();
                }
            }
        });
    }

    public void validation(){

            Toast.makeText(getApplicationContext(), "Wybierz tabele", Toast.LENGTH_SHORT).show();
            TextView errorText = (TextView) tableNameSpinner.getSelectedView();
            errorText.setPadding(10, 0, 40, 0);
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Wybierz tabele");
            return;


    }
    public void executeOperation(){
        if (tableNameSpinner.getSelectedItemPosition() == 1) {
            nameTable= tableName[0];
        }
        if (tableNameSpinner.getSelectedItemPosition() == 2) {
            nameTable= tableName[1];
        }
        if (tableNameSpinner.getSelectedItemPosition() == 3) {
            nameTable= tableName[2];
        }
        if (tableNameSpinner.getSelectedItemPosition() == 4) {
            nameTable= tableName[3];
        }
        if (tableNameSpinner.getSelectedItemPosition() == 5) {
            nameTable= tableName[4];
        }
        if (tableNameSpinner.getSelectedItemPosition() == 6) {
            nameTable= tableName[5];
        }
        if (tableNameSpinner.getSelectedItemPosition() == 7) {
            nameTable= tableName[6];
        }
        if (tableNameSpinner.getSelectedItemPosition() == 8) {
            nameTable= tableName[7];
        }
        if (tableNameSpinner.getSelectedItemPosition() == 9) {
            nameTable= tableName[8];
        }
        if (tableNameSpinner.getSelectedItemPosition() == 10) {
            nameTable= tableName[9];
        }
        if (tableNameSpinner.getSelectedItemPosition() == 11) {
            nameTable= tableName[10];
        }
        if (tableNameSpinner.getSelectedItemPosition() == 12) {
            nameTable= tableName[11];
        }
        if (tableNameSpinner.getSelectedItemPosition() == 13) {
            nameTable= tableName[12];
        }
        if (tableNameSpinner.getSelectedItemPosition() == 14) {
            nameTable= tableName[13];
        }
        if (tableNameSpinner.getSelectedItemPosition() == 15) {
            nameTable= tableName[14];
        }

        Intent intent = new Intent(getApplicationContext(),AddProduct.class);
        intent.putExtra("tableName", nameTable);
        startActivity(intent);
    }
}
