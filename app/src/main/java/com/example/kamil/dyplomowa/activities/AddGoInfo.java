package com.example.kamil.dyplomowa.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamil.dyplomowa.R;
import com.example.kamil.dyplomowa.adapters.SpinnerNothingSelectedAdapter;
import com.example.kamil.dyplomowa.database.DataBaseHelper;
import com.example.kamil.dyplomowa.database.DataBaseOperation;
import com.example.kamil.dyplomowa.database.DatabaseContract;
import com.example.kamil.dyplomowa.models.Activity;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddGoInfo extends AppCompatActivity {

    public EditText dateET;
    public Spinner spinner;
    public CheckBox checkBox;
    public CheckBox checkBox2;
    public EditText distanceET;
    public EditText minuteET;
    public EditText hourET;
    public Button save;
    public RelativeLayout activity_add_go_info;
    DataBaseHelper dataBaseHelper;
    DataBaseOperation dataBaseOperation = null;
    Activity activity = new Activity();
    long result;
    private List<Activity> activityList;
    String spinnerElement;
    static final int DATE_DIALOG_ID = 1;
    int yearField, month, day;
    Date date;
    Date currentDateTypeDate;
    String currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_go_info);
        initUIelements();
        dateET.setTextIsSelectable(true);

        distanceET.setEnabled(false);
        minuteET.setEnabled(false);
        hourET.setEnabled(false);

        checkBox.setChecked(false);
        checkBox2.setChecked(false);

        date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        currentDate = dateFormat.format(date);
        try {
            currentDateTypeDate = dateFormat.parse(currentDate);
            System.out.println(currentDateTypeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        dateET.setText(currentDate);
        dateET.setFocusable(false);

        final Calendar c = Calendar.getInstance();
        yearField = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        dateET.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showDialog(DATE_DIALOG_ID);
                return false;
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.activity_item,
                R.layout.simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(
                new SpinnerNothingSelectedAdapter(
                        adapter,
                        R.layout.activity_spinner_title,
                        this));


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner.getSelectedItemPosition() == 0) {

                } else {
                    spinnerElement = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        dataBaseOperation = new DataBaseOperation(this);

        dataBaseHelper = new DataBaseHelper(this);
        //Check exists database
        File database = getApplicationContext().getDatabasePath(DatabaseContract.DBNAME);
        if (false == database.exists()) {
            dataBaseHelper.getReadableDatabase();
            //Copy db
            if (dataBaseHelper.copyDataBase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateDownload = dateET.getText().toString();
                System.out.println("jaka data" + dateDownload);
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                Date resultDate = null;
                try {
                    resultDate = df.parse(dateDownload);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                if (spinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(getApplicationContext(), "Wybierz aktywność", Toast.LENGTH_SHORT).show();
                    TextView errorText = (TextView) spinner.getSelectedView();
                    errorText.setPadding(10, 0, 40, 0);
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Wybierz aktywność");
                    return;
                }
                if (checkBox.isChecked() == false && checkBox2.isChecked() == false) {
                    Toast.makeText(getApplicationContext(), "Wybierz jedna z opcji", Toast.LENGTH_SHORT).show();
                    checkBox.setError("");
                    checkBox2.setError("");
                    return;
                }
                if (checkBox.isChecked() && checkBox2.isChecked() == false) {
                    if (distanceET.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Podaj dystans", Toast.LENGTH_SHORT).show();
                        distanceET.setError("Podaj dystans");
                        return;
                    }
                    if (resultDate.after(currentDateTypeDate)) {
                        Toast.makeText(getApplicationContext(), "Data nie może być wieksza od dzisiejszej", Toast.LENGTH_SHORT).show();
                        dateET.setError("");
                        return;
                    }


                    if (checkBox.isChecked() && checkBox2.isChecked() == false && !distanceET.getText().toString().equals("")
                            && (resultDate.before(currentDateTypeDate)) || (resultDate.equals(currentDateTypeDate))) {
                        if (dataBaseOperation.searchActivity(dateET.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Aktywność w tym dniu została już dodana", Toast.LENGTH_SHORT).show();
                            dateET.setError(null);
                            return;
                        } else {
                            setActivityData3();
                            result = dataBaseOperation.saveDataActivity(activity);
                            Log.d("result", Long.toString(result));
                            activityList = dataBaseOperation.getActivityList();

                            if (result > 0) {
                                Toast.makeText(getApplicationContext(), "Aktywność dodana", Toast.LENGTH_SHORT).show();
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(getApplicationContext(), LogActivity.class);
                                        startActivity(intent);
                                    }
                                }, 1000);
                            } else {
                                Toast.makeText(getApplicationContext(), "Aktywność nie dodana.Spróbuj ponownie.", Toast.LENGTH_SHORT).show();
                            }
                            for (int i = 0; i < activityList.size(); i++) {

                                System.out.println(activityList.get(i).getName() + "\n" + activityList.get(i).getDistance() + "\n" +
                                        activityList.get(i).getTime() + "\n" + activityList.get(i).getDate());
                            }
                        }

                    }
                }


                if (checkBox2.isChecked() && checkBox.isChecked() == false) {
                    if (minuteET.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Podaj minuty", Toast.LENGTH_SHORT).show();
                        minuteET.setError("Podaj minuty");
                        return;
                    }

                    if(!minuteET.getText().toString().equals("")){
                        double minute = Double.parseDouble(minuteET.getText().toString());
                        if(minute>59){
                            Toast.makeText(getApplicationContext(), "Maksymalna wartość to 59", Toast.LENGTH_SHORT).show();
                            minuteET.setError("Max 59");
                            return;
                        }

                    }
                    if (hourET.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), "Podaj godziny", Toast.LENGTH_SHORT).show();
                        hourET.setError("Podaj godziny");
                        return;
                    }

                    if (resultDate.after(currentDateTypeDate)) {
                        Toast.makeText(getApplicationContext(), "Data nie może być wieksza od dzisiejszej", Toast.LENGTH_SHORT).show();
                        dateET.setError("");
                        return;
                    }

                    if (checkBox2.isChecked() && checkBox.isChecked() == false && !minuteET.getText().toString().equals("") &&
                            !hourET.getText().toString().equals("") && (resultDate.before(currentDateTypeDate)) || (resultDate.equals(currentDateTypeDate))) {
                        if (dataBaseOperation.searchActivity(dateET.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Aktywność w tym dniu została już dodana", Toast.LENGTH_SHORT).show();
                            dateET.setError(null);
                            return;
                        } else {
                            setActivityData2();
                            activity.setDistance(0);
                            result = dataBaseOperation.saveDataActivity(activity);
                            Log.d("result", Long.toString(result));
                            activityList = dataBaseOperation.getActivityList();

                            if (result > 0) {
                                Toast.makeText(getApplicationContext(), "Aktywność dodana", Toast.LENGTH_SHORT).show();
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(getApplicationContext(), LogActivity.class);
                                        startActivity(intent);
                                    }
                                }, 1000);
                            } else {
                                Toast.makeText(getApplicationContext(), "Aktywność nie dodana.Spróbuj ponownie.", Toast.LENGTH_SHORT).show();
                            }

                            for (int i = 0; i < activityList.size(); i++) {

                                System.out.println(activityList.get(i).getName() + "\n" + activityList.get(i).getDistance() + "\n" +
                                        activityList.get(i).getTime() + "\n" + activityList.get(i).getDate());
                            }
                        }
                    }

                }
                if (checkBox.isChecked() && checkBox2.isChecked()) {
                    if ("".equals(distanceET.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Podaj dystans", Toast.LENGTH_SHORT).show();
                        //heightET.requestFocus();

                        distanceET.setError("Podaj dystans");
                        return;
                    }

                    if ("".equals(minuteET.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Podaj minuty", Toast.LENGTH_SHORT).show();
                        //heightET.requestFocus();

                        minuteET.setError("Podaj minuty");
                        return;
                    }
                    if(!minuteET.getText().toString().equals("")){
                        double minute = Double.parseDouble(minuteET.getText().toString());
                        if(minute>59){
                            Toast.makeText(getApplicationContext(), "Maksymalna wartość to 59", Toast.LENGTH_SHORT).show();
                            minuteET.setError("Max 59");
                            return;
                        }

                    }
                    if ("".equals(hourET.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Podaj godziny", Toast.LENGTH_SHORT).show();
                        //weightET.requestFocus();
                        hourET.setError("Podaj godziny");
                        return;
                    }
                    if (resultDate.after(currentDateTypeDate)) {
                        Toast.makeText(getApplicationContext(), "Data nie może być wieksza od dzisiejszej", Toast.LENGTH_SHORT).show();
                        dateET.setError("");
                        return;
                    }

                    if (checkBox2.isChecked() && checkBox.isChecked() && !distanceET.getText().toString().equals("") && !minuteET.getText().toString().equals("") &&
                            !hourET.getText().toString().equals("") && (resultDate.before(currentDateTypeDate)) || (resultDate.equals(currentDateTypeDate))) {

                        if (dataBaseOperation.searchActivity(dateET.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Aktywność w tym dniu została już dodana", Toast.LENGTH_SHORT).show();
                            dateET.setError(null);
                            return;
                        } else {
                            setActivityData();
                            result = dataBaseOperation.saveDataActivity(activity);
                            Log.d("result", Long.toString(result));
                            activityList = dataBaseOperation.getActivityList();

                            if (result > 0) {
                                Toast.makeText(getApplicationContext(), "Aktywność dodana", Toast.LENGTH_SHORT).show();
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(getApplicationContext(), LogActivity.class);
                                        startActivity(intent);
                                    }
                                }, 1000);
                            } else {
                                Toast.makeText(getApplicationContext(), "Aktywność nie dodana.Spróbuj ponownie.", Toast.LENGTH_SHORT).show();
                            }

                            for (int i = 0; i < activityList.size(); i++) {

                                System.out.println(activityList.get(i).getName() + "\n" + activityList.get(i).getDistance() + "\n" +
                                        activityList.get(i).getTime() + "\n" + activityList.get(i).getDate());
                            }
                        }
                    }
                }
                spinner.setSelection(0);
                distanceET.setEnabled(false);
                minuteET.setEnabled(false);
                hourET.setEnabled(false);
                distanceET.setText(null);
                minuteET.setText(null);
                hourET.setText(null);
                dateET.setText(currentDate);

                checkBox.setChecked(false);
                checkBox2.setChecked(false);




            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    distanceET.setEnabled(true);
                } else {
                    distanceET.setEnabled(false);
                }
                checkBox.setError(null);
                checkBox2.setError(null);

                distanceET.setText(null);
                minuteET.setText(null);
                hourET.setText(null);
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox2.isChecked()) {
                    minuteET.setEnabled(true);
                    hourET.setEnabled(true);
                } else {
                    minuteET.setEnabled(false);
                    hourET.setEnabled(false);
                }
                checkBox.setError(null);
                checkBox2.setError(null);
                distanceET.setText(null);
                minuteET.setText(null);
                hourET.setText(null);
            }
        });


//hide soft keyboard after clicking activity Layout
        activity_add_go_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

            }
        });

    }

    public void setActivityData() {

        activity.setName(spinnerElement);
        activity.setDistance((Double.parseDouble(distanceET.getText().toString())));
        activity.setTime(hourET.getText().toString() + ":" + minuteET.getText().toString());
        activity.setDate(dateET.getText().toString());

    }

    public void setActivityData2() {

        activity.setName(spinnerElement);
        activity.setTime(hourET.getText().toString() + ":" + minuteET.getText().toString());
        activity.setDate(dateET.getText().toString());

    }

    public void setActivityData3() {
        activity.setName(spinnerElement);
        activity.setDistance((Double.parseDouble(distanceET.getText().toString())));
        activity.setTime("00:00");
        activity.setDate(dateET.getText().toString());

    }


    public void initUIelements() {
        dateET = (EditText) findViewById(R.id.addGoDate);
        spinner = (Spinner) findViewById(R.id.addGoSpinner);
        save = (Button) findViewById(R.id.saveGoButton);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        distanceET = (EditText) findViewById(R.id.distancEditText);
        minuteET = (EditText) findViewById(R.id.minuteEditText);
        hourET = (EditText) findViewById(R.id.hourEditText);
        activity_add_go_info = (RelativeLayout) findViewById(R.id.activity_add_go_info);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, yearField, month, day);
        }
        return null;
    }

    private void updateDisplay4() {
        dateET.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append("0" + (day + 0)).append("-").append("0" + (month + 1)).append("-").append(yearField));
    }

    private void updateDisplay3() {
        dateET.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append("0" + (day + 0)).append("-").append(month + 1).append("-").append(yearField));
    }

    private void updateDisplay2() {
        dateET.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(day).append("-").append("0" + (month + 1)).append("-").append(yearField));
    }

    private void updateDisplay() {
        dateET.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(day).append("-").append(month + 1).append("-").append(yearField));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub


            if (dayOfMonth < 10 && monthOfYear > 8) {
                yearField = year;
                month = monthOfYear;
                day = dayOfMonth;
                updateDisplay3();
            } else if (dayOfMonth < 10 && monthOfYear < 9) {
                yearField = year;
                month = monthOfYear;
                day = dayOfMonth;
                updateDisplay4();
            } else if (dayOfMonth > 9 && monthOfYear > 8) {
                yearField = year;
                month = monthOfYear;
                day = dayOfMonth;
                updateDisplay();
            } else if (dayOfMonth > 9 && monthOfYear < 9) {
                yearField = year;
                month = monthOfYear;
                day = dayOfMonth;
                updateDisplay2();
            }

        }
    };

}
