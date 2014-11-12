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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class PMainActivity extends Activity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ArrayList<ProjectObject> pData;
    String[] loads;
    ListView lv;
    Spinner s;
    String proj;
    String day;
    String hour;
    int i;
    ArrayList<String> projects = new ArrayList<String>();
    ArrayList<String> days = new ArrayList<String>();
    ArrayList<String> hours = new ArrayList<String>();

    static ArrayAdapter<String> projAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmain);

        lv = (ListView) findViewById(R.id.plistView);

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

        if (pData != null) {

            for (i = 0; i < pData.size(); i++) {
                proj = "";
                ProjectObject data = pData.get(i);
                proj = data.getName();
                day = data.getDays();
                hour = data.getHours();
                Log.i("check project", String.valueOf(proj));
                projects.add(proj);
                days.add(day);
                hours.add(hour);


            }
        }

        s = (Spinner) findViewById(R.id.pspinner);
        lv = (ListView) findViewById(R.id.plistView);

        loads = getResources().getStringArray(R.array.loads);
        final ArrayAdapter<String> loadsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, loads);
        s.setAdapter(loadsAdapter);
        s.setSelection(1);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (loads[i].equals("Assignments")) {
                    Intent main = new Intent(PMainActivity.this, MainActivity.class);
                    PMainActivity.this.startActivity(main);
                }
                else if (loads[i].equals("Profile")) {
                    Intent pro = new Intent(PMainActivity.this, ProfileActivity.class);
                    PMainActivity.this.startActivity(pro);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        projAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, projects);
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);

        lv.setAdapter(projAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pmain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_pnew) {
            Intent pAddNew = new Intent(this, PAddActivity.class);
            this.startActivity(pAddNew);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.i("check click", "Click Click Boom");
        Intent projPass = new Intent(this, PDetailActivity.class);
        String timeProj = projects.get(i);
        String dayProj = days.get(i);
        String hourProj = hours.get(i);
        Log.i("check click", timeProj);
        projPass.putExtra("name", timeProj);
        projPass.putExtra("day", dayProj);
        projPass.putExtra("hour", hourProj);
        startActivity(projPass);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        return false;
    }
}
