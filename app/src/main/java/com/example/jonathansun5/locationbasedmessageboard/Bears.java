package com.example.jonathansun5.locationbasedmessageboard;

import android.widget.ImageView;

public class Bears {

    public Integer landmarkPicture;
    public String landmarkName;
    public String coordinates;

    Bears(Integer landmarkPicture, String landmarkName, String coordinates) {
        this.landmarkPicture = landmarkPicture;
        this.landmarkName = landmarkName;
        this.coordinates = coordinates;
    }
}
