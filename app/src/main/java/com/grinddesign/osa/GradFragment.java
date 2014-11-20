package com.grinddesign.osa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class GradFragment extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ArrayList<GradObject> gData;
    ArrayList<CustObject> cData;

    Button timedButt;
    Button gradButt;
    Button cButt;
    ImageView img;
    TextView tv;

    String[] loads;
    int i;
    int c;
    String name;
    long millis;

    public static ArrayList<String> namesArray;
    public static ArrayList<Long> millisArray;
    ArrayList<CustObject> crazyGlue = new ArrayList<CustObject>();

    public static ArrayAdapter<String> loadsAdapter;
    public static ArrayAdapter<String> custAdapter;

    Context context;

    public GradFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_grad, container, false);

        final Spinner s = (Spinner) view.findViewById(R.id.spingt);
        final ListView lv = (ListView) view.findViewById(R.id.gradList);
        img = (ImageView) view.findViewById(R.id.gtimerbg);
        tv = (TextView) view.findViewById(R.id.gtimer);
        timedButt = (Button) view.findViewById(R.id.custbutt);
        gradButt = (Button) view.findViewById(R.id.gradButt);
        cButt = (Button) view.findViewById(R.id.cButt);

        timedButt.setOnClickListener(myhandler);
        gradButt.setOnClickListener(handle1);
        cButt.setOnClickListener(handle2);

        namesArray = new ArrayList<String>();
        millisArray = new ArrayList<Long>();

        try {
            Log.i("step", "1");
            FileInputStream fis = getActivity().openFileInput("grad.dat");
            Log.i("step", "2");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Log.i("read", "trying to read saved file");
            if (gData == null) {
                gData = new ArrayList<GradObject>();
                Log.i("TEST", "TEST");
            }
            gData = (ArrayList<GradObject>) ois.readObject();

            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (gData != null) {
            img.setVisibility(View.VISIBLE);
            tv.setVisibility(View.VISIBLE);
            gradButt.setVisibility(View.INVISIBLE);


            GradObject gradData = gData.get(0);
            long gMillis = gradData.getGmillis();
            long time = System.currentTimeMillis();
            long count = gMillis - time;
            new CountDownTimer(count, 1000) {

                public void onTick(long millisUntilFinished) {
                    int days = (int) ((millisUntilFinished / 1000) / 86400);
                    String countdown = String.format("%02d DAYS TILL.......", days);
                    tv.setText(countdown);
                }

                public void onFinish() {
                    tv.setText("done!");
                }
            }.start();
        }
        else
        {
            img.setVisibility(View.INVISIBLE);
            tv.setVisibility(View.INVISIBLE);
            gradButt.setVisibility(View.VISIBLE);
        }

        try {
            Log.i("step", "1");
            FileInputStream fis = getActivity().openFileInput("cust.dat");
            Log.i("step", "2");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Log.i("read", "trying to read saved file");
            if (cData == null) {
                cData = new ArrayList<CustObject>();
                Log.i("TEST", "TEST");
            }
            cData = (ArrayList<CustObject>) ois.readObject();

            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        if (cData != null) {
            timedButt.setVisibility(View.VISIBLE);
            cButt.setVisibility(View.INVISIBLE);
            for (i = 0; i < cData.size(); i++) {
                CustObject data = cData.get(i);
                name = data.getTname();
                millis = data.getTmillis();
                Log.i("check project", String.valueOf(name));
                namesArray.add(name);
                millisArray.add(millis);
            }
        }
        else
        {
            timedButt.setVisibility(View.INVISIBLE);
            cButt.setVisibility(View.VISIBLE);
        }

        loads = getResources().getStringArray(R.array.loads);
        loadsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, android.R.id.text1, loads);
        s.setAdapter(loadsAdapter);
        s.setSelection(3);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (loads[i].equals("Assignments")) {
                    Intent main = new Intent(context, MainActivity.class);
                    GradFragment.this.startActivity(main);
                } else if (loads[i].equals("Time Management")) {
                    Intent time = new Intent(context, PMainActivity.class);
                    GradFragment.this.startActivity(time);
                } else if (loads[i].equals("Profile")) {
                    Intent pro = new Intent(context, ProfileActivity.class);
                    GradFragment.this.startActivity(pro);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        custAdapter = new CustCell(getActivity(), R.layout.activity_time_cell, namesArray);
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);
        lv.setAdapter(custAdapter);

        return view;
    }

    View.OnClickListener myhandler = new View.OnClickListener() {
        public void onClick(View v) {
            Intent newTime = new Intent(getActivity(), TAddActivity.class);
            startActivity(newTime);
        }
    };

    View.OnClickListener handle1 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent gTime = new Intent(getActivity(), GAddActivity.class);
            startActivity(gTime);
        }
    };

    View.OnClickListener handle2 = new View.OnClickListener() {
        public void onClick(View v) {
            Intent newTime = new Intent(getActivity(), TAddActivity.class);
            startActivity(newTime);
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        try {
            FileInputStream fis = getActivity().openFileInput("cust.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            if (cData == null) {
                cData = new ArrayList<CustObject>();
            }
            cData = (ArrayList<CustObject>) ois.readObject();
            crazyGlue.addAll(cData);

            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (i = 0; i < namesArray.size(); i++) {
            String pn = namesArray.get(position);
            for (c = 0; c < crazyGlue.size(); c++) {
                CustObject subData = crazyGlue.get(c);
                String custName = subData.getTname();
                if (pn.equals(custName)) {
                    crazyGlue.remove(subData);
                }
            }
        }

        try {
            FileOutputStream fos = getActivity().openFileOutput("cust.dat", Context.MODE_PRIVATE);

            // Wrapping our file stream.
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // Writing the serializable object to the file
            cData = new ArrayList<CustObject>();

            cData.addAll(crazyGlue);

            oos.writeObject(cData);
            // Closing our object stream which also closes the wrapped stream.

            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }





        namesArray.remove(position);
        custAdapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
