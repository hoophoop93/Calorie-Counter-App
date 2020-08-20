package com.example.kamil.dyplomowa.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamil.dyplomowa.R;
import com.example.kamil.dyplomowa.database.DataBaseHelper;
import com.example.kamil.dyplomowa.database.DataBaseOperation;
import com.example.kamil.dyplomowa.database.DatabaseContract;
import com.example.kamil.dyplomowa.models.Activity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.PendingIntent.getActivity;

public class LogActivity extends AppCompatActivity {

    static final int DATE_DIALOG_ID = 1;
    int yearField, month, day;
    public EditText dateET;
    public TextView nameActivity;
    public TextView distance;
    public TextView time;
    DataBaseHelper dataBaseHelper;
    DataBaseOperation dataBaseOperation = null;
    Activity activity = new Activity();
    long result;
    private List<Activity> activityList;
    ImageView iconActivity;
    TextView dayWeek;
    DatePickerDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dateET.setText("DD-MM-YYYY");
                nameActivity.setText(null);
                distance.setText("-/-");
                time.setText("-/-");
                dayWeek.setText(null);
                iconActivity.setVisibility(ImageView.INVISIBLE);
                Intent intent = new Intent(getApplicationContext(), AddGoInfo.class);
                startActivity(intent);

            }
        });
        initUIelements();

        dayWeek.setVisibility(TextView.INVISIBLE);
        iconActivity.setVisibility(ImageView.INVISIBLE);
        dateET.setTextIsSelectable(true);
        dateET.setFocusable(false);
        dateET.setText("DD-MM-YYYY");

        final Calendar c = Calendar.getInstance();
        yearField = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        dataBaseOperation = new DataBaseOperation(LogActivity.this);
        dataBaseHelper = new DataBaseHelper(LogActivity.this);
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

        dateET.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                showDialog(DATE_DIALOG_ID);
                return false;
            }
        });


    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                dialog = new DatePickerDialog(this, mDateSetListener, yearField, month, day);

                /*dialog.setButton(BUTTON_NEUTRAL, "Zamknij", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mDateSetListener != null) {
                            if (which == BUTTON_NEUTRAL) {
                                dialog.dismiss();
                                System.out.println("zamyka");
                            }

                        }
                    }
                });
                dialog.setButton(BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mDateSetListener != null) {
                            if (which == BUTTON_POSITIVE) {

                            }

                        }
                    }
                });*/

                return dialog;
        }
        return null;
    }

    private void updateDisplay() {
        dateET.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(day).append("-").append(month + 1).append("-").append(yearField));
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

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub

            yearField = year;
            month = monthOfYear;
            day = dayOfMonth;

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
            SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
            Date date = new Date(yearField, month, day-1);
            String dayOfWeek = simpledateformat.format(date);
            dayWeek.setText(dayOfWeek);
            dayWeek.setVisibility(TextView.VISIBLE);

            activityList = dataBaseOperation.getAcitivityAfterDate(dateET.getText().toString());

            System.out.println(activityList.size());
            for (int i = 0; i < activityList.size(); i++) {

                System.out.println(activityList.get(i).getName() + "\n" + activityList.get(i).getDistance() + "\n" +
                        activityList.get(i).getTime() + "\n" + activityList.get(i).getDate());
            }


            if (dataBaseOperation.searchActivity(dateET.getText().toString())== false) {
                Toast.makeText(getApplicationContext(), "Brak informacji do wyświetlania", Toast.LENGTH_SHORT).show();
                nameActivity.setText(null);
                distance.setText("-/-");
                time.setText("-/-");
                dateET.setError(null);
                iconActivity.setVisibility(ImageView.INVISIBLE);
                return;
            }else{
                if(nameActivity.getText().toString().equals("Rower")){
                    int id = getResources().getIdentifier("com.example.kamil.dyplomowa:drawable/daily_activity_24dp" , null, null);
                    iconActivity.setImageResource(id);
                    iconActivity.setVisibility(ImageView.VISIBLE);

                }
                 if(nameActivity.getText().toString().equals("Bieganie")){
                    int id = getResources().getIdentifier("com.example.kamil.dyplomowa:drawable/ic_directions_run_black_24dp" , null, null);
                    iconActivity.setImageResource(id);
                    iconActivity.setVisibility(ImageView.VISIBLE);

                }
                if(!nameActivity.getText().toString().equals("Bieganie") && !nameActivity.getText().toString().equals("Rower")){
                    iconActivity.setVisibility(ImageView.INVISIBLE);
                }


                    String distanceParse = Double.toString(activityList.get(0).getDistance());
                    nameActivity.setPaintFlags(nameActivity.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    nameActivity.setText(activityList.get(0).getName());

                    distance.setText(distanceParse + " m");
                    distance.setTextColor(getResources().getColor(R.color.colorGreen));
                    time.setText(activityList.get(0).getTime() + " h");
                    time.setTextColor(getResources().getColor(R.color.colorGreen));

            }



        }
    };

    public void initUIelements() {
        dayWeek = (TextView) findViewById(R.id.dayWeek);
        iconActivity = (ImageView) findViewById(R.id.iconActivity);
        dateET = (EditText) findViewById(R.id.dateEditTextLog);
        nameActivity = (TextView) findViewById(R.id.nameActivityLog);
        distance = (TextView) findViewById(R.id.distanceLog);
        time = (TextView) findViewById(R.id.timeLog);

    }

}
