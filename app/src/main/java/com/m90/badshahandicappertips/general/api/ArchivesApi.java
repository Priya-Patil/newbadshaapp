package com.m90.badshahandicappertips.general.api;

import com.m90.badshahandicappertips.general.model.ArchivesModel;
import com.m90.badshahandicappertips.general.model.IsPaidModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ArchivesApi {

    @FormUrlEncoded
    @POST("ach")
    Call<ArrayList<ArchivesModel>> archives(
             @Field("id") String id
    );

    @FormUrlEncoded
    @POST("city1")
    Call<ArrayList<ArchivesModel>> getVenue(
             @Field("venue") String venue
    );
}
