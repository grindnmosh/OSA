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
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;


public class TAddFragment extends Fragment {

    ArrayList<CustObject> cData;
    EditText name;
    long dateMillis;
    DatePicker datePicker;
    String n;
    long m;

    Context context;

    public interface onSave {

        void saved(int i);

    }

    private onSave parentActivity;

    public TAddFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tadd, container, false);
        name = (EditText) view.findViewById(R.id.tname);
        datePicker = (DatePicker) view.findViewById(R.id.tdate);

        Calendar today = Calendar.getInstance();

        datePicker.init(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged (DatePicker datePicker,int i, int i2, int i3){
                        Calendar dateCalendar = Calendar.getInstance();
                        dateCalendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                        dateMillis = dateCalendar.getTimeInMillis();
                        Log.i("load millis", String.valueOf(dateMillis));
                    }
                });
        return view;
    }

    public void savingMe() {
        try {
            FileInputStream fis = getActivity().openFileInput("cust.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Log.i("read", "trying to read saved file");
            if (cData == null) {
                cData = new ArrayList<CustObject>();
                Log.i("TEST", "TEST");
            }
            cData = (ArrayList<CustObject>) ois.readObject();
            Log.i("read", String.valueOf(cData));



            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        n = name.getText().toString();
        m = dateMillis;


        try {
            Log.i("jObj2", "am I here");
            FileOutputStream fos = getActivity().openFileOutput("cust.dat", Context.MODE_PRIVATE);

            ObjectOutputStream oos = new ObjectOutputStream(fos);

            Log.i("jObj2", "am I here3");
            if (cData == null) {
                cData = new ArrayList<CustObject>();
            }



            cData.add(new CustObject(n, m));
            oos.writeObject(cData);
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
