package com.example.kamil.dyplomowa.activities;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamil.dyplomowa.utils.Items;
import com.example.kamil.dyplomowa.R;
import com.example.kamil.dyplomowa.database.DataBaseHelper;
import com.example.kamil.dyplomowa.database.DataBaseOperation;
import com.example.kamil.dyplomowa.database.DatabaseContract;
import com.example.kamil.dyplomowa.models.Product;

public class ProductCategory extends AppCompatActivity {

    int count = 0;
    private List<Product> mProductList;
    private DataBaseHelper dataBaseHelper;
    public ProgressBar pb;
    public String tableName[] = {"zbozowe", "alkohole", "grzyby", "jaja", "miesa", "mleczne", "napoje", "orzechy",
            "owoce", "przyprawy", "ryby", "slodycze", "tluszcze", "warzywa", "wedliny", "wszystkie"};


    private ArrayList<Items> mainList;
    private ArrayList<Items.SubCategory> subArrayList1;
    private ArrayList<Items.SubCategory> subArrayList2;
    private ArrayList<Items.SubCategory> subArrayList3;
    private ArrayList<Items.SubCategory> subArrayList4;
    private ArrayList<Items.SubCategory> subArrayList5;
    private ArrayList<Items.SubCategory> subArrayList6;
    private ArrayList<Items.SubCategory> subArrayList7;
    private ArrayList<Items.SubCategory> subArrayList8;
    private ArrayList<Items.SubCategory> subArrayList9;
    private ArrayList<Items.SubCategory> subArrayList10;
    private ArrayList<Items.SubCategory> subArrayList11;
    private ArrayList<Items.SubCategory> subArrayList12;
    private ArrayList<Items.SubCategory> subArrayList13;
    private ArrayList<Items.SubCategory> subArrayList14;
    private ArrayList<Items.SubCategory> subArrayList15;
    private ArrayList<Items.SubCategory> subArrayList16;

    ArrayList<Items.SubCategory.ItemList> subArrayListItem1;
    ArrayList<Items.SubCategory.ItemList> subArrayListItem2;
    ArrayList<Items.SubCategory.ItemList> subArrayListItem3;
    ArrayList<Items.SubCategory.ItemList> subArrayListItem4;
    ArrayList<Items.SubCategory.ItemList> subArrayListItem5;
    ArrayList<Items.SubCategory.ItemList> subArrayListItem6;
    ArrayList<Items.SubCategory.ItemList> subArrayListItem7;
    ArrayList<Items.SubCategory.ItemList> subArrayListItem8;
    ArrayList<Items.SubCategory.ItemList> subArrayListItem9;
    ArrayList<Items.SubCategory.ItemList> subArrayListItem10;
    ArrayList<Items.SubCategory.ItemList> subArrayListItem11;
    ArrayList<Items.SubCategory.ItemList> subArrayListItem12;
    ArrayList<Items.SubCategory.ItemList> subArrayListItem13;
    ArrayList<Items.SubCategory.ItemList> subArrayListItem14;
    ArrayList<Items.SubCategory.ItemList> subArrayListItem15;
    ArrayList<Items.SubCategory.ItemList> subArrayListItem16;


    private LinearLayout mLinearListView;

    boolean isFirstViewClick = false;
    boolean isSecondViewClick = false;
    Context context;


    public class Task extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {

            pb.setVisibility(ProgressBar.VISIBLE);
            mLinearListView = (LinearLayout) findViewById(R.id.linear_ListView);

            //Make array list one is for mainlist and other is for sublist
            mainList = new ArrayList<Items>();
            subArrayList1 = new ArrayList<Items.SubCategory>();
            subArrayList2 = new ArrayList<Items.SubCategory>();
            subArrayList3 = new ArrayList<Items.SubCategory>();
            subArrayList4 = new ArrayList<Items.SubCategory>();
            subArrayList5 = new ArrayList<Items.SubCategory>();
            subArrayList6 = new ArrayList<Items.SubCategory>();
            subArrayList7 = new ArrayList<Items.SubCategory>();
            subArrayList8 = new ArrayList<Items.SubCategory>();
            subArrayList9 = new ArrayList<Items.SubCategory>();
            subArrayList10 = new ArrayList<Items.SubCategory>();
            subArrayList11 = new ArrayList<Items.SubCategory>();
            subArrayList12 = new ArrayList<Items.SubCategory>();
            subArrayList13 = new ArrayList<Items.SubCategory>();
            subArrayList14 = new ArrayList<Items.SubCategory>();
            subArrayList15 = new ArrayList<Items.SubCategory>();
            subArrayList16 = new ArrayList<Items.SubCategory>();

            //Add main categories in Mainlists along with their items it
            mainList.add(new Items("Produkty zbożowe", subArrayList1));
            mainList.add(new Items("Produkty mleczne", subArrayList2));
            mainList.add(new Items("Alkohole", subArrayList3));
            mainList.add(new Items("Grzyby", subArrayList4));
            mainList.add(new Items("Jaja", subArrayList5));
            mainList.add(new Items("Mięsa", subArrayList6));
            mainList.add(new Items("Orzechy", subArrayList7));
            mainList.add(new Items("Owoce", subArrayList8));
            mainList.add(new Items("Przyprawy", subArrayList9));
            mainList.add(new Items("Ryby", subArrayList10));
            mainList.add(new Items("Słodycze", subArrayList11));
            mainList.add(new Items("Soki i napoje", subArrayList12));
            mainList.add(new Items("Tłuszcze", subArrayList13));
            mainList.add(new Items("Warzywa", subArrayList14));
            mainList.add(new Items("Wędliny", subArrayList15));
            mainList.add(new Items("Wszystkie produkty", subArrayList16));

            //This arraylists are used to put items in sublists
            subArrayListItem1 = new ArrayList<Items.SubCategory.ItemList>();
            subArrayListItem2 = new ArrayList<Items.SubCategory.ItemList>();
            subArrayListItem3 = new ArrayList<Items.SubCategory.ItemList>();
            subArrayListItem4 = new ArrayList<Items.SubCategory.ItemList>();
            subArrayListItem5 = new ArrayList<Items.SubCategory.ItemList>();
            subArrayListItem6 = new ArrayList<Items.SubCategory.ItemList>();
            subArrayListItem7 = new ArrayList<Items.SubCategory.ItemList>();
            subArrayListItem8 = new ArrayList<Items.SubCategory.ItemList>();
            subArrayListItem9 = new ArrayList<Items.SubCategory.ItemList>();
            subArrayListItem10 = new ArrayList<Items.SubCategory.ItemList>();
            subArrayListItem11 = new ArrayList<Items.SubCategory.ItemList>();
            subArrayListItem12 = new ArrayList<Items.SubCategory.ItemList>();
            subArrayListItem13 = new ArrayList<Items.SubCategory.ItemList>();
            subArrayListItem14 = new ArrayList<Items.SubCategory.ItemList>();
            subArrayListItem15 = new ArrayList<Items.SubCategory.ItemList>();
            subArrayListItem16 = new ArrayList<Items.SubCategory.ItemList>();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //subArrayList1.add(new Items.SubCategory("Motorola", subArrayListItem1));
            DataBaseOperation dataBaseOperation = new DataBaseOperation(ProductCategory.this);
            dataBaseHelper = new DataBaseHelper(ProductCategory.this);
            //Check exists database
            File database = getApplicationContext().getDatabasePath(DatabaseContract.DBNAME);
            if (false == database.exists()) {
                dataBaseHelper.getReadableDatabase();
                //Copy db
                if (dataBaseHelper.copyDataBase(ProductCategory.this)) {
                    Toast.makeText(getApplicationContext(), "Copy database succes", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Copy data error", Toast.LENGTH_SHORT).show();
                }
            }

            mProductList = dataBaseOperation.getListProduct(tableName[0]);

            //Add arrylist in category
            for (int i = 0; i < mProductList.size(); i++) {
                subArrayListItem1.add(new Items.SubCategory.ItemList(
                        "Kalorie \t\t\t\t" + Double.toString(mProductList.get(i).getKcal()) + "\n"
                                + "Białko \t\t\t\t\t" + Double.toString(mProductList.get(i).getProtein()) + " g \n"
                                + "Tłuszcz \t\t\t\t" + Double.toString(mProductList.get(i).getFat()) + " g \n"
                                + "Węgl. \t\t\t\t\t" + Double.toString(mProductList.get(i).getCarbs()) + " g\n"
                                + "Błonnik \t\t\t\t" + Double.toString(mProductList.get(i).getFiber()) + " g"
                ));
            }

            //Add items means arrylist
            for (int i = 0; i < mProductList.size(); i++) {
                subArrayList1.add(new Items.SubCategory(mProductList.get(i).name, subArrayListItem1));
                //System.out.println(i+"tablica 1"+subArrayList1.get(i).getpSubCatName()+" " + subArrayListItem1.get(i).getItemName());
            }


            mProductList = dataBaseOperation.getListProduct(tableName[5]);
            //Add arrylist in category
            for (int i = 0; i < mProductList.size(); i++) {
                subArrayListItem2.add(new Items.SubCategory.ItemList(
                        "Kalorie \t\t\t\t" + Double.toString(mProductList.get(i).getKcal()) + "\n"
                                + "Białko \t\t\t\t\t" + Double.toString(mProductList.get(i).getProtein()) + " g \n"
                                + "Tłuszcz \t\t\t\t" + Double.toString(mProductList.get(i).getFat()) + " g \n"
                                + "Węgl. \t\t\t\t\t" + Double.toString(mProductList.get(i).getCarbs()) + " g\n"
                                + "Błonnik \t\t\t\t" + Double.toString(mProductList.get(i).getFiber()) + " g"
                ));
            }

            for (int i = 0; i < mProductList.size(); i++) {
                subArrayList2.add(new Items.SubCategory(mProductList.get(i).name, subArrayListItem2));
                // System.out.println(i+" "+subArrayList2.get(i).getpSubCatName() +" "+ subArrayListItem2.get(i).getItemName());
            }


            mProductList = dataBaseOperation.getListProduct(tableName[1]);
            for (int i = 0; i < mProductList.size(); i++) {
                subArrayListItem3.add(new Items.SubCategory.ItemList(
                        "Kalorie \t\t\t\t" + Double.toString(mProductList.get(i).getKcal()) + "\n"
                                + "Białko \t\t\t\t\t" + Double.toString(mProductList.get(i).getProtein()) + " g \n"
                                + "Tłuszcz \t\t\t\t" + Double.toString(mProductList.get(i).getFat()) + " g \n"
                                + "Węgl. \t\t\t\t\t" + Double.toString(mProductList.get(i).getCarbs()) + " g\n"
                                + "Błonnik \t\t\t\t" + Double.toString(mProductList.get(i).getFiber()) + " g"
                ));
            }

            for (int i = 0; i < mProductList.size(); i++) {
                subArrayList3.add(new Items.SubCategory(mProductList.get(i).name, subArrayListItem3));
            }

            mProductList = dataBaseOperation.getListProduct(tableName[2]);
            for (int i = 0; i < mProductList.size(); i++) {
                subArrayListItem4.add(new Items.SubCategory.ItemList(
                        "Kalorie \t\t\t\t" + Double.toString(mProductList.get(i).getKcal()) + "\n"
                                + "Białko \t\t\t\t\t" + Double.toString(mProductList.get(i).getProtein()) + " g \n"
                                + "Tłuszcz \t\t\t\t" + Double.toString(mProductList.get(i).getFat()) + " g \n"
                                + "Węgl. \t\t\t\t\t" + Double.toString(mProductList.get(i).getCarbs()) + " g\n"
                                + "Błonnik \t\t\t\t" + Double.toString(mProductList.get(i).getFiber()) + " g"
                ));
            }

            for (int i = 0; i < mProductList.size(); i++) {
                subArrayList4.add(new Items.SubCategory(mProductList.get(i).name, subArrayListItem4));
            }

            mProductList = dataBaseOperation.getListProduct(tableName[3]);
            for (int i = 0; i < mProductList.size(); i++) {
                subArrayListItem5.add(new Items.SubCategory.ItemList(
                        "Kalorie \t\t\t\t" + Double.toString(mProductList.get(i).getKcal()) + "\n"
                                + "Białko \t\t\t\t\t" + Double.toString(mProductList.get(i).getProtein()) + " g \n"
                                + "Tłuszcz \t\t\t\t" + Double.toString(mProductList.get(i).getFat()) + " g \n"
                                + "Węgl. \t\t\t\t\t" + Double.toString(mProductList.get(i).getCarbs()) + " g\n"
                                + "Błonnik \t\t\t\t" + Double.toString(mProductList.get(i).getFiber()) + " g"
                ));
            }

            for (int i = 0; i < mProductList.size(); i++) {
                subArrayList5.add(new Items.SubCategory(mProductList.get(i).name, subArrayListItem5));
            }

            mProductList = dataBaseOperation.getListProduct(tableName[4]);
            for (int i = 0; i < mProductList.size(); i++) {
                subArrayListItem6.add(new Items.SubCategory.ItemList(
                        "Kalorie \t\t\t\t" + Double.toString(mProductList.get(i).getKcal()) + "\n"
                                + "Białko \t\t\t\t\t" + Double.toString(mProductList.get(i).getProtein()) + " g \n"
                                + "Tłuszcz \t\t\t\t" + Double.toString(mProductList.get(i).getFat()) + " g \n"
                                + "Węgl. \t\t\t\t\t" + Double.toString(mProductList.get(i).getCarbs()) + " g\n"
                                + "Błonnik \t\t\t\t" + Double.toString(mProductList.get(i).getFiber()) + " g"
                ));
            }

            for (int i = 0; i < mProductList.size(); i++) {
                subArrayList6.add(new Items.SubCategory(mProductList.get(i).name, subArrayListItem6));
            }

            mProductList = dataBaseOperation.getListProduct(tableName[7]);
            for (int i = 0; i < mProductList.size(); i++) {
                subArrayListItem7.add(new Items.SubCategory.ItemList(
                        "Kalorie \t\t\t\t" + Double.toString(mProductList.get(i).getKcal()) + "\n"
                                + "Białko \t\t\t\t\t" + Double.toString(mProductList.get(i).getProtein()) + " g \n"
                                + "Tłuszcz \t\t\t\t" + Double.toString(mProductList.get(i).getFat()) + " g \n"
                                + "Węgl. \t\t\t\t\t" + Double.toString(mProductList.get(i).getCarbs()) + " g\n"
                                + "Błonnik \t\t\t\t" + Double.toString(mProductList.get(i).getFiber()) + " g"
                ));
            }

            for (int i = 0; i < mProductList.size(); i++) {
                subArrayList7.add(new Items.SubCategory(mProductList.get(i).name, subArrayListItem7));
            }

            mProductList = dataBaseOperation.getListProduct(tableName[8]);
            for (int i = 0; i < mProductList.size(); i++) {
                subArrayListItem8.add(new Items.SubCategory.ItemList(
                        "Kalorie \t\t\t\t" + Double.toString(mProductList.get(i).getKcal()) + "\n"
                                + "Białko \t\t\t\t\t" + Double.toString(mProductList.get(i).getProtein()) + " g \n"
                                + "Tłuszcz \t\t\t\t" + Double.toString(mProductList.get(i).getFat()) + " g \n"
                                + "Węgl. \t\t\t\t\t" + Double.toString(mProductList.get(i).getCarbs()) + " g\n"
                                + "Błonnik \t\t\t\t" + Double.toString(mProductList.get(i).getFiber()) + " g"
                ));
            }

            for (int i = 0; i < mProductList.size(); i++) {
                subArrayList8.add(new Items.SubCategory(mProductList.get(i).name, subArrayListItem8));
            }

            mProductList = dataBaseOperation.getListProduct(tableName[9]);
            for (int i = 0; i < mProductList.size(); i++) {
                subArrayListItem9.add(new Items.SubCategory.ItemList(
                        "Kalorie \t\t\t\t" + Double.toString(mProductList.get(i).getKcal()) + "\n"
                                + "Białko \t\t\t\t\t" + Double.toString(mProductList.get(i).getProtein()) + " g \n"
                                + "Tłuszcz \t\t\t\t" + Double.toString(mProductList.get(i).getFat()) + " g \n"
                                + "Węgl. \t\t\t\t\t" + Double.toString(mProductList.get(i).getCarbs()) + " g\n"
                                + "Błonnik \t\t\t\t" + Double.toString(mProductList.get(i).getFiber()) + " g"
                ));
            }

            for (int i = 0; i < mProductList.size(); i++) {
                subArrayList9.add(new Items.SubCategory(mProductList.get(i).name, subArrayListItem9));
            }

            mProductList = dataBaseOperation.getListProduct(tableName[10]);
            for (int i = 0; i < mProductList.size(); i++) {
                subArrayListItem10.add(new Items.SubCategory.ItemList(
                        "Kalorie \t\t\t\t" + Double.toString(mProductList.get(i).getKcal()) + "\n"
                                + "Białko \t\t\t\t\t" + Double.toString(mProductList.get(i).getProtein()) + " g \n"
                                + "Tłuszcz \t\t\t\t" + Double.toString(mProductList.get(i).getFat()) + " g \n"
                                + "Węgl. \t\t\t\t\t" + Double.toString(mProductList.get(i).getCarbs()) + " g\n"
                                + "Błonnik \t\t\t\t" + Double.toString(mProductList.get(i).getFiber()) + " g"
                ));
            }

            for (int i = 0; i < mProductList.size(); i++) {
                subArrayList10.add(new Items.SubCategory(mProductList.get(i).name, subArrayListItem10));
            }

            mProductList = dataBaseOperation.getListProduct(tableName[11]);
            for (int i = 0; i < mProductList.size(); i++) {
                subArrayListItem11.add(new Items.SubCategory.ItemList(
                        "Kalorie \t\t\t\t" + Double.toString(mProductList.get(i).getKcal()) + "\n"
                                + "Białko \t\t\t\t\t" + Double.toString(mProductList.get(i).getProtein()) + " g \n"
                                + "Tłuszcz \t\t\t\t" + Double.toString(mProductList.get(i).getFat()) + " g \n"
                                + "Węgl. \t\t\t\t\t" + Double.toString(mProductList.get(i).getCarbs()) + " g\n"
                                + "Błonnik \t\t\t\t" + Double.toString(mProductList.get(i).getFiber()) + " g"
                ));
            }

            for (int i = 0; i < mProductList.size(); i++) {
                subArrayList11.add(new Items.SubCategory(mProductList.get(i).name, subArrayListItem11));
            }

            mProductList = dataBaseOperation.getListProduct(tableName[6]);
            for (int i = 0; i < mProductList.size(); i++) {
                subArrayListItem12.add(new Items.SubCategory.ItemList(
                        "Kalorie \t\t\t\t" + Double.toString(mProductList.get(i).getKcal()) + "\n"
                                + "Białko \t\t\t\t\t" + Double.toString(mProductList.get(i).getProtein()) + " g \n"
                                + "Tłuszcz \t\t\t\t" + Double.toString(mProductList.get(i).getFat()) + " g \n"
                                + "Węgl. \t\t\t\t\t" + Double.toString(mProductList.get(i).getCarbs()) + " g\n"
                                + "Błonnik \t\t\t\t" + Double.toString(mProductList.get(i).getFiber()) + " g"
                ));
            }

            for (int i = 0; i < mProductList.size(); i++) {
                subArrayList12.add(new Items.SubCategory(mProductList.get(i).name, subArrayListItem12));
            }

            mProductList = dataBaseOperation.getListProduct(tableName[12]);
            for (int i = 0; i < mProductList.size(); i++) {
                subArrayListItem13.add(new Items.SubCategory.ItemList(
                        "Kalorie \t\t\t\t" + Double.toString(mProductList.get(i).getKcal()) + "\n"
                                + "Białko \t\t\t\t\t" + Double.toString(mProductList.get(i).getProtein()) + " g \n"
                                + "Tłuszcz \t\t\t\t" + Double.toString(mProductList.get(i).getFat()) + " g \n"
                                + "Węgl. \t\t\t\t\t" + Double.toString(mProductList.get(i).getCarbs()) + " g\n"
                                + "Błonnik \t\t\t\t" + Double.toString(mProductList.get(i).getFiber()) + " g"
                ));
            }

            for (int i = 0; i < mProductList.size(); i++) {
                subArrayList13.add(new Items.SubCategory(mProductList.get(i).name, subArrayListItem3));
            }

            mProductList = dataBaseOperation.getListProduct(tableName[13]);
            for (int i = 0; i < mProductList.size(); i++) {
                subArrayListItem14.add(new Items.SubCategory.ItemList(
                        "Kalorie \t\t\t\t" + Double.toString(mProductList.get(i).getKcal()) + "\n"
                                + "Białko \t\t\t\t\t" + Double.toString(mProductList.get(i).getProtein()) + " g \n"
                                + "Tłuszcz \t\t\t\t" + Double.toString(mProductList.get(i).getFat()) + " g \n"
                                + "Węgl. \t\t\t\t\t" + Double.toString(mProductList.get(i).getCarbs()) + " g\n"
                                + "Błonnik \t\t\t\t" + Double.toString(mProductList.get(i).getFiber()) + " g"
                ));
            }

            for (int i = 0; i < mProductList.size(); i++) {
                subArrayList14.add(new Items.SubCategory(mProductList.get(i).name, subArrayListItem14));
            }
            mProductList = dataBaseOperation.getListProduct(tableName[14]);
            for (int i = 0; i < mProductList.size(); i++) {
                subArrayListItem15.add(new Items.SubCategory.ItemList(
                        "Kalorie \t\t\t\t" + Double.toString(mProductList.get(i).getKcal()) + "\n"
                                + "Białko \t\t\t\t\t" + Double.toString(mProductList.get(i).getProtein()) + " g \n"
                                + "Tłuszcz \t\t\t\t" + Double.toString(mProductList.get(i).getFat()) + " g \n"
                                + "Węgl. \t\t\t\t\t" + Double.toString(mProductList.get(i).getCarbs()) + " g\n"
                                + "Błonnik \t\t\t\t" + Double.toString(mProductList.get(i).getFiber()) + " g"
                ));
            }
            for (int i = 0; i < mProductList.size(); i++) {
                subArrayList15.add(new Items.SubCategory(mProductList.get(i).name, subArrayListItem15));
            }

            mProductList = dataBaseOperation.getListProduct(tableName[15]);
            for (int i = 0; i < mProductList.size(); i++) {
                subArrayListItem16.add(new Items.SubCategory.ItemList(
                        "Kalorie \t\t\t\t" + Double.toString(mProductList.get(i).getKcal()) + "\n"
                                + "Białko \t\t\t\t\t" + Double.toString(mProductList.get(i).getProtein()) + " g \n"
                                + "Tłuszcz \t\t\t\t" + Double.toString(mProductList.get(i).getFat()) + " g \n"
                                + "Węgl. \t\t\t\t\t" + Double.toString(mProductList.get(i).getCarbs()) + " g\n"
                                + "Błonnik \t\t\t\t" + Double.toString(mProductList.get(i).getFiber()) + " g"
                ));
            }
            for (int i = 0; i < mProductList.size(); i++) {
                subArrayList16.add(new Items.SubCategory(mProductList.get(i).name, subArrayListItem16));
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            run();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    pb.setVisibility(ProgressBar.INVISIBLE);

                }
            });

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(ProgressBar.INVISIBLE);

        new Task().execute();

    }


    public void run() {

        //Adds data into first row
        for (int i = 0; i < mainList.size(); i++) {

            LayoutInflater inflater = null;
            inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View mLinearView = inflater.inflate(R.layout.row_first, null, true);

            final TextView mProductName = (TextView) mLinearView.findViewById(R.id.textViewName);
            final RelativeLayout mLinearFirstArrow = (RelativeLayout) mLinearView.findViewById(R.id.linearFirst);
            final LinearLayout mLinearScrollSecond = (LinearLayout) mLinearView.findViewById(R.id.linear_scroll);

            //checkes if menu is already opened or not
            if (isFirstViewClick == false) {
                mLinearScrollSecond.setVisibility(View.GONE);
            } else {
                mLinearScrollSecond.setVisibility(View.VISIBLE);
            }
            //Handles onclick effect on list item
            mLinearFirstArrow.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {


                    if (isFirstViewClick == false) {
                        isFirstViewClick = true;
                        mLinearScrollSecond.setVisibility(View.VISIBLE);

                    } else {
                        isFirstViewClick = false;
                        mLinearScrollSecond.setVisibility(View.GONE);
                    }

                    return false;
                }
            });


            final String name = mainList.get(i).getName();
            mProductName.setText(name);


            //Adds data into second row
            for (int j = 0; j < mainList.get(i).getmSubCategoryList().size(); j++) {
                LayoutInflater inflater2 = null;
                inflater2 = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View mLinearView2 = inflater2.inflate(R.layout.row_second, null);

                TextView mSubItemName = mSubItemName = (TextView) mLinearView2.findViewById(R.id.textViewTitle);
                final RelativeLayout mLinearSecondArrow = (RelativeLayout) mLinearView2.findViewById(R.id.linearSecond);
                final LinearLayout mLinearScrollThird = (LinearLayout) mLinearView2.findViewById(R.id.linear_scroll_third);

                //checkes if menu is already opened or not
                if (isSecondViewClick == false) {
                    mLinearScrollThird.setVisibility(View.GONE);
                } else {
                    mLinearScrollThird.setVisibility(View.VISIBLE);
                }
                //Handles onclick effect on list item
                mLinearSecondArrow.setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN: {
                                System.out.println("1");

                                return true;

                            }
                            case MotionEvent.ACTION_MOVE: {

                                System.out.println("2");
                                break;
                            }
                            case MotionEvent.ACTION_UP: {
                                System.out.println("3");
                                if (isSecondViewClick == false) {
                                    isSecondViewClick = true;
                                    mLinearScrollThird.setVisibility(View.VISIBLE);

                                } else {
                                    isSecondViewClick = false;
                                    mLinearScrollThird.setVisibility(View.GONE);
                                }
                                break;
                            }
                            default:
                                return false;
                        }

                        return false;
                    }
                });


                final String catName = mainList.get(i).getmSubCategoryList().get(j).getpSubCatName();
                mSubItemName.setText(catName);


                //Adds items in subcategories

                LayoutInflater inflater3 = null;
                inflater3 = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View mLinearView3 = inflater3.inflate(R.layout.row_third, null);

                TextView mItemName = (TextView) mLinearView3.findViewById(R.id.textViewItemName);

                final String itemName = mainList.get(i).getmSubCategoryList().get(j).getItemListArray().get(count).getItemName();

                mItemName.setText(itemName);

                mLinearScrollThird.addView(mLinearView3);

                count++;

                if (count == mainList.get(i).getmSubCategoryList().get(j).getItemListArray().size()) {
                    count = 0;
                }


                mLinearFirstArrow.setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN: {
                                return true;

                            }
                            case MotionEvent.ACTION_MOVE: {
                                break;
                            }
                            case MotionEvent.ACTION_UP: {
                                if (isFirstViewClick == false) {
                                    isFirstViewClick = true;
                                    mLinearScrollSecond.setVisibility(View.VISIBLE);

                                } else {
                                    isFirstViewClick = false;
                                    mLinearScrollSecond.setVisibility(View.GONE);
                                }
                                break;
                            }
                            default:
                                return false;
                        }

                        return true;
                    }
                });


                mLinearScrollSecond.addView(mLinearView2);

            }

            mLinearListView.addView(mLinearView);
        }


    }


}