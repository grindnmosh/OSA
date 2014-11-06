package com.grinddesign.osa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


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
        setContentView(R.layout.activity_adetail);
        ass = (TextView) findViewById(R.id.assD);
        cla = (TextView) findViewById(R.id.classD);
        due = (TextView) findViewById(R.id.dueD);
        det = (TextView) findViewById(R.id.detD);
        link = (TextView) findViewById(R.id.linkD);
        ins = (TextView) findViewById(R.id.insD);
        em = (TextView) findViewById(R.id.emD);
        ser = (TextView) findViewById(R.id.serD);
        han = (TextView) findViewById(R.id.hanD);

        Intent i = getIntent();
        ass.setText(i.getStringExtra("item"));
        cla.setText(i.getStringExtra("class"));
        due.setText(i.getStringExtra("due"));
        det.setText(i.getStringExtra("detail"));
        link.setText(i.getStringExtra("link"));
        ins.setText(i.getStringExtra("instruct"));
        em.setText(i.getStringExtra("email"));
        ser.setText(i.getStringExtra("service"));
        han.setText(i.getStringExtra("handle"));
    }



}
