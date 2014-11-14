package com.grinddesign.osa;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class PAddFragment extends Fragment {

    ArrayList<ProjectObject> pData;
    TextView name;
    TextView days;
    TextView hours;

    String prn;
    String prd;
    String prh;

    Context context;

    public interface onSave {

        void saved(int i);

    }

    private onSave parentActivity;

    public PAddFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_padd, container, false);

        name = (TextView) view.findViewById(R.id.prname);
        days = (TextView) view.findViewById(R.id.days);
        hours = (TextView) view.findViewById(R.id.hours);

        return view;
    }

    public void savingMe() {
        Log.i("step", "0");
        try {
            Log.i("step", "1");
            FileInputStream fis = getActivity().openFileInput("proj.dat");
            Log.i("step", "2");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Log.i("read", "trying to read saved file");
            if (pData == null) {
                pData = new ArrayList<ProjectObject>();
                Log.i("TEST", "TEST");
            }
            pData = (ArrayList<ProjectObject>) ois.readObject();
            Log.i("read", String.valueOf(pData));



            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        prn = name.getText().toString();
        prd = days.getText().toString();
        prh = hours.getText().toString();


        try {
            Log.i("jObj2", "am I here");
            FileOutputStream fos = getActivity().openFileOutput("proj.dat", Context.MODE_PRIVATE);

            // Wrapping our file stream.
            Log.i("jObj2", "am I here2");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // Writing the serializable object to the file
            Log.i("jObj2", "am I here3");
            if (pData == null) {
                pData = new ArrayList<ProjectObject>();
            }



            pData.add(new ProjectObject(prn, prd, prh));
            Log.i("jObj2", String.valueOf(pData));
            oos.writeObject(pData);
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
