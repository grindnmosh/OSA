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
import android.widget.TimePicker;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;


public class TAddActivity extends Activity {

    ArrayList<CustObject> cData;
    EditText name;
    long dateMillis;
    DatePicker datePicker;
    String n;
    long m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tadd);

        name = (EditText) findViewById(R.id.tname);
        datePicker = (DatePicker) findViewById(R.id.tdate);

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
        getMenuInflater().inflate(R.menu.tadd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_save) {
            try {
                FileInputStream fis = openFileInput("cust.dat");
                ObjectInputStream ois = new ObjectInputStream(fis);
                Log.i("read", "trying to read saved file");
                if (cData == null) {
                    cData = new ArrayList<CustObject>();
                    Log.i("TEST", "TEST");
                }
                cData = (ArrayList<CustObject>) ois.readObject();
                Log.i("read", String.valueOf(cData));



                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            n = name.getText().toString();
            m = dateMillis;


            try {
                Log.i("jObj2", "am I here");
                FileOutputStream fos = openFileOutput("cust.dat", Context.MODE_PRIVATE);

                ObjectOutputStream oos = new ObjectOutputStream(fos);

                Log.i("jObj2", "am I here3");
                if (cData == null) {
                    cData = new ArrayList<CustObject>();
                }



                cData.add(new CustObject(n, m));
                oos.writeObject(cData);
                // Closing our object stream which also closes the wrapped stream.

                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("jObj2", "Not Doing It");
            }

            Intent gLoad = new Intent(this, GradActivity.class);
            gLoad.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Log.i("TAPPED OUT", "Reaching For Me");
            startActivity(gLoad);
        }
        return super.onOptionsItemSelected(item);
    }
}
