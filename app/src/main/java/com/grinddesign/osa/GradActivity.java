package com.grinddesign.osa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class GradActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ArrayList<CustObject> cData;

    Spinner s;
    Button timedButt;
    ImageView img;
    TextView tv;

    String[] loads;
    int i;
    String name;
    long millis;

    public static ArrayAdapter<String> loadsAdapter;
    public static ArrayAdapter<String> custAdapter;
    public static ArrayList<String> namesArray = new ArrayList<String>();
    public static ArrayList<Long> millisArray = new ArrayList<Long>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grad);

        s = (Spinner) findViewById(R.id.spingt);
        final ListView lv = (ListView) findViewById(R.id.gradList);
        img = (ImageView) findViewById(R.id.gtimerbg);
        tv = (TextView) findViewById(R.id.gtimer);
        timedButt = (Button) findViewById(R.id.custbutt);

        img.setVisibility(View.INVISIBLE);
        tv.setVisibility(View.INVISIBLE);

        timedButt.setOnClickListener(myhandle);

        try {
            Log.i("step", "1");
            FileInputStream fis = openFileInput("cust.dat");
            Log.i("step", "2");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Log.i("read", "trying to read saved file");
            if (cData == null) {
                cData = new ArrayList<CustObject>();
                Log.i("TEST", "TEST");
            }
            cData = (ArrayList<CustObject>) ois.readObject();

            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (cData != null) {

            img.setVisibility(View.INVISIBLE);
            tv.setVisibility(View.INVISIBLE);

            for (i = 0; i < cData.size(); i++) {
                CustObject data = cData.get(i);
                name = data.getTname();
                millis = data.getTmillis();
                Log.i("check project", String.valueOf(name));
                namesArray.add(name);
                millisArray.add(millis);
            }
        }

        loads = getResources().getStringArray(R.array.loads);
        loadsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1, loads);
        s.setAdapter(loadsAdapter);
        s.setSelection(3);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (loads[i].equals("Assignments")) {
                    Intent main = new Intent(GradActivity.this, MainActivity.class);
                    GradActivity.this.startActivity(main);
                } else if (loads[i].equals("Time Management")) {
                    Intent time = new Intent(GradActivity.this, PMainActivity.class);
                    GradActivity.this.startActivity(time);
                } else if (loads[i].equals("Profile")) {
                    Intent pro = new Intent(GradActivity.this, ProfileActivity.class);
                    GradActivity.this.startActivity(pro);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        custAdapter = new CustCell(this, R.layout.activity_time_cell, namesArray);
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);

        lv.setAdapter(custAdapter);
    }

    View.OnClickListener myhandle = new View.OnClickListener() {
        public void onClick(View v) {
            Intent newTime = new Intent(GradActivity.this, TAddActivity.class);
            startActivity(newTime);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.grad, menu);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }
}
