package com.grinddesign.osa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class PDetailActivity extends Activity {

    TextView projName;

    String proj;
    String d;
    String h;
    float days;
    float hours;
    float perDay;
    int i;
    ListView pd;
    ArrayAdapter<String> pdAdapter;
    public static ArrayList<String> daysArray;
    public static ArrayList<String> perDayArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdetail);

        projName = (TextView) findViewById(R.id.projName);
        pd = (ListView) findViewById(R.id.pdlistView);

        daysArray = new ArrayList<String>();
        perDayArray = new ArrayList<String>();

        Intent intent = getIntent();
        proj = (intent.getStringExtra("name"));
        d = (intent.getStringExtra("day"));
        h = (intent.getStringExtra("hour"));
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

        pdAdapter = new TimeCell(this, R.layout.activity_time_cell, daysArray);


        //load adapter into listview
        pd.setAdapter(pdAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pdetail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
