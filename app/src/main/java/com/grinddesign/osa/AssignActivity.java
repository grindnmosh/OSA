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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class AssignActivity extends Activity implements AdapterView.OnItemClickListener {

    ArrayList<AssignObject> assData;
    ArrayList<String> assign = new ArrayList<String>();
    ArrayList<String> due = new ArrayList<String>();
    ArrayList<String> det = new ArrayList<String>();
    ArrayList<String> link = new ArrayList<String>();
    ArrayList<String> ins = new ArrayList<String>();
    ArrayList<String> em = new ArrayList<String>();
    ArrayList<String> chSer = new ArrayList<String>();
    ArrayList<String> chHan = new ArrayList<String>();
    static ArrayAdapter<String> assAdapter;
    String classTime;

    String assy;
    int i;
    int c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);

        Intent intent = getIntent();
        classTime = intent.getStringExtra("item");
        Log.i("arrayList", classTime);
        ListView lv = (ListView) findViewById(R.id.listView2);

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

        if (assData != null) {
            for (i = 0; i < assData.size(); i++) {
                assy = "";
                AssignObject data = assData.get(i);
                assy = data.getCl();
                Log.i("check class", String.valueOf(assy));
                for (c = 0; c < assData.size(); c++) {
                    AssignObject subData = assData.get(c);
                    String className = data.getCl();
                    Log.i("read", String.valueOf(assy));
                    if (classTime.equals(className)) {
                        Log.i("Class Object", subData.toString());
                        if (!assign.contains(data.getAss())) {
                            assign.add(data.getAss());
                            due.add(data.getDue());
                            det.add(data.getDet());
                            ins.add(data.getIns());
                            em.add(data.getEm());
                            link.add(data.getLink());
                            chSer.add(data.getChSer());
                            chHan.add(data.getChHan());
                            Log.i("Assignments", assign.toString());
                        }
                    }
                }
            }
        }
        assAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, assign);
        lv.setOnItemClickListener(this);
        lv.setAdapter(assAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.assign, menu);
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
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Log.i("check click", "Click Click Boom");
        Intent assPass = new Intent(this, ADetailActivity.class);
        String assignment = assign.get(position);
        String dueDate = due.get(position);
        String detail = det.get(position);
        String linked = link.get(position);
        String teach = ins.get(position);
        String email = em.get(position);
        String serve = chSer.get(position);
        String hand = chHan.get(position);
        Log.i("check click", assignment);
        assPass.putExtra("item", assignment);
        assPass.putExtra("class", classTime);
        assPass.putExtra("due", dueDate);
        assPass.putExtra("detail", detail);
        assPass.putExtra("link", linked);
        assPass.putExtra("instruct", teach);
        assPass.putExtra("email", email);
        assPass.putExtra("service", serve);
        assPass.putExtra("handle", hand);
        startActivity(assPass);
    }
}
