package com.m90.badshahandicappertips.general.api;

import com.m90.badshahandicappertips.general.model.IsPaidModel;
import com.m90.badshahandicappertips.general.model.IsRaceAvailableModel;
import com.m90.badshahandicappertips.general.model.PlateDetailsModel;
import com.m90.badshahandicappertips.general.model.TotalPlateNumberDetailsModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IsRaceAvailableApi {

    @FormUrlEncoded
    @POST("city")
    Call<ArrayList<IsRaceAvailableModel>> checkRace(
             @Field("id") String id

    );

    //http://badsha.cricketnewshub.xyz/public/api/tbl?venue=mysor&date=2020-11-25&table_no=1

    @FormUrlEncoded
    @POST("tbl")
    Call<ArrayList<PlateDetailsModel>> plateDetails(
             @Field("venue") String venue,
             @Field("date") String date,
             @Field("table_no") String table_no
    );

    //http://badsha.cricketnewshub.xyz/public/api/total?venue=mysor&date=2020-11-25
    @FormUrlEncoded
    @POST("total")
    Call<ArrayList<TotalPlateNumberDetailsModel>> plateNumbers(
             @Field("venue") String venue,
             @Field("date") String date
    );
}
