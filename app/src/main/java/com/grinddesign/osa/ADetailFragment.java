package com.grinddesign.osa;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class ADetailFragment extends Fragment {

    TextView ass;
    TextView cla;
    TextView due;
    TextView det;
    TextView link;
    TextView ins;
    TextView em;
    TextView ser;
    TextView han;

    public ADetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_adetail, container, false);
        ass = (TextView) view.findViewById(R.id.assD);
        cla = (TextView) view.findViewById(R.id.classD);
        due = (TextView) view.findViewById(R.id.dueD);
        det = (TextView) view.findViewById(R.id.detD);
        link = (TextView) view.findViewById(R.id.linkD);
        ins = (TextView) view.findViewById(R.id.insD);
        em = (TextView) view.findViewById(R.id.emD);
        ser = (TextView) view.findViewById(R.id.serD);
        han = (TextView) view.findViewById(R.id.hanD);

        return view;

    }

    public void loadMe() {
        ass.setText(getActivity().getIntent().getStringExtra("item"));
        cla.setText(getActivity().getIntent().getStringExtra("class"));
        due.setText(getActivity().getIntent().getStringExtra("due"));
        det.setText(getActivity().getIntent().getStringExtra("detail"));
        link.setText(getActivity().getIntent().getStringExtra("link"));
        ins.setText(getActivity().getIntent().getStringExtra("instruct"));
        em.setText(getActivity().getIntent().getStringExtra("email"));
        ser.setText(getActivity().getIntent().getStringExtra("service"));
        han.setText(getActivity().getIntent().getStringExtra("handle"));
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
