package com.grinddesign.osa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;


public class GAddFragment extends Fragment {

    ArrayList<GradObject> gData;
    long dateMillis;
    DatePicker datePicker;
    long m;

    Context context;

    public interface onSave {

        void saved(int i);

    }

    private onSave parentActivity;

    public GAddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_gadd, container, false);

        datePicker = (DatePicker) view.findViewById(R.id.gDate);

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
        m = dateMillis;


        try {
            Log.i("jObj2", "am I here");
            FileOutputStream fos = getActivity().openFileOutput("grad.dat", Context.MODE_PRIVATE);

            ObjectOutputStream oos = new ObjectOutputStream(fos);

            Log.i("jObj2", "am I here3");
            if (gData == null) {
                gData = new ArrayList<GradObject>();
            }



            gData.add(new GradObject(m));
            oos.writeObject(gData);

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
