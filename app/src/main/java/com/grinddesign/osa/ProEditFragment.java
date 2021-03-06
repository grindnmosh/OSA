package com.grinddesign.osa;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class ProEditFragment extends Fragment implements DialogInterface.OnClickListener, View.OnClickListener {

    ArrayList<ProfileObject> proData;
    TextView name;
    TextView num;
    TextView sch;
    TextView d1;
    TextView d2;
    TextView d3;
    TextView d4;
    TextView d5;
    TextView p1;
    TextView p2;
    TextView p3;
    TextView p4;
    TextView p5;
    ImageView proImg;

    String na;
    String sn;
    String sc;
    String de1;
    String de2;
    String de3;
    String de4;
    String de5;
    String ph1;
    String ph2;
    String ph3;
    String ph4;
    String ph5;
    String imgPath = "";

    Button mainImg;
    Button proImgbutt;

    Intent photoPickerIntent;

    Context context;

    public interface onSave {

        void saved(int i);

    }

    private onSave parentActivity;

    public ProEditFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_pro_edit, container, false);

        name = (TextView) view.findViewById(R.id.studName);
        num = (TextView) view.findViewById(R.id.studNum);
        sch = (TextView) view.findViewById(R.id.schName);
        d1 = (TextView) view.findViewById(R.id.dept1);
        d2 = (TextView) view.findViewById(R.id.dept2);
        d3 = (TextView) view.findViewById(R.id.dept3);
        d4 = (TextView) view.findViewById(R.id.dept4);
        d5 = (TextView) view.findViewById(R.id.dept5);
        p1 = (TextView) view.findViewById(R.id.num1);
        p2 = (TextView) view.findViewById(R.id.num2);
        p3 = (TextView) view.findViewById(R.id.num3);
        p4 = (TextView) view.findViewById(R.id.num4);
        p5 = (TextView) view.findViewById(R.id.num5);
        proImg = (ImageView) view.findViewById(R.id.proImgMain);

        mainImg = (Button) view.findViewById(R.id.imgButt);
        proImgbutt = (Button) view.findViewById(R.id.imgProButt);

        mainImg.setOnClickListener(this);
        proImgbutt.setOnClickListener(this);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .build();
        ImageLoader.getInstance().init(config);

        Log.i("step", "0");
        try {
            Log.i("step", "1");
            FileInputStream fis = getActivity().openFileInput("pro.dat");
            Log.i("step", "2");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Log.i("read", "trying to read saved file");
            if (proData == null) {
                proData = new ArrayList<ProfileObject>();
                Log.i("TEST", "TEST");
            }
            proData = (ArrayList<ProfileObject>) ois.readObject();
            Log.i("read", String.valueOf(proData));



            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (proData != null) {
            ProfileObject data = proData.get(0);
            name.setText(data.getStudName());
            num.setText(data.getStudNum());
            sch.setText(data.getSchName());
            d1.setText(data.getDept1());
            d2.setText(data.getDept2());
            d3.setText(data.getDept3());
            d4.setText(data.getDept4());
            d5.setText(data.getDept5());
            p1.setText(data.getNum1());
            p2.setText(data.getNum2());
            p3.setText(data.getNum3());
            p4.setText(data.getNum4());
            p5.setText(data.getNum5());
            imgPath = data.getImg();

            Log.i("image", imgPath);

            ImageLoader.getInstance().displayImage(imgPath, proImg);
        }

        return view;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgButt:
                photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
                break;
            case R.id.imgProButt:
                photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
                break;

        }
    }

    public void onActivityResult(int requestCode, int resultCode,
                                 Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == getActivity().RESULT_OK) {
            Uri photoUri = intent.getData();

            if (photoUri != null) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this
                            .getActivity().getContentResolver(), photoUri);
                    proImg.setImageBitmap(bitmap);
                    imgPath = photoUri.toString();
                    Log.i("Image Saved", imgPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void savingMe() {
        proData = new ArrayList<ProfileObject>();

        na = name.getText().toString();
        sn = num.getText().toString();
        sc = sch.getText().toString();
        de1 = d1.getText().toString();
        de2 = d2.getText().toString();
        de3 = d3.getText().toString();
        de4 = d4.getText().toString();
        de5 = d5.getText().toString();
        ph1 = p1.getText().toString();
        ph2 = p2.getText().toString();
        ph3 = p3.getText().toString();
        ph4 = p4.getText().toString();
        ph5 = p5.getText().toString();

        try {
            Log.i("jObj2", "am I here");
            FileOutputStream fos = getActivity().openFileOutput("pro.dat", Context.MODE_PRIVATE);

            // Wrapping our file stream.
            Log.i("jObj2", "am I here2");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // Writing the serializable object to the file
            Log.i("jObj2", "am I here3");
            if (proData == null) {
                proData = new ArrayList<ProfileObject>();
            }



            proData.add(new ProfileObject(na, sn, sc, de1, de2, de3, de4, de5, ph1, ph2, ph3, ph4, ph5, imgPath));
            Log.i("jObj2", String.valueOf(proData));
            oos.writeObject(proData);

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
        this.context = activity;
        parentActivity = (onSave) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
