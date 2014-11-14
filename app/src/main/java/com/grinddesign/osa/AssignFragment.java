package com.grinddesign.osa;

import android.app.Activity;
import android.content.Context;
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
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class AssignFragment extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    ArrayList<AssignObject> assData;
    ArrayList<String> assign = new ArrayList<String>();
    ArrayList<String> due = new ArrayList<String>();
    ArrayList<String> det = new ArrayList<String>();
    ArrayList<String> link = new ArrayList<String>();
    ArrayList<String> ins = new ArrayList<String>();
    ArrayList<String> em = new ArrayList<String>();
    ArrayList<String> chSer = new ArrayList<String>();
    ArrayList<String> chHan = new ArrayList<String>();
    ArrayList<AssignObject> crazyGlue = new ArrayList<AssignObject>();
    public static ArrayAdapter<String> assAdapter;
    String classTime;

    String assy;
    int i;
    int c;

    Context context;

    public AssignFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_assign, container, false);

        classTime = getActivity().getIntent().getStringExtra("item");
        Log.i("arrayList", classTime);
        final ListView lv = (ListView) view.findViewById(R.id.listView2);

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
        assAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,
                android.R.id.text1, assign);
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);
        lv.setAdapter(assAdapter);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Log.i("check click", "Click Click Boom");
        Intent assPass = new Intent(context, ADetailActivity.class);
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

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
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
        for (i = 0; i < assign.size(); i++) {
            assy = assign.get(position);
            Log.i("check class", String.valueOf(assy));
            for (c = 0; c < crazyGlue.size(); c++) {
                AssignObject subData = crazyGlue.get(c);
                String assName = subData.getAss();
                Log.i("read", String.valueOf(assy));
                if (assy.equals(assName)) {
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





        assign.remove(position);
        assAdapter.notifyDataSetChanged();
        return false;
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
