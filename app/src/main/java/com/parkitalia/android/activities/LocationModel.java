package com.parkitalia.android.activities;

/**
 * Created by Indosurplus on 5/8/2017.
 */

public class LocationModel  {
String placeid;
    String latitude;
    String longtitude;

    public String getPlaceid() {
        return placeid;
    }

    public void setPlaceid(String placeid) {
        this.placeid = placeid;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public LocationModel(String placeid, String latitude, String longtitude) {

        this.placeid = placeid;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }
}
