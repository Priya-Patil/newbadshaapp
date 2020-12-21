package com.m90.badshahandicappertips.home.model;


import com.google.gson.annotations.SerializedName;

public  class MessageResponce {

    @SerializedName("id")
    public String id;
    @SerializedName("message")
    public String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageResponce{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
