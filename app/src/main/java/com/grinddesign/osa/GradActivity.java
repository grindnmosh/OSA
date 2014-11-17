package com.grinddesign.osa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class GradActivity extends Activity {

    Spinner s;
    Button timedButt;
    String[] loads;

    public static ArrayAdapter<String> loadsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grad);

        s = (Spinner) findViewById(R.id.spingt);
        timedButt = (Button) findViewById(R.id.custbutt);

        timedButt.setOnClickListener(myhandle);

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
}
