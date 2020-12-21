package com.m90.badshahandicappertips.home.api;

import com.m90.badshahandicappertips.home.model.ExpirydateResponce;
import com.m90.badshahandicappertips.home.model.MessageResponce;
import com.m90.badshahandicappertips.otp.model.OTPResponce;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface HomeMessageApi {

    @FormUrlEncoded
    @PUT("message/1")
    Call<MessageResponce> getMessage(
            @Field("id") String id
    );


    //http://badsha.avistore.in/public/api/expire?mobile=8669176540
    @FormUrlEncoded
    @POST("expire")
    Call<ArrayList<ExpirydateResponce>> getExpiryDate(
            @Field("mobile") String mobile
    );
}
