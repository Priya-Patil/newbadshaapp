package com.m90.badshahandicappertips.general.model;

public class RoundModel {
    @Override
    public String toString() {
        return "RoundModel{" +
                "name='" + name + '\'' +
                ", image_drawable=" + image_drawable +
                ", url='" + url + '\'' +
                '}';
    }

    private String name;
    private int image_drawable;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private  String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage_drawable() {
        return image_drawable;
    }

    public void setImage_drawable(int image_drawable) {
        this.image_drawable = image_drawable;
    }

}
