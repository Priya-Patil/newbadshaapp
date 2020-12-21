package com.m90.badshahandicappertips.otp.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public  class OTPResponce {

    @SerializedName("mobile")
    public String mobile;
    @SerializedName("updated_at")
    public String updated_at;
    @SerializedName("created_at")
    public String created_at;
    @SerializedName("id")
    public int id;

    @Override
    public String toString() {
        return "OTPResponce{" +
                "mobile='" + mobile + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", created_at='" + created_at + '\'' +
                ", id=" + id +
                '}';
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}