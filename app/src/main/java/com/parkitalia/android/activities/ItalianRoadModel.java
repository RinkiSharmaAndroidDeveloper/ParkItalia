package com.parkitalia.android.activities;

/**
 * Created by pc on 6/22/2017.
 */

public class ItalianRoadModel {
    String id,title,image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ItalianRoadModel(String id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }
}
