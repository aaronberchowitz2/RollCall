package com.example.rollcall;

import java.io.Serializable;

class Leaders implements Serializable {
    private int Image;
    private String Name;
    private String Points;

    public int getImage() {
        return Image;
    }

    public String getName() {
        return Name;
    }

    public String getPoints() {
        return Points;
    }



    public Leaders (int image, String name , String points){
        this.Image = image;
        this.Name = name;
        this.Points = points;

    }
}
