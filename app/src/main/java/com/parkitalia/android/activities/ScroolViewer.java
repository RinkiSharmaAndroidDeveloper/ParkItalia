package com.parkitalia.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.parkitalia.android.R;

/**
 * Created by Indosurplus on 5/24/2017.
 */

public class ScroolViewer extends Activity {
    String data1="Banca Popolare Di Cortona\n" +
            "Via Guelfa, 4, Cortona\n" +
            "Type:  Bank Machine\n" +
            "GPS: 43.274786, 11.985029\n";
    String data2="Farmacia Centrale\n" +
            "Via Nazionale, 38, Cortona\n" +
            "Type:  Pharmacy\n" +
            "GPS: 43.274474, 11.986448\n";
    String data3="Postal\n" +
            "Via Benedetti, 2/8 Cortona\n" +
            "0575 603021\n" +
            "Type:  Post Office\n" +
            "GPS: 43.275220, 11.985675\n";
    String data4="Cortona Case Immobiliare\n" +
            "Via Guelfa, 27, Cortona\n" +
            "Website:  (www.cortonacase.com) \n" +
            "0575 604881\n" +
            "Type:  Top Real Estate Agency\n" +
            "GPS:  43.274241, 11.984812\n";
    String data5="Taxi\n" +
            "Adreani Enzo \tMob. +39 335 8196313\n" +
            "Belleri Enzo \tMob. +39 335 335394\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicescroll);
        TextView textView1= (TextView) findViewById(R.id.text1);
        TextView textView2= (TextView) findViewById(R.id.text2);
        TextView textView3= (TextView) findViewById(R.id.text3);
        TextView textView4= (TextView) findViewById(R.id.text4);
        TextView textView5= (TextView) findViewById(R.id.text5);
        textView1.setText(data1);
        textView2.setText(data2);
        textView3.setText(data3);
        textView4.setText(data4);
        textView5.setText(data5);
    }
}
