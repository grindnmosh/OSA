package com.grinddesign.osa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class AAddActivity extends Activity {

    ArrayList<AssignObject> assData;
    TextView assName;
    TextView className;
    TextView dueDate;
    TextView detail;
    TextView link;
    TextView teach;
    TextView email;
    TextView service;
    TextView user;

    String assN;
    String assC;
    String assDu;
    String assDe;
    String assL;
    String assT;
    String assE;
    String assS;
    String assU;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadd);

        assName = (TextView) findViewById(R.id.assIn);
        className = (TextView) findViewById(R.id.assClass);
        dueDate = (TextView) findViewById(R.id.assDue);
        detail = (TextView) findViewById(R.id.assDetail);
        link = (TextView) findViewById(R.id.assLink);
        teach = (TextView) findViewById(R.id.assTaught);
        email = (TextView) findViewById(R.id.assMess);
        service = (TextView) findViewById(R.id.assServe);
        user = (TextView) findViewById(R.id.assHandle);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.aadd, menu);
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
                Log.i("step", "1");
                FileInputStream fis = openFileInput("ass.dat");
                Log.i("step", "2");
                ObjectInputStream ois = new ObjectInputStream(fis);
                Log.i("read", "trying to read saved file");
                if (assData == null) {
                    assData = new ArrayList<AssignObject>();
                    Log.i("TEST", "TEST");
                }
                assData = (ArrayList<AssignObject>) ois.readObject();
                Log.i("read", String.valueOf(assData));



                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            assN = assName.getText().toString();
            assC = className.getText().toString();
            assDu = dueDate.getText().toString();
            assDe = detail.getText().toString();
            assL = link.getText().toString();
            assT = teach.getText().toString();
            assE = email.getText().toString();
            assS = service.getText().toString();
            assU = user.getText().toString();

            try {
                Log.i("jObj2", "am I here");
                FileOutputStream fos = openFileOutput("ass.dat", Context.MODE_PRIVATE);

                // Wrapping our file stream.
                Log.i("jObj2", "am I here2");
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                // Writing the serializable object to the file
                Log.i("jObj2", "am I here3");
                if (assData == null) {
                    assData = new ArrayList<AssignObject>();
                }



                assData.add(new AssignObject(assN, assC, assDu, assDe, assL, assT, assE, assS, assU));
                Log.i("jObj2", String.valueOf(assData));
                oos.writeObject(assData);
                // Closing our object stream which also closes the wrapped stream.

                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("jObj2", "Not Doing It");
            }
            Intent load = new Intent(this, MainActivity.class);
            load.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Log.i("TAPPED OUT", "Reaching For Me");
            startActivity(load);
        }
        return super.onOptionsItemSelected(item);
    }
}
