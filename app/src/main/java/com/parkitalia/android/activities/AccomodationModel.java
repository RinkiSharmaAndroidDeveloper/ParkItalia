package com.parkitalia.android.activities;

/**
 * Created by Android-Dev2 on 5/30/2017.
 */

public class AccomodationModel {
    String ImageId,Accomd_name,Accomd_location,Accomd_wevsite,Accomd_phone,Accomd_type,image;
    double Accomd_gps_lat,Accomd_gps_lng;


    public AccomodationModel(String imageId,String accomd_type,String image, String accomd_name, String accomd_location, String accomd_wevsite, String accomd_phone, double Accomd_gps_lat,double Accomd_gps_lng) {
        this.ImageId = imageId;
        Accomd_type = accomd_type;
        this.image=image;
        Accomd_name = accomd_name;
        Accomd_location = accomd_location;
        Accomd_wevsite = accomd_wevsite;
        Accomd_phone = accomd_phone;
       this.Accomd_gps_lat = Accomd_gps_lat;
      this. Accomd_gps_lng = Accomd_gps_lng;
    }

    public String getImageId() {
        return ImageId;
    }

    public void setImageId(String imageId) {
        ImageId = imageId;
    }

    public String  getImage() {
        return image;
    }

    public void setImage(String  image) {
        this.image = image;
    }

    public String getAccomd_name() {
        return Accomd_name;
    }

    public double getAccomd_gps_lat() {
        return Accomd_gps_lat;
    }

    public void setAccomd_gps_lat(double accomd_gps_lat) {
        Accomd_gps_lat = accomd_gps_lat;
    }

    public double getAccomd_gps_lng() {
        return Accomd_gps_lng;
    }

    public void setAccomd_gps_lng(double accomd_gps_lng) {
        Accomd_gps_lng = accomd_gps_lng;
    }

    public void setAccomd_name(String accomd_name) {
        Accomd_name = accomd_name;
    }

    public String getAccomd_location() {
        return Accomd_location;
    }

    public void setAccomd_location(String accomd_location) {
        Accomd_location = accomd_location;
    }

    public String getAccomd_wevsite() {
        return Accomd_wevsite;
    }

    public void setAccomd_wevsite(String accomd_wevsite) {
        Accomd_wevsite = accomd_wevsite;
    }

    public String getAccomd_phone() {
        return Accomd_phone;
    }

    public void setAccomd_phone(String accomd_phone) {
        Accomd_phone = accomd_phone;
    }

    public String getAccomd_type() {
        return Accomd_type;
    }

    public void setAccomd_type(String accomd_type) {
        Accomd_type = accomd_type;
    }


}
