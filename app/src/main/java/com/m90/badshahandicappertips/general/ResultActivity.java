package com.m90.badshahandicappertips.general;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.m90.badshahandicappertips.R;
import com.m90.badshahandicappertips.databinding.ActivityRaceCardBinding;
import com.m90.badshahandicappertips.general.adapter.NumberDetailsRoundAdapter;
import com.m90.badshahandicappertips.general.adapter.RacecardAdapter;
import com.m90.badshahandicappertips.general.api.IsRaceAvailableApi;
import com.m90.badshahandicappertips.general.model.PlateDetailsModel;
import com.m90.badshahandicappertips.general.model.RoundModel;
import com.m90.badshahandicappertips.general.model.TotalPlateNumberDetailsModel;
import com.m90.badshahandicappertips.home.model.HomeModel;
import com.m90.badshahandicappertips.retrofit.RetrofitClientInstance;
import com.m90.badshahandicappertips.utils.Utilities;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityRaceCardBinding binding;
    // quiz
    private int[] myImageList = new int[]{R.drawable.bb1, R.drawable.logo,R.drawable.logo,
            R.drawable.logo,R.drawable.logo,
            R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo};

    private String[] myImageNameList = new String[]{"Quiz1","Quiz2","Quiz3","Quiz4","Quiz5","Quiz6","Quiz7","Quiz7","Quiz7"};
    private String[] roundList = new String[]{"8","7","6","5","4","3","2","1"};

    ArrayList<HomeModel> imageModelYouTubeArrayList ;
   // ArrayList<TotalPlateNumberDetailsModel> roundarrayList ;
    ArrayList<TotalPlateNumberDetailsModel> totalPlateNumberDetailsModels;
    RacecardAdapter racecardAdapter;
    NumberDetailsRoundAdapter numberDetailsRoundAdapter;
    String rvenue, rdate, tnumber="1", active1;
    Activity activity;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_race_card);

        activity= ResultActivity.this;
        progressDialog=new ProgressDialog(ResultActivity.this);
        rvenue=getIntent().getStringExtra("rvenue");
        rdate=getIntent().getStringExtra("rdate");
        active1=getIntent().getStringExtra("active1");
       // active1=getIntent().getStringExtra("active1");
        Log.e( "onCreate: ",rvenue+"    "+rdate );

        binding.txtTitle.setText("Result");
        plateNumberDetails(rvenue,rdate);
        plateDetails(rvenue,rdate,"1");
       // String url="http://badsha.cricketnewshub.xyz/public/api/racecarda?venue="+rvenue+"&date="+rdate+"&table_no=1";
        String url=getResources().getString(R.string.base_url)+"result?active1="+active1+"&venue="+rvenue+"&date="+rdate+"&table_no=1";
        Log.e( "urlonCreate: ", url);
        loadWebViewLoad(binding.webview, url);
        binding.txtRaceNo.setText("Race No. 1");

        binding.imgBack.setOnClickListener(this);
    }

    private void loadWebViewLoad(WebView webview, String url) {
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setSupportMultipleWindows(true);
        webview.setWebViewClient(new WebViewClient());
        webview.setWebChromeClient(new WebChromeClient());
        webview.loadUrl(url);
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

    /*private ArrayList<RoundModel> arrayRound(){

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

            binding.rlRound.setLayoutManager(new LinearLayoutManager(ResultActivity.this,
                    LinearLayoutManager.HORIZONTAL, false));
            binding.rlRound.setItemAnimator(new DefaultItemAnimator());
            binding.rlRound.setHasFixedSize(true);

            numberDetailsRoundAdapter = new NumberDetailsRoundAdapter(this, totalPlateNumberDetailsModels,
                    new NumberDetailsRoundAdapter.ItemClickListener() {
                        @Override
                        public void onClick(View view, int position) {

                            plateDetails(rvenue,rdate,totalPlateNumberDetailsModels.get(position).r);

                            String url=getResources().getString(R.string.base_url)+"result?active1="+active1+"&venue="+rvenue+"&date="+rdate+"&table_no="+totalPlateNumberDetailsModels.get(position).r;
                            loadWebViewLoad(binding.webview, url);
                           // Toast.makeText(RaceCardActivity.this, "aa"+totalPlateNumberDetailsModels.get(position).r, Toast.LENGTH_SHORT).show();

                        }
                    });
            binding.rlRound.setAdapter(numberDetailsRoundAdapter);
        } catch (Exception ex) {
            //Utility.showErrorMessage(HomepageActivity.this, "No record found", Snackbar.LENGTH_LONG);
        }
    }


}