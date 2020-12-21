package com.m90.badshahandicappertips.general.model;


import com.google.gson.annotations.SerializedName;

public  class ArchivesModel {
    @SerializedName("id")
    public int id;
    @SerializedName("venue")
    public String venue;
    @SerializedName("date")
    public String date;
    @SerializedName("active1")
    public String active1;
    @SerializedName("hit")
    public String hit;

    @Override
    public String toString() {
        return "ArchivesModel{" +
                "id=" + id +
                ", venue='" + venue + '\'' +
                ", date='" + date + '\'' +
                ", active1='" + active1 + '\'' +
                ", hit='" + hit + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getActive1() {
        return active1;
    }

    public void setActive1(String active1) {
        this.active1 = active1;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }
}