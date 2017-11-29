package com.parkitalia.android.activities;

/**
 * Created by Indosurplus on 5/24/2017.
 */

public class FilterModel
{
    String id;
    double lat;
    double lngt;
    String name;
    String type;
    String image;

    public FilterModel(String id,double lat, double lngt, String name, String type/*,String image*/) {
        this.id = id;
        this.lat = lat;
        this.lngt = lngt;
        this.name = name;
        this.type = type;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getLat() {

        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLngt() {
        return lngt;
    }

    public void setLngt(double lngt) {
        this.lngt = lngt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
