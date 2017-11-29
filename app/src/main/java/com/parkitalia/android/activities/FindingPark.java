package com.parkitalia.android.activities;

import android.app.Activity;
import android.location.LocationManager;
import android.os.Bundle;

import com.parkitalia.android.R;

import android.widget.TextView;

//import com.parkitalia.android.fragments.AppMapFragment;


/**
 * Created by Indosurplus on 5/3/2017.
 */

public class FindingPark extends Activity {
    TextView tvLocationName,tvLastLocation;
    LocationManager locationManager;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
    }
}
