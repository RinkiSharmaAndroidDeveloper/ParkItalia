package com.parkitalia.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.parkitalia.android.R;

/**
 * Created by Indosurplus on 5/24/2017.
 */

public class ITALIANROADSIGNS extends Activity {
    String data1="City Centre";
    String data2="Do Not Enter";
    String data3="Minimum Speed Sign";
    String data4="No Passing";
    String data5="Parking Area";
    String data6="Right of way";
    String data7="Max Speed Limit";
    String data8="Stop";
    String data9="Yield";
    String data10="ZTL or Limited Traffic Zone";
    String data11="No Parking or Stopping";
    String data12="One Way";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.scrollsymbols);
        TextView textView1= (TextView) findViewById(R.id.text1);
        TextView textView2= (TextView) findViewById(R.id.text2);
        TextView textView3= (TextView) findViewById(R.id.text3);
        TextView textView4= (TextView) findViewById(R.id.text4);
        TextView textView5= (TextView) findViewById(R.id.text5);
        TextView textView6= (TextView) findViewById(R.id.text6);
        TextView textView7= (TextView) findViewById(R.id.text7);
        TextView textView8= (TextView) findViewById(R.id.text8);
        TextView textView9= (TextView) findViewById(R.id.text9);
        TextView textView10= (TextView) findViewById(R.id.text10);
        TextView textView11= (TextView) findViewById(R.id.text11);
        TextView textView12= (TextView) findViewById(R.id.text12);
        textView1.setText(data1);
        textView2.setText(data2);
        textView3.setText(data3);
        textView4.setText(data4);
        textView5.setText(data5);
        textView6.setText(data6);
        textView7.setText(data7);
        textView8.setText(data8);
        textView9.setText(data9);
        textView10.setText(data10);
        textView11.setText(data11);
        textView12.setText(data12);


    }
}
