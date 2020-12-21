package com.m90.badshahandicappertips.general;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.m90.badshahandicappertips.R;
import com.m90.badshahandicappertips.databinding.ActivityTrackBinding;
import com.m90.badshahandicappertips.general.adapter.NumberDetailsRoundAdapter;
import com.m90.badshahandicappertips.general.adapter.RacecardAdapter;
import com.m90.badshahandicappertips.general.adapter.TrackAdapter;
import com.m90.badshahandicappertips.general.api.IsRaceAvailableApi;
import com.m90.badshahandicappertips.general.api.TrackApi;
import com.m90.badshahandicappertips.general.model.PlateDetailsModel;
import com.m90.badshahandicappertips.general.model.TotalPlateNumberDetailsModel;
import com.m90.badshahandicappertips.general.model.TrackModel;
import com.m90.badshahandicappertips.home.model.HomeModel;
import com.m90.badshahandicappertips.retrofit.RetrofitClientInstance;
import com.m90.badshahandicappertips.utils.Utilities;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackworkActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityTrackBinding binding;
    // quiz
    private int[] myImageList = new int[]{R.drawable.bb1, R.drawable.logo,R.drawable.logo,
            R.drawable.logo,R.drawable.logo,
            R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo};

    private String[] myImageNameList = new String[]{"Quiz1","Quiz2","Quiz3","Quiz4","Quiz5","Quiz6","Quiz7","Quiz7","Quiz7"};
    private String[] roundList = new String[]{"8","7","6","5","4","3","2","1"};

    ArrayList<HomeModel> imageModelYouTubeArrayList ;
   // ArrayList<TotalPlateNumberDetailsModel> roundarrayList ;
    ArrayList<TotalPlateNumberDetailsModel> totalPlateNumberDetailsModels;
    ArrayList<TrackModel> trackModels;
    RacecardAdapter racecardAdapter;
    NumberDetailsRoundAdapter numberDetailsRoundAdapter;
    TrackAdapter trackAdapter;
    String rvenue, rdate, tnumber="1";
    Activity activity;
    ProgressDialog progressDialog;

    private ArrayList<HashMap<String, String>> subjectContentList;

    String chk="chk";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_track);

        activity= TrackworkActivity.this;
        progressDialog=new ProgressDialog(TrackworkActivity.this);
        rvenue=getIntent().getStringExtra("rvenue");
        rdate=getIntent().getStringExtra("rdate");
        Log.e( "onCreate: ",rvenue+"    "+rdate );

        binding.txtTitle.setText("Trackwork");

        plateNumberDetails(rvenue,rdate);

        plateDetails(rvenue,rdate,"1");
        trackWork(rvenue, rdate, "1");
        binding.txtRaceNo.setText("Race No. 1");
        binding.imgBack.setOnClickListener(this);
      //  Toast.makeText(activity, "1111", Toast.LENGTH_SHORT).show();
    }


    private ArrayList<HomeModel> arrayDailyQuiz(){

        ArrayList<HomeModel> list = new ArrayList<>();

        for(int i = 0; i < 9; i++){
            HomeModel homeModel = new HomeModel();
            homeModel.setName(myImageNameList[i]);
          //  homeModel.setImage_drawable(myImageList[i]);
            list.add(homeModel);
        }

        return list;
    }

   /* private ArrayList<RoundModel> arrayRound(){

        ArrayList<RoundModel> list = new ArrayList<>();

        for(int i = 0; i < 8; i++){
            RoundModel homeModel = new RoundModel();
            homeModel.setName(roundList[i]);
          //  homeModel.setImage_drawable(myImageList[i]);
            list.add(homeModel);
        }

        return list;
    }*/

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            default:
                break;

          case R.id.img_back:

              onBackPressed();
                break;

           /*   case R.id.rl_subjects:
                drl_Opener.closeDrawers();
                Utility.launchActivity(HomeActivity.this, HomeActivity.class,
                        false);

                break;*/

        }
    }



    void plateDetails(String venue,  String date, String tableno)
    {
        if(Utilities.isNetworkAvailable(activity))
        {
            IsRaceAvailableApi isRaceAvailableApi = RetrofitClientInstance.getRetrofitInstanceServer().
                    create(IsRaceAvailableApi.class);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            isRaceAvailableApi.plateDetails(venue, date, tableno).
                    enqueue(new Callback<ArrayList<PlateDetailsModel>>() {

                        @Override
                        public void onResponse(Call<ArrayList<PlateDetailsModel>> call,
                                               Response<ArrayList<PlateDetailsModel>> response) {

                            ArrayList<PlateDetailsModel> plateDetailsModels = response.body();

                             Log.e("PlateDetailsModel: ", plateDetailsModels.get(0).toString());
                           // String active= isPaidModels.get(0).active1;

                            binding.txtRaceNo.setText("Race No. "+tableno);
                            binding.txtNumber1.setText(rvenue);
                            binding.txtNumber2.setText(rdate);
                            binding.txtTitle1.setText(plateDetailsModels.get(0).main1);
                            binding.txtTitle2.setText(plateDetailsModels.get(0).subm);
                            binding.txtDistance.setText(plateDetailsModels.get(0).dista);
                            binding.txtTime.setText(plateDetailsModels.get(0).rtime);
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<ArrayList<PlateDetailsModel>> call, Throwable t) {

                            progressDialog.dismiss();
                            //Utilities.setError(layout1,layout2,txt_error,getResources().getString(R.string.something_went_wrong));
                            Log.d("errorchk",t.getMessage());

                        }
                    });


        }
        else {

            Toast.makeText(activity, getResources().getString(R.string.check_internet), Toast.LENGTH_SHORT).show();
            //Utilities.setError(layout1,layout2,txt_error,getResources().getString(R.string.check_internet));

        }
    }

    void plateNumberDetails(String venue,  String date)
    {
        if(Utilities.isNetworkAvailable(activity))
        {
            IsRaceAvailableApi isRaceAvailableApi = RetrofitClientInstance.getRetrofitInstanceServer().
                    create(IsRaceAvailableApi.class);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            isRaceAvailableApi.plateNumbers(venue, date).
                    enqueue(new Callback<ArrayList<TotalPlateNumberDetailsModel>>() {

                        @Override
                        public void onResponse(Call<ArrayList<TotalPlateNumberDetailsModel>> call,
                                               Response<ArrayList<TotalPlateNumberDetailsModel>> response) {

                             totalPlateNumberDetailsModels = response.body();
                            // roundarrayList = response.body();

                             Log.e("totaletailsModels: ", totalPlateNumberDetailsModels.toString());
                           // String active= isPaidModels.get(0).active1;
                            bindList(totalPlateNumberDetailsModels);

                          //  roundarrayList = plateDetailsModels;

                            progressDialog.dismiss();
                        }


                        @Override
                        public void onFailure(Call<ArrayList<TotalPlateNumberDetailsModel>> call, Throwable t) {

                            progressDialog.dismiss();
                            //Utilities.setError(layout1,layout2,txt_error,getResources().getString(R.string.something_went_wrong));
                            Log.d("errorchk",t.getMessage());

                        }
                    });

//            Log.e( "roundarrayList: ", totalPlateNumberDetailsModels.toString() );



        }
        else {

            Toast.makeText(activity, getResources().getString(R.string.check_internet), Toast.LENGTH_SHORT).show();
            //Utilities.setError(layout1,layout2,txt_error,getResources().getString(R.string.check_internet));

        }
    }

    private void bindList(final ArrayList<TotalPlateNumberDetailsModel> totalPlateNumberDetailsModels) {
        Log.e("adapter called","yes");
        try {

            binding.rlRound.setLayoutManager(new LinearLayoutManager(TrackworkActivity.this,
                    LinearLayoutManager.HORIZONTAL, false));
            binding.rlRound.setItemAnimator(new DefaultItemAnimator());
            binding.rlRound.setHasFixedSize(true);

            numberDetailsRoundAdapter = new NumberDetailsRoundAdapter(this, totalPlateNumberDetailsModels,
                    new NumberDetailsRoundAdapter.ItemClickListener() {
                        @Override
                        public void onClick(View view, int position) {

                            plateDetails(rvenue,rdate,totalPlateNumberDetailsModels.get(position).r);
                           // String url=getResources().getString(R.string.base_url)+"TraceWork?venue="+rvenue+"&date="+rdate+"&table_no="+totalPlateNumberDetailsModels.get(position).r;

                            trackWork(rvenue, rdate, totalPlateNumberDetailsModels.get(position).r);

                        }
                    });
            binding.rlRound.setAdapter(numberDetailsRoundAdapter);
        } catch (Exception ex) {
            //Utility.showErrorMessage(HomepageActivity.this, "No record found", Snackbar.LENGTH_LONG);
        }
    }

    private void bindListTrack(final ArrayList<TrackModel> trackModels) {
        Log.e("chkar",trackModels.toString());
        try {

            binding.rlAchives.setLayoutManager(new LinearLayoutManager(TrackworkActivity.this,
                    LinearLayoutManager.VERTICAL, false));
            binding.rlAchives.setItemAnimator(new DefaultItemAnimator());
            binding.rlAchives.setHasFixedSize(true);

            trackAdapter = new TrackAdapter(this, trackModels);
            binding.rlAchives.setAdapter(trackAdapter);
        } catch (Exception ex) {
            //Utility.showErrorMessage(HomepageActivity.this, "No record found", Snackbar.LENGTH_LONG);
        }
    }



    void trackWork(String venue,  String date, String table_no)
    {
        if(Utilities.isNetworkAvailable(activity))
        {
            TrackApi trackApi = RetrofitClientInstance.getRetrofitInstanceServer().
                    create(TrackApi.class);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            trackApi.getTrack(venue, date, table_no).
                    enqueue(new Callback<ArrayList<TrackModel>>() {

                        @Override
                        public void onResponse(Call<ArrayList<TrackModel>> call,
                                               Response<ArrayList<TrackModel>> response) {

                            trackModels = response.body();
                            // roundarrayList = response.body();

                            Log.e("totaletailsModels: ", trackModels.toString());
                            // String active= isPaidModels.get(0).active1;
                            bindListTrack(trackModels);



                            progressDialog.dismiss();


                        }


                        @Override
                        public void onFailure(Call<ArrayList<TrackModel>> call, Throwable t) {

                            progressDialog.dismiss();
                            //Utilities.setError(layout1,layout2,txt_error,getResources().getString(R.string.something_went_wrong));
                            Log.d("errorchk",t.getMessage());

                        }
                    });

//            Log.e( "roundarrayList: ", totalPlateNumberDetailsModels.toString() );



        }
        else {

            Toast.makeText(activity, getResources().getString(R.string.check_internet), Toast.LENGTH_SHORT).show();
            //Utilities.setError(layout1,layout2,txt_error,getResources().getString(R.string.check_internet));

        }
    }



}