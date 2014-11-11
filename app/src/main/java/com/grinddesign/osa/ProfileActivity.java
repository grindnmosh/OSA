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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class ProfileActivity extends Activity {

    String[] loads;
    Spinner s;

    ArrayList<ProfileObject> proData;
    TextView name;
    TextView num;
    TextView sch;
    TextView d1;
    TextView d2;
    TextView d3;
    TextView d4;
    TextView d5;
    TextView p1;
    TextView p2;
    TextView p3;
    TextView p4;
    TextView p5;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = (TextView) findViewById(R.id.studName);
        num = (TextView) findViewById(R.id.studNum);
        sch = (TextView) findViewById(R.id.school);
        d1 = (TextView) findViewById(R.id.dept1);
        d2 = (TextView) findViewById(R.id.dept2);
        d3 = (TextView) findViewById(R.id.dept3);
        d4 = (TextView) findViewById(R.id.dept4);
        d5 = (TextView) findViewById(R.id.dept5);
        p1 = (TextView) findViewById(R.id.num1);
        p2 = (TextView) findViewById(R.id.num2);
        p3 = (TextView) findViewById(R.id.num3);
        p4 = (TextView) findViewById(R.id.num4);
        p5 = (TextView) findViewById(R.id.num5);

        Log.i("step", "0");
        try {
            Log.i("step", "1");
            FileInputStream fis = openFileInput("pro.dat");
            Log.i("step", "2");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Log.i("read", "trying to read saved file");
            if (proData == null) {
                proData = new ArrayList<ProfileObject>();
                Log.i("TEST", "TEST");
            }
            proData = (ArrayList<ProfileObject>) ois.readObject();
            Log.i("read", String.valueOf(proData));



            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (proData != null) {
            ProfileObject data = proData.get(0);
            name.setText(data.getStudName());
            num.setText(data.getStudNum());
            sch.setText(data.getSchName());
            d1.setText(data.getDept1());
            d2.setText(data.getDept2());
            d3.setText(data.getDept3());
            d4.setText(data.getDept4());
            d5.setText(data.getDept5());
            p1.setText(data.getNum1());
            p2.setText(data.getNum2());
            p3.setText(data.getNum3());
            p4.setText(data.getNum4());
            p5.setText(data.getNum5());
        }

        s = (Spinner) findViewById(R.id.prospin);

        loads = getResources().getStringArray(R.array.loads);
        final ArrayAdapter<String> loadsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, loads);
        s.setAdapter(loadsAdapter);
        s.setSelection(2);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (loads[i].equals("Assignments")) {
                    Intent main = new Intent(ProfileActivity.this, MainActivity.class);
                    ProfileActivity.this.startActivity(main);
                }
                else if (loads[i].equals("Time Management")) {
                    Intent time = new Intent(ProfileActivity.this, PMainActivity.class);
                    ProfileActivity.this.startActivity(time);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_edit) {
            Intent proNew = new Intent(this, ProEditActivity.class);
            this.startActivity(proNew);
        }
        return super.onOptionsItemSelected(item);
    }
}
