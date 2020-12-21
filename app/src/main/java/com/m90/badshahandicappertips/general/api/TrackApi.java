package com.m90.badshahandicappertips.general.api;

import com.m90.badshahandicappertips.general.model.ArchivesModel;
import com.m90.badshahandicappertips.general.model.TrackModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TrackApi {

    @FormUrlEncoded
    @POST("track")
    Call<ArrayList<TrackModel>> getTrack(
            @Field("venue") String venue,
            @Field("date") String date,
            @Field("table_no") String table_no
    );

}
