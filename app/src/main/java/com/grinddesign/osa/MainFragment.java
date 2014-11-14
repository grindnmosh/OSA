package com.grinddesign.osa;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class MainFragment extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, DialogInterface.OnClickListener {

    Button newButt;
    ArrayList<AssignObject> assData;
    int i;
    int c;
    String classy;
    String[] loads;
    public static ArrayAdapter<String> cellAdapter;
    public static ArrayAdapter<String> loadsAdapter;
    ArrayList<String> classes = new ArrayList<String>();
    ArrayList<String> classObj = new ArrayList<String>();
    ArrayList<AssignObject> crazyGlue = new ArrayList<AssignObject>();

    Context context;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_main, container, false);

        final Spinner s = (Spinner) view.findViewById(R.id.prospin);
        final ListView lv = (ListView) view.findViewById(R.id.listView);
        newButt = (Button) view.findViewById(R.id.newButt);
        newButt.setOnClickListener(myhandle);


        try {
            Log.i("step", "1");
            FileInputStream fis = getActivity().openFileInput("ass.dat");
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
            newButt.setVisibility(View.INVISIBLE);

            for (i = 0; i < assData.size(); i++) {
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
                        Log.i("Class Object", subData.toString());
                        if (!classes.contains(classy)) {
                            classes.add(classy);
                        }
                    }
                }
            }
        }
        else
        {
            newButt.setVisibility(View.VISIBLE);

        }
        loads = getResources().getStringArray(R.array.loads);
        loadsAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, android.R.id.text1, loads);
        s.setAdapter(loadsAdapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (loads[i].equals("Time Management")) {
                    Intent time = new Intent(context, PMainActivity.class);
                    MainFragment.this.startActivity(time);
                }
                else if (loads[i].equals("Profile")) {
                    Intent pro = new Intent(context, ProfileActivity.class);
                    MainFragment.this.startActivity(pro);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cellAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, android.R.id.text1, classes);
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);

        lv.setAdapter(cellAdapter);

        return view;
    }

    View.OnClickListener myhandle = new View.OnClickListener() {
        public void onClick(View v) {
            Intent adder = new Intent(context, AAddActivity.class);
            startActivity(adder);
        }
    };



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("check click", "Click Click Boom");
        Intent assPass = new Intent(context, AssignActivity.class);
        String assignment = classes.get(position);
        Log.i("check click", assignment);
        assPass.putExtra("item", assignment);
        startActivity(assPass);


    }



    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View strings,
                                   int position, long id) {

        try {
            Log.i("step", "1");
            FileInputStream fis = getActivity().openFileInput("ass.dat");
            Log.i("step", "2");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Log.i("read", "trying to read saved file");
            if (assData == null) {
                assData = new ArrayList<AssignObject>();
                Log.i("TEST", "TEST");
            }
            assData = (ArrayList<AssignObject>) ois.readObject();
            Log.i("read", String.valueOf(assData));
            crazyGlue.addAll(assData);

            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Log.i("check kill", "Click Click Boom");
        for (i = 0; i < crazyGlue.size(); i++) {
            classy = classes.get(position);
            Log.i("check class", String.valueOf(classy));
            for (c = 0; c < crazyGlue.size(); c++) {
                AssignObject subData = crazyGlue.get(c);
                String className = subData.getCl();
                Log.i("read", String.valueOf(classy));
                if (classy.equals(className)) {
                    crazyGlue.remove(subData);
                }
            }
        }

        try {
            Log.i("jObj2", "am I here");
            FileOutputStream fos = getActivity().openFileOutput("ass.dat", Context.MODE_PRIVATE);

            // Wrapping our file stream.
            Log.i("jObj2", "am I here2");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // Writing the serializable object to the file
            Log.i("jObj2", "am I here3");
            assData = new ArrayList<AssignObject>();

            assData.addAll(crazyGlue);

            Log.i("jObj2", String.valueOf(assData));
            oos.writeObject(assData);
            // Closing our object stream which also closes the wrapped stream.

            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("jObj2", "Not Doing It");
        }





        classes.remove(position);
        cellAdapter.notifyDataSetChanged();
        return false;

    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Intent addNew = new Intent(context, AAddActivity.class);
        this.startActivity(addNew);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
