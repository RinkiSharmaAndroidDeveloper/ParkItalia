package com.parkitalia.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.parkitalia.android.R;

/**
 * Created by Indosurplus on 5/24/2017.
 */

public class TopTour extends AppCompatActivity {
String data1="Tourist Info \n" +
        "Piazza Signorelli\n" +
        "Type:  Tourist Info\n" +
        "GPS: 43.275371, 11.984847\n" +
        "\n" +
        "\n" +
        "\n" +
        "Do it in Tuscany\n" +
        "Vicolo del Precipizio, 2, Cortona\n" +
        "Website (www.doitintuscany.net. )\n" +
        "0575 601966\n" +
        "Type:  Tours\n" +
        "GPS: 43.274742, 11.986223\n";
    double lat1= 43.275371; double lng1= 11.984847; double lat2= 43.274742; double lng2= 11.986223;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toptour);

        Button gps1 = (Button) findViewById(R.id.button1);
        Button gps2 = (Button) findViewById(R.id.button2);

        gps1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TopTour.this, DinningMapActivity.class);
                intent.putExtra("lat",lat1);
                intent.putExtra("lng",lng1);
                startActivity(intent);
            }
        });

        gps2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(TopTour.this, DinningMapActivity.class);
                intent.putExtra("lat",lat2);
                intent.putExtra("lng",lng2);
                startActivity(intent);
            }
        });


    }
}
