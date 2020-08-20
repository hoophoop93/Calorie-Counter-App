package com.example.kamil.dyplomowa.adapters;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.kamil.dyplomowa.fragments.CaloryCalculatorSearchAddDialog;
import com.example.kamil.dyplomowa.R;
import com.example.kamil.dyplomowa.database.DatabaseContract;


/**
 * Created by Kamil on 02.12.2016.
 */

public class CustomAdapter extends CursorAdapter {

    public Context context;
    TextView txtId, txtName;
    private LayoutInflater mInflater;
    public FragmentManager fm;
    public static String itemListViewName;

    public CustomAdapter(Context context, Cursor c, int flags, FragmentManager fm) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.fm = fm;
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_search, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.txtId = (TextView) view.findViewById(R.id.txtId);
        holder.txtName = (TextView) view.findViewById(R.id.txtNameProduct);
        holder.txtName.setOnClickListener(mOnNameClickListener);
        view.setTag(holder);

        return view;

    }

    public View.OnClickListener mOnNameClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            openDialogFragment();
            txtName = (TextView) v.findViewById(R.id.txtNameProduct);
            itemListViewName = txtName.getText().toString();

            //final int position = CaloryCalculatorSearchAdd.listView.getPositionForView((View) v.getParent());

        }
    };


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //If you want to have zebra lines color effect uncomment below code
        /*if(cursor.getPosition()%2==1) {
             view.setBackgroundResource(R.drawable.item_list_backgroundcolor);
        } else {
            view.setBackgroundResource(R.drawable.item_list_backgroundcolor2);
        }*/

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.txtId.setText(cursor.getString(cursor.getColumnIndex(DatabaseContract.PRODUCT_COLUMN_ID)));
        holder.txtName.setText(cursor.getString(cursor.getColumnIndex(DatabaseContract.PRODUCT_COLUMN_NAME) + 3));


    }

    static class ViewHolder {
        TextView txtId;
        TextView txtName;
    }

    public void openDialogFragment() {

        CaloryCalculatorSearchAddDialog myDialog = new CaloryCalculatorSearchAddDialog();
        myDialog.show(fm, "Tag");

    }

}
