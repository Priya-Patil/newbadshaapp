package com.m90.badshahandicappertips.general.model;


import com.google.gson.annotations.SerializedName;

public  class TrackModel {
    @SerializedName("id")
    public int id;
    @SerializedName("horse")
    public String horse;
    @SerializedName("date1")
    public String date1;
    @SerializedName("venue1")
    public String venue1;
    @SerializedName("track")
    public String track;
    @SerializedName("progress")
    public String progress;
    @SerializedName("distance")
    public String distance;
    @SerializedName("remark")
    public String remark;
    @SerializedName("venue")
    public String venue;
    @SerializedName("date")
    public String date;
    @SerializedName("tid")
    public String tid;
    @SerializedName("updated_at")
    public String updated_at;
    @SerializedName("active1")
    public String active1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHorse() {
        return horse;
    }

    public void setHorse(String horse) {
        this.horse = horse;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getVenue1() {
        return venue1;
    }

    public void setVenue1(String venue1) {
        this.venue1 = venue1;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getActive1() {
        return active1;
    }

    public void setActive1(String active1) {
        this.active1 = active1;
    }

    @Override
    public String toString() {
        return "TrackModel{" +
                "id=" + id +
                ", horse='" + horse + '\'' +
                ", date1='" + date1 + '\'' +
                ", venue1='" + venue1 + '\'' +
                ", track='" + track + '\'' +
                ", progress='" + progress + '\'' +
                ", distance='" + distance + '\'' +
                ", remark='" + remark + '\'' +
                ", venue='" + venue + '\'' +
                ", date='" + date + '\'' +
                ", tid='" + tid + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", active1='" + active1 + '\'' +
                '}';
    }
}