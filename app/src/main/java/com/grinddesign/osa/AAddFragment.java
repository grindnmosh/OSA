package com.grinddesign.osa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class AAddFragment extends Fragment {

    ArrayList<AssignObject> assData;
    TextView assName;
    TextView className;
    TextView dueDate;
    TextView detail;
    TextView link;
    TextView teach;
    TextView email;
    TextView service;
    TextView user;

    String assN;
    String assC;
    String assDu;
    String assDe;
    String assL;
    String assT;
    String assE;
    String assS;
    String assU;

    Context context;

    public interface onSave {

        void saved(int i);

    }

    private onSave parentActivity;


    public AAddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_aadd, container, false);

        assName = (TextView) view.findViewById(R.id.assIn);
        className = (TextView) view.findViewById(R.id.assClass);
        dueDate = (TextView) view.findViewById(R.id.assDue);
        detail = (TextView) view.findViewById(R.id.assDetail);
        link = (TextView) view.findViewById(R.id.assLink);
        teach = (TextView) view.findViewById(R.id.assTaught);
        email = (TextView) view.findViewById(R.id.assMess);
        service = (TextView) view.findViewById(R.id.assServe);
        user = (TextView) view.findViewById(R.id.assHandle);

        return view;

    }

    public void savingMe() {
        Log.i("step", "0");
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

        assN = assName.getText().toString();
        assC = className.getText().toString();
        assDu = dueDate.getText().toString();
        assDe = detail.getText().toString();
        assL = link.getText().toString();
        assT = teach.getText().toString();
        assE = email.getText().toString();
        assS = service.getText().toString();
        assU = user.getText().toString();

        try {
            Log.i("jObj2", "am I here");
            FileOutputStream fos = getActivity().openFileOutput("ass.dat", Context.MODE_PRIVATE);

            // Wrapping our file stream.
            Log.i("jObj2", "am I here2");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // Writing the serializable object to the file
            Log.i("jObj2", "am I here3");
            if (assData == null) {
                assData = new ArrayList<AssignObject>();
            }



            assData.add(new AssignObject(assN, assC, assDu, assDe, assL, assT, assE, assS, assU));
            Log.i("jObj2", String.valueOf(assData));
            oos.writeObject(assData);
            // Closing our object stream which also closes the wrapped stream.

            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("jObj2", "Not Doing It");
        }
        parentActivity.saved(1);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof onSave) {
            this.context = activity;
            parentActivity = (onSave) activity;
        }
        else {
            throw new ClassCastException(activity.toString() + "must implement method" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}
