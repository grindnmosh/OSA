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


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    ArrayList<AssignObject> assData;
    int i;
    int c;
    String classy;
    String[] loads;
    static ArrayAdapter<String> cellAdapter;
    ArrayList<String> classes = new ArrayList<String>();
    ArrayList<String> classObj = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner s = (Spinner) findViewById(R.id.spinner);
        ListView lv = (ListView) findViewById(R.id.listView);

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

        for (i = 0; i < assData.size(); i++) {
            if (assData != null) {
                classy = "";
                AssignObject data = assData.get(i);
                classy = data.getCl();
                Log.i("check class", String.valueOf(classy));
                for (c = 0; c < assData.size(); c++) {
                    AssignObject subData = assData.get(c);
                    String className = data.getCl();
                    Log.i("read", String.valueOf(classy));
                    if (classy.equals(className)) {
                        classObj.add(subData.toString());
                        if (!classes.contains(classy)) {
                            classes.add(classy);
                        }
                    }
                }
            }
        }
        loads = getResources().getStringArray(R.array.loads);
        ArrayAdapter<String> loadsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, loads);
        s.setAdapter(loadsAdapter);

        cellAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classes);
        lv.setOnItemClickListener(this);
        lv.setAdapter(cellAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("check click", "Click Click Boom");
        Intent assPass = new Intent(this, AssignActivity.class);
        String assignment = classObj.get(position);
        Log.i("check click", assignment);
        assPass.putExtra("item", assignment);
        startActivity(assPass);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_new) {
            Intent addNew = new Intent(this, AAddActivity.class);
            this.startActivity(addNew);
        }
        return super.onOptionsItemSelected(item);
    }
}
