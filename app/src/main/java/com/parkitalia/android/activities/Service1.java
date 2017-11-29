package com.parkitalia.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.parkitalia.android.R;

public class Service1 extends AppCompatActivity{


    double lat1= 43.274786; double lng1= 11.985029; double lat2= 43.274474; double lng2= 11.986448; double lat3= 43.275220; double lng3= 11.985675;
    double lat4= 43.274241; double lng4= 11.984812; double lat5= 43.275371; double lng5= 11.984847;double lat6= 43.274742; double lng6= 11.986223;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollservices1);

        Button gps1 = (Button) findViewById(R.id.button1);
        Button gps2 = (Button) findViewById(R.id.button2);
        Button gps3 = (Button) findViewById(R.id.button3);
        Button gps4 = (Button) findViewById(R.id.button4);
        Button gps5 = (Button) findViewById(R.id.button5);
        Button gps6 = (Button) findViewById(R.id.button6);


        gps1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Service1.this, DinningMapActivity.class);
                intent.putExtra("lat",lat1);
                intent.putExtra("lng",lng1);
                startActivity(intent);
            }
        });

        gps2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Service1.this, DinningMapActivity.class);
                intent.putExtra("lat",lat2);
                intent.putExtra("lng",lng2);
                startActivity(intent);
            }
        });

        gps3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Service1.this, DinningMapActivity.class);
                intent.putExtra("lat",lat3);
                intent.putExtra("lng",lng3);
                startActivity(intent);
            }
        });

        gps4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Service1.this, DinningMapActivity.class);
                intent.putExtra("lat",lat4);
                intent.putExtra("lng",lng4);
                startActivity(intent);
            }
        });

    }
}
