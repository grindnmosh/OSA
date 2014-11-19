package com.grinddesign.osa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class PDetailFragment extends Fragment {

    TextView projName;

    String proj;
    String d;
    String h;
    float days;
    float hours;
    float perDay;
    int i;
    public static ArrayAdapter<String> pdAdapter;
    public static ArrayList<String> daysArray;
    public static ArrayList<String> perDayArray;
    Context context;

    public PDetailFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_pdetail, container, false);

        projName = (TextView) view.findViewById(R.id.projName);
        final ListView pd = (ListView) view.findViewById(R.id.pdlistView);

        daysArray = new ArrayList<String>();
        perDayArray = new ArrayList<String>();

        proj = (getActivity().getIntent().getStringExtra("name"));
        d = (getActivity().getIntent().getStringExtra("day"));
        h = (getActivity().getIntent().getStringExtra("hour"));
        days = Integer.parseInt(d);
        hours = Integer.parseInt(h);
        Log.i("days", String.valueOf(days));
        perDay = hours/days;
        for (i = 1; i <= days; i++) {
            d = "Day " + i;
            daysArray.add(d);
            h = perDay + " hours per day";
            perDayArray.add(h);

        }

        projName.setText(proj);

        pdAdapter = new TimeCell(context, R.layout.activity_time_cell, daysArray);

        pd.setAdapter(pdAdapter);

        return view;

    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}
