package com.grinddesign.osa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.grinddesign.osa.ADetailActivity;


public class ADetailActivity extends Activity {

    TextView ass;
    TextView cla;
    TextView due;
    TextView det;
    TextView link;
    TextView ins;
    TextView em;
    TextView ser;
    TextView han;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_adetail);
        ADetailFragment fragment = (ADetailFragment) getFragmentManager().findFragmentById(R.id.fragmentAD);
        fragment.loadMe();
    }



}
