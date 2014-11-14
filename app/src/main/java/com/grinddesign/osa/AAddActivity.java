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


public class AAddActivity extends Activity implements AAddFragment.onSave {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_aadd);




    }

    @Override
    public void saved(int i) {
        Intent load = new Intent(this, MainActivity.class);
        load.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Log.i("TAPPED OUT", "Reaching For Me");
        startActivity(load);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.aadd, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_save) {
            AAddFragment fraggle = (AAddFragment) getFragmentManager().findFragmentById(R.id.fragmentAA);
            fraggle.savingMe();
        }
        return super.onOptionsItemSelected(item);
    }




}
