package com.grinddesign.osa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;


public class TimeCell extends ArrayAdapter<String> {




    private Context context;
    private ArrayList<String> arrayLister = PDetailFragment.daysArray;

    public TimeCell(Context context, int resource, ArrayList<String> arrayLister) {

        super(context, resource, arrayLister);
        Log.i("cust", "adapter1");
        this.context = context;
        this.arrayLister = arrayLister;
    }



    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.i("cust", "adapter");

        String days = arrayLister.get(position);
        String hourly = PDetailFragment.perDayArray.get(position);



        //this is my code to inflate the custom layout
        LayoutInflater blowUp = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = blowUp.inflate(R.layout.activity_time_cell, null);

        //this is to assign the post to the proper field
        TextView tvMain = (TextView) view.findViewById(R.id.dayText);
        tvMain.setText(days);

        //this is to assign the date to the proper field
        TextView tvSub  = (TextView) view.findViewById(R.id.perDayText);
        tvSub.setText(hourly);

        return view;
    }
}
