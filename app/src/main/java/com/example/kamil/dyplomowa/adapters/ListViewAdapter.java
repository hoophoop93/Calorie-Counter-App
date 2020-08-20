package com.example.kamil.dyplomowa.adapters;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.kamil.dyplomowa.activities.CaloryCalculator;
import com.example.kamil.dyplomowa.database.DataBaseHelper;
import com.example.kamil.dyplomowa.database.DataBaseOperation;
import com.example.kamil.dyplomowa.database.DatabaseContract;
import com.example.kamil.dyplomowa.fragments.CaloryCalculatorDialog;
import com.example.kamil.dyplomowa.R;
import com.example.kamil.dyplomowa.models.Product;
import com.example.kamil.dyplomowa.utils.Singleton;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.kamil.dyplomowa.database.DatabaseContract.Constants.FIRST_COLUMN;
import static com.example.kamil.dyplomowa.database.DatabaseContract.Constants.SECOND_COLUMN;


/**
 * Created by Kamil on 09.12.2016.
 */

public class ListViewAdapter extends BaseAdapter {

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    public FragmentManager fm;
    public static String nameProduct;
    public static String grams;
    Singleton singleton;
    private DataBaseOperation dataBaseOperation;
    private DataBaseHelper dataBaseHelper;
    public TableRow tableRow;


    public ListViewAdapter(Activity activity,ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }
    public ListViewAdapter(Activity activity,ArrayList<HashMap<String, String>> list,FragmentManager fm){
        super();
        this.activity=activity;
        this.list=list;
        this.fm = fm;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub



        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.calory_calculator_list_view_item, null);

            txtFirst=(TextView) convertView.findViewById(R.id.nameProductListView);
            txtSecond=(TextView) convertView.findViewById(R.id.gramsProductListView);
            tableRow = (TableRow) convertView.findViewById(R.id.productRow);

            tableRow.setOnClickListener(mOnNameClickListener);

            tableRow.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final int position = CaloryCalculator.listView.getPositionForView((View) v.getParent());


                    singleton = Singleton.getInstance();
                    /*System.out.println(singleton.getList().size());
                    System.out.println(singleton.getList().get(0)+"element o 0");
                    System.out.println(position+" pozycja uzuwanego elementu");

                    */

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
                    builder1.setMessage("Czy na pewno chcesz usunąć ten produkt?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Tak",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    HashMap<String, String> obj = (HashMap<String, String>) CaloryCalculator.listView.getAdapter().getItem(position);
                                    nameProduct = obj.get(FIRST_COLUMN);
                                    String grams2 = obj.get(SECOND_COLUMN);
                                    grams2 = grams2.substring(0, grams2.length()-1);
                                    System.out.println(nameProduct);

                                    dataBaseOperation = new DataBaseOperation(activity);
                                    dataBaseHelper = new DataBaseHelper(activity);
                                    //Check exists database
                                    File database = activity.getDatabasePath(DatabaseContract.DBNAME);
                                    if (false == database.exists()) {
                                        dataBaseHelper.getReadableDatabase();
                                        //Copy db
                                        if (dataBaseHelper.copyDataBase(activity)) {

                                        } else {

                                        }
                                    }
                                    List<Product> productInfo = dataBaseOperation.getProductInfo(nameProduct);

                                    double kcal = productInfo.get(0).getKcal();
                                    double protein = productInfo.get(0).getProtein();
                                    double fat = productInfo.get(0).getFat();
                                    double carbs = productInfo.get(0).getCarbs();
                                    double fiber = productInfo.get(0).getFiber();

                                    DecimalFormat df = new DecimalFormat("#.##");

                                    double kcalResult = kcal * Double.parseDouble(grams2) / 100;
                                    String totalKcal = CaloryCalculator.kcal.getText().toString().replace(",",".");
                                    double totalKcalDouble = Double.parseDouble(totalKcal);
                                    double kcalAfterDelete = totalKcalDouble - kcalResult;
                                    String kcalResultString = df.format(kcalAfterDelete);
                                    CaloryCalculator.kcal.setText(kcalResultString);

                                    double proteinResult = protein * Double.parseDouble(grams2) / 100;
                                    String totalProtein = CaloryCalculator.protein.getText().toString().replace(",",".");
                                    double totalProteinDouble = Double.parseDouble(totalProtein);
                                    double proteinAfterDelete = totalProteinDouble - proteinResult;
                                    String proteinResultString = df.format(proteinAfterDelete);
                                    CaloryCalculator.protein.setText(proteinResultString);

                                    double fatResult = fat * Double.parseDouble(grams2) / 100;
                                    String totalFat = CaloryCalculator.fat.getText().toString().replace(",",".");
                                    double totalFatDouble = Double.parseDouble(totalFat);
                                    double fatAfterDelete = totalFatDouble - fatResult;
                                    String fatResultString = df.format(fatAfterDelete);
                                    CaloryCalculator.fat.setText(fatResultString);

                                    double carbsResult = carbs * Double.parseDouble(grams2) / 100;
                                    String totalCarbs = CaloryCalculator.carbs.getText().toString().replace(",",".");
                                    double totalCarbsDouble = Double.parseDouble(totalCarbs);
                                    double carbsAfterDelete = totalCarbsDouble - carbsResult;
                                    String carbsResultString = df.format(carbsAfterDelete);
                                    CaloryCalculator.carbs.setText(carbsResultString);

                                    double fiberResult = fiber * Double.parseDouble(grams2) / 100;
                                    String totalFiber = CaloryCalculator.fiber.getText().toString().replace(",",".");
                                    double totalFiberDouble = Double.parseDouble(totalFiber);
                                    double fiberAfterDelete = totalFiberDouble - fiberResult;
                                    String fiberResultString = df.format(fiberAfterDelete);
                                    CaloryCalculator.fiber.setText(fiberResultString);

                                    singleton.getList().remove(position);

                                    CaloryCalculator.adapter.notifyDataSetChanged();
                                    CaloryCalculator.listView.setAdapter(CaloryCalculator.adapter);

                                    if(singleton.getList().size() == 0){
                                        CaloryCalculator.kcal.setText("0");
                                        CaloryCalculator.protein.setText("0");
                                        CaloryCalculator.fat.setText("0");
                                        CaloryCalculator.fiber.setText("0");
                                        CaloryCalculator.carbs.setText("0");
                                    }

                                    CaloryCalculator.sumKcal= CaloryCalculator.sumKcal - kcalResult;
                                    CaloryCalculator.sumProtein = CaloryCalculator.sumProtein - proteinResult;
                                    CaloryCalculator.sumFat = CaloryCalculator.sumFat - fatResult;
                                    CaloryCalculator.sumFiber = CaloryCalculator.sumFiber - fiberResult;
                                    CaloryCalculator.sumCarbs = CaloryCalculator.sumCarbs - carbsResult;
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
            });

        }

        HashMap<String, String> map=list.get(position);
        txtFirst.setText(map.get(FIRST_COLUMN));
        txtSecond.setText(map.get(SECOND_COLUMN));


        return convertView;
    }
    public View.OnClickListener mOnNameClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            final int position = CaloryCalculator.listView.getPositionForView((View) v.getParent());

            openDialogFragment();

            HashMap<String, String> obj = (HashMap<String, String>) CaloryCalculator.listView.getAdapter().getItem(position);
            nameProduct = obj.get(FIRST_COLUMN);
            grams = obj.get(SECOND_COLUMN);
            grams = grams.substring(0, grams.length()-1);

        }
    };

    public void openDialogFragment() {

        CaloryCalculatorDialog myDialog = new CaloryCalculatorDialog();
        myDialog.show(fm, "Tag");

    }

}


