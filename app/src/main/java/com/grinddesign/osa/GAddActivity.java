package com.grinddesign.osa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;


public class GAddActivity extends Activity {

    ArrayList<GradObject> gData;
    long dateMillis;
    DatePicker datePicker;
    long m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadd);

        datePicker = (DatePicker) findViewById(R.id.gDate);

        Calendar today = Calendar.getInstance();

        datePicker.init(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged (DatePicker datePicker,int i, int i2, int i3){
                        Calendar dateCalendar = Calendar.getInstance();
                        dateCalendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                        dateMillis = dateCalendar.getTimeInMillis();
                        Log.i("load millis", String.valueOf(dateMillis));
                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gadd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_save) {
            m = dateMillis;


            try {
                Log.i("jObj2", "am I here");
                FileOutputStream fos = openFileOutput("grad.dat", Context.MODE_PRIVATE);

                ObjectOutputStream oos = new ObjectOutputStream(fos);

                Log.i("jObj2", "am I here3");
                if (gData == null) {
                    gData = new ArrayList<GradObject>();
                }



                gData.add(new GradObject(m));
                oos.writeObject(gData);

                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("jObj2", "Not Doing It");
            }
            Intent gLoad = new Intent(this, GradActivity.class);
            gLoad.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(gLoad);
        }
        return super.onOptionsItemSelected(item);
    }
}
