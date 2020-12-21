package com.m90.badshahandicappertips.general.api;

import com.m90.badshahandicappertips.general.model.IsPaidModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CheckIsPaidApi {

    @FormUrlEncoded
    @POST("check")
    Call<ArrayList<IsPaidModel>> checkRace(
           /* @Field("id") String id*/
            @Field("mobile") String mobile
    );
}
