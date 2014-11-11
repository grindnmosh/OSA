package com.grinddesign.osa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;


public class ProfileActivity extends Activity {

    String[] loads;
    Spinner s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
