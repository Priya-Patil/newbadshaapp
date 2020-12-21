package com.m90.badshahandicappertips.home.model;


import com.google.gson.annotations.SerializedName;

public  class ExpirydateResponce {

    @SerializedName("id")
    public int id;
    @SerializedName("mobile")
    public String mobile;
    @SerializedName("updated_at")
    public String updated_at;
    @SerializedName("created_at")
    public String created_at;
    @SerializedName("active1")
    public String active1;
    @SerializedName("date")
    public String date;

    @Override
    public String toString() {
        return "ExpirydateResponce{" +
                "id=" + id +
                ", mobile='" + mobile + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", created_at='" + created_at + '\'' +
                ", active1='" + active1 + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getActive1() {
        return active1;
    }

    public void setActive1(String active1) {
        this.active1 = active1;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}