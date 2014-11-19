package com.grinddesign.osa;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CustCell extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> arrayLister = GradFragment.namesArray;

    public CustCell(Context context, int resource, ArrayList<String> arrayLister) {
        super(context, resource, arrayLister);
        this.context = context;
        this.arrayLister = arrayLister;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.i("cust", "adapter");

        String names = arrayLister.get(position);
        long millis = GradFragment.millisArray.get(position);
        long time = System.currentTimeMillis();
        long count = millis - time;

        LayoutInflater blowUp = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = blowUp.inflate(R.layout.activity_time_cell, null);

        TextView tvMain = (TextView) view.findViewById(R.id.dayText);
        tvMain.setText(names);

        final TextView tvSub  = (TextView) view.findViewById(R.id.perDayText);
        new CountDownTimer(count, 1000) {

            public void onTick(long millisUntilFinished) {
                int days = (int) ((millisUntilFinished / 1000) / 86400);
                int hours = (int) (((millisUntilFinished / 1000)
                        - (days * 86400)) / 3600);
                int minutes = (int) (((millisUntilFinished / 1000)
                        - (days * 86400) - (hours * 3600)) / 60);
                int seconds = (int) ((millisUntilFinished / 1000) % 60);

                String countdown = String.format("%02dd %02dh %02dm %02ds", days,
                        hours, minutes, seconds);
                tvSub.setText(countdown);
            }

            public void onFinish() {
                tvSub.setText("done!");
            }
        }.start();

        return view;

    }
}
