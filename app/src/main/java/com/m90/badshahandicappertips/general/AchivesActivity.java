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
import com.m90.badshahandicappertips.constants.Utility;
import com.m90.badshahandicappertips.databinding.ActivityAchivesBinding;
import com.m90.badshahandicappertips.general.adapter.AchivesAdapter;
import com.m90.badshahandicappertips.general.api.ArchivesApi;
import com.m90.badshahandicappertips.general.model.ArchivesModel;
import com.m90.badshahandicappertips.home.HomeActivity;
import com.m90.badshahandicappertips.home.model.HomeModel;
import com.m90.badshahandicappertips.otp.OTPActivity;
import com.m90.badshahandicappertips.otp.api.OTPApi;
import com.m90.badshahandicappertips.otp.model.OTPResponce;
import com.m90.badshahandicappertips.retrofit.RetrofitClientInstance;
import com.m90.badshahandicappertips.utils.Utilities;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AchivesActivity extends AppCompatActivity implements View.OnClickListener {


    ActivityAchivesBinding binding;
    // quiz
    private int[] myImageList = new int[]{R.drawable.bb1, R.drawable.logo,R.drawable.logo,
            R.drawable.logo,R.drawable.logo,
            R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo};

    private String[] myImageNameList = new String[]{"Quiz1","Quiz2","Quiz3","Quiz4","Quiz5","Quiz6","Quiz7","Quiz7","Quiz7"};

 //   ArrayList<ArchivesModel> imageModelYouTubeArrayList ;
    AchivesAdapter achivesAdapter;
    Activity activity;
    ProgressDialog progressDialog;
    ArrayList<ArchivesModel> newlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_achives);

        activity=AchivesActivity.this;
        progressDialog=new ProgressDialog(AchivesActivity.this);

        //job alerts
        checkArchives("0");
        binding.imgBack.setOnClickListener(this);
        binding.txt1.setOnClickListener(this);
        binding.txt2.setOnClickListener(this);


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

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            default:
                break;

           case R.id.img_back:
                onBackPressed();
                break;

            case R.id.txt1:
                Utility.launchActivity(AchivesActivity.this, PrivacyActivity.class,false);
                break;

            case R.id.txt2:
                Utility.launchActivity(AchivesActivity.this, AboutActivity.class,false);
                break;

          /*   case R.id.rl_subjects:
                drl_Opener.closeDrawers();
                Utility.launchActivity(HomeActivity.this, HomeActivity.class,
                        false);

                break;*/

        }
    }

    void checkArchives(String id)
    {
        if(Utilities.isNetworkAvailable(activity))
        {

            newlist = new ArrayList<>();

            ArchivesApi archivesApi = RetrofitClientInstance.getRetrofitInstanceServer().
                    create(ArchivesApi.class);

            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            // pbLoading.setProgressStyle(R.id.abbreviationsBar);
            progressDialog.show();
            archivesApi.archives(id).
                    enqueue(new Callback<ArrayList<ArchivesModel>>() {

                        @Override
                        public void onResponse(Call<ArrayList<ArchivesModel>> call, Response<ArrayList<ArchivesModel>> response) {

                            ArrayList<ArchivesModel> archivesModels = response.body();


                            for (int i=0; i<archivesModels.size();i++)
                            {

                                Log.e("chkdate: ", archivesModels.get(0).date);

                                ArchivesModel archivesModel = new ArchivesModel();
                                archivesModel.setVenue(archivesModels.get(i).venue);
                                archivesModel.setDate(archivesModels.get(i).date);
                                archivesModel.setId(archivesModels.get(i).id);
                                newlist.add(archivesModel);
                               // Log.e( "onResponse: ", archivesModels.get(i).venue);
                            }
                            progressDialog.dismiss();

                            Log.e( "onResponse: ", newlist.toString());

                           // imageModelYouTubeArrayList = newlist;
                            binding.rlAchives.setLayoutManager(new LinearLayoutManager(AchivesActivity.this,
                                    LinearLayoutManager.VERTICAL, true));
                            binding.rlAchives.setItemAnimator(new DefaultItemAnimator());
                            binding.rlAchives.setHasFixedSize(true);

                            achivesAdapter = new AchivesAdapter(AchivesActivity.this, newlist);
                            /*  achivesAdapter = new AchivesAdapter(AchivesActivity.this, newlist,
                                    new AchivesAdapter.ItemClickListener() {
                                        @Override
                                        public void onClick(View view, int position) {


                                            Toast.makeText(AchivesActivity.this, " "+newlist.get(position).getVenue(), Toast.LENGTH_SHORT).show();
                                            Bundle bundle=new Bundle();
                                            bundle.putString("Videopackageid", String.valueOf(imageModelYouTubeArrayList.get(position).getVenue()));
                                            //
                                            Utility.launchActivity(AchivesActivity.this, RaceCardActivity.class, false);

                                        }
                                    });*/
                            binding.rlAchives.setAdapter(achivesAdapter);
                        }


                        @Override
                        public void onFailure(Call<ArrayList<ArchivesModel>> call, Throwable t) {

                            progressDialog.dismiss();
                            //Utilities.setError(layout1,layout2,txt_error,getResources().getString(R.string.something_went_wrong));
                            Log.d("errorchk",t.getMessage());

                        }
                    });
        }
        else {

            Toast.makeText(activity, getResources().getString(R.string.check_internet), Toast.LENGTH_SHORT).show();

        }
    }
}