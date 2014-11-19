package com.grinddesign.osa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;


public class GAddActivity extends Activity implements GAddFragment.onSave {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gadd);
    }

    @Override
    public void saved(int i) {
        Intent gLoad = new Intent(this, GradActivity.class);
        gLoad.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(gLoad);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gadd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_save) {
            GAddFragment gradle = (GAddFragment) getFragmentManager().findFragmentById(R.id.gfragment);
            gradle.savingMe();
        }
        return super.onOptionsItemSelected(item);
    }
}
