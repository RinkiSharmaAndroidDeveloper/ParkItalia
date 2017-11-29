package com.parkitalia.android.activities;

/**
 * Created by Indosurplus on 5/19/2017.
 */

public class LatlongmodelGet
{
    String id;
    double lat;
    double lng;
    String name;
    String type;
    String image;



    public LatlongmodelGet(String id,double lat, double lng, String name, String type, String image) {

        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.type = type;
        this.image = image;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
