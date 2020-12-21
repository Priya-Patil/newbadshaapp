package com.m90.badshahandicappertips.general.model;

import com.google.gson.annotations.SerializedName;

public class PlateDetailsModel {


    @SerializedName("r")
    public String r;
    @SerializedName("n")
    public String n;
    @SerializedName("main1")
    public String main1;
    @SerializedName("subm")
    public String subm;
    @SerializedName("dista")
    public String dista;
    @SerializedName("rtime")
    public String rtime;
    @SerializedName("venu")
    public String venu;
    @SerializedName("date")
    public String date;
    @SerializedName("active1")
    public int active1;
    @SerializedName("created")
    public String created;



    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getMain1() {
        return main1;
    }

    public void setMain1(String main1) {
        this.main1 = main1;
    }

    public String getSubm() {
        return subm;
    }

    public void setSubm(String subm) {
        this.subm = subm;
    }

    public String getDista() {
        return dista;
    }

    public void setDista(String dista) {
        this.dista = dista;
    }

    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    public String getVenu() {
        return venu;
    }

    public void setVenu(String venu) {
        this.venu = venu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getActive1() {
        return active1;
    }

    public void setActive1(int active1) {
        this.active1 = active1;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "PlateDetailsModel{" +
                ", r='" + r + '\'' +
                ", n='" + n + '\'' +
                ", main1='" + main1 + '\'' +
                ", subm='" + subm + '\'' +
                ", dista='" + dista + '\'' +
                ", rtime='" + rtime + '\'' +
                ", venu='" + venu + '\'' +
                ", date='" + date + '\'' +
                ", active1=" + active1 +
                ", created='" + created + '\'' +
                '}';
    }
}
