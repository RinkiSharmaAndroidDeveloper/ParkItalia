package com.parkitalia.android.activities;

/**
 * Created by pc on 6/22/2017.
 */

public class ServiceModel  {
    String id,title,address,desc,phone,lat,lng,image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ServiceModel(String id, String title, String address, String desc, String phone, String lat, String lng, String image) {

        this.id = id;
        this.title = title;
        this.address = address;
        this.desc = desc;
        this.phone = phone;
        this.lat = lat;
        this.lng = lng;
        this.image = image;
    }
}
