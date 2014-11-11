package com.grinddesign.osa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class PAddActivity extends Activity {

    ArrayList<ProjectObject> pData;
    TextView name;
    TextView days;
    TextView hours;

    String prn;
    String prd;
    String prh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padd);

        name = (TextView) findViewById(R.id.prname);
        days = (TextView) findViewById(R.id.days);
        hours = (TextView) findViewById(R.id.hours);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.padd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_save) {
            Log.i("step", "0");
            try {
                Log.i("step", "1");
                FileInputStream fis = openFileInput("proj.dat");
                Log.i("step", "2");
                ObjectInputStream ois = new ObjectInputStream(fis);
                Log.i("read", "trying to read saved file");
                if (pData == null) {
                    pData = new ArrayList<ProjectObject>();
                    Log.i("TEST", "TEST");
                }
                pData = (ArrayList<ProjectObject>) ois.readObject();
                Log.i("read", String.valueOf(pData));



                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            prn = name.getText().toString();
            prd = days.getText().toString();
            prh = hours.getText().toString();


            try {
                Log.i("jObj2", "am I here");
                FileOutputStream fos = openFileOutput("proj.dat", Context.MODE_PRIVATE);

                // Wrapping our file stream.
                Log.i("jObj2", "am I here2");
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                // Writing the serializable object to the file
                Log.i("jObj2", "am I here3");
                if (pData == null) {
                    pData = new ArrayList<ProjectObject>();
                }



                pData.add(new ProjectObject(prn, prd, prh));
                Log.i("jObj2", String.valueOf(pData));
                oos.writeObject(pData);
                // Closing our object stream which also closes the wrapped stream.

                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("jObj2", "Not Doing It");
            }
            Intent pLoad = new Intent(this, PMainActivity.class);
            pLoad.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Log.i("TAPPED OUT", "Reaching For Me");
            startActivity(pLoad);
        }
        return super.onOptionsItemSelected(item);
    }
}
