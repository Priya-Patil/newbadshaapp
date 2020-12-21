package com.m90.badshahandicappertips.general.model;


import com.google.gson.annotations.SerializedName;

public  class IsRaceAvailableModel {

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("active1")
    public String active1;

    @Override
    public String toString() {
        return "IsRaceAvailableModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active1='" + active1 + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActive1() {
        return active1;
    }

    public void setActive1(String active1) {
        this.active1 = active1;
    }
}