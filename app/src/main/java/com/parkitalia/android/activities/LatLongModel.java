package com.parkitalia.android.activities;

/**
 * Created by Indosurplus on 5/15/2017.
 */

public class LatLongModel {
    double lat;
    double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public LatLongModel(double lat, double lng) {

        this.lat = lat;
        this.lng = lng;
    }
}
