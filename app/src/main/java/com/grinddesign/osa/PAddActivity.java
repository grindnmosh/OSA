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


public class PAddActivity extends Activity implements PAddFragment.onSave {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_padd);
    }

    @Override
    public void saved(int i) {
        Intent pLoad = new Intent(this, PMainActivity.class);
        pLoad.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Log.i("TAPPED OUT", "Reaching For Me");
        startActivity(pLoad);
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
            PAddFragment praggle = (PAddFragment) getFragmentManager().findFragmentById(R.id.fragmentPA);
            praggle.savingMe();
        }
        return super.onOptionsItemSelected(item);
    }
}
