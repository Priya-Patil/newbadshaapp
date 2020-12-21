package com.m90.badshahandicappertips.otp.api;

import com.m90.badshahandicappertips.otp.model.OTPResponce;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OTPApi {


    @FormUrlEncoded
    @POST("user")
    Call<OTPResponce> checkLogin (
            @Field("mobile") String mobile,
            @Field("active1") String active
            // lang: eng/mar

    );
}
