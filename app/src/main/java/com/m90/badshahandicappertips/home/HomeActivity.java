package com.m90.badshahandicappertips.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.m90.badshahandicappertips.databinding.ActivityHomeBinding;
import com.m90.badshahandicappertips.general.AboutActivity;
import com.m90.badshahandicappertips.general.PrivacyActivity;
import com.m90.badshahandicappertips.R;
import com.m90.badshahandicappertips.constants.Utility;
import com.m90.badshahandicappertips.general.AchivesActivity;
import com.m90.badshahandicappertips.general.SubScriptionActivity;
import com.m90.badshahandicappertips.general.model.ArchivesModel;
import com.m90.badshahandicappertips.home.adapter.CitynameHomeAdapter;
import com.m90.badshahandicappertips.home.api.HomeMessageApi;
import com.m90.badshahandicappertips.home.model.ExpirydateResponce;
import com.m90.badshahandicappertips.home.model.HomeModel;
import com.m90.badshahandicappertips.home.model.MessageResponce;
import com.m90.badshahandicappertips.retrofit.RetrofitClientInstance;
import com.m90.badshahandicappertips.utils.PrefManager;
import com.m90.badshahandicappertips.utils.Utilities;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements  View.OnClickListener{


    ActivityHomeBinding binding;
    // quiz
    private int[] myImageList = new int[]{R.drawable.bb1, R.drawable.bb2,R.drawable.bb3,
            R.drawable.bb4,R.drawable.bb5,
            R.drawable.bb6,R.drawable.bb7,R.drawable.bb8,R.drawable.bb9};

    private String[] myImageNameList = new String[]{"1","2","3","4","5","6","7","8","9"};

    ArrayList<HomeModel>   imageModelYouTubeArrayList ;
    CitynameHomeAdapter dailyQuizAdapter;

    Activity activity;
    ProgressDialog progressDialog;
    PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        activity=HomeActivity.this;
        progressDialog= new ProgressDialog(HomeActivity.this);
        prefManager= new PrefManager(HomeActivity.this);

        setCity();
        binding.submit1.setOnClickListener(this);
        binding.txt1.setOnClickListener(this);
        binding.txt2.setOnClickListener(this);
        getMessage("1");
        checkExpiry(prefManager.getMobile());
    }

    void setCity()
    {
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        imageModelYouTubeArrayList = arrayDailyQuiz();
        GridLayoutManager glm = new GridLayoutManager(getApplicationContext(), 3);
        binding.recyclerViewCourses.setLayoutManager(glm);
        binding.recyclerViewCourses.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerViewCourses.setHasFixedSize(true);

        dailyQuizAdapter = new CitynameHomeAdapter(this, imageModelYouTubeArrayList,
                new CitynameHomeAdapter.ItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                         prefManager.setCityide(imageModelYouTubeArrayList.get(position).getName());
                         Bundle bundle=new Bundle();
                        bundle.putString("cityid", imageModelYouTubeArrayList.get(position).getName());
                        Utility.launchActivity(HomeActivity.this, SubScriptionActivity.class,
                                false, bundle);

                    //12-11  showDialogNoRecord();
                    }
                });
        binding.recyclerViewCourses.setAdapter(dailyQuizAdapter);
        progressDialog.dismiss();
    }

    private ArrayList<HomeModel> arrayDailyQuiz(){

        ArrayList<HomeModel> list = new ArrayList<>();

        for(int i = 0; i < 9; i++){
            HomeModel homeModel = new HomeModel();
            homeModel.setName(myImageNameList[i]);
            homeModel.setImage_drawable(myImageList[i]);
            list.add(homeModel);
        }

        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;

           case R.id.submit1:
               Utility.launchActivity(HomeActivity.this, AchivesActivity.class,false);
              //18-11 showDialogNoRecord();

               break;

           case R.id.txt1:

               Utility.launchActivity(HomeActivity.this, PrivacyActivity.class,false);
                break;

            case R.id.txt2:
                Utility.launchActivity(HomeActivity.this, AboutActivity.class,false);
                break;

          /*   case R.id.rl_subjects:
                drl_Opener.closeDrawers();
                Utility.launchActivity(HomeActivity.this, HomeActivity.class,
                        false);
                break;*/

        }
    }
    void getMessage(String id)
    {
        if(Utilities.isNetworkAvailable(activity))
        {
            HomeMessageApi homeMessageApi = RetrofitClientInstance.getRetrofitInstanceServer().
                    create(HomeMessageApi.class);

            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            homeMessageApi.getMessage(id).
                    enqueue(new Callback<MessageResponce>() {

                        @Override
                        public void onResponse(Call<MessageResponce> call, Response<MessageResponce> response) {

                            MessageResponce messageResponce = response.body();

                             Log.e("chkres: ", messageResponce.toString());
                            binding.txtMarquee.setText(messageResponce.getMessage());
                            binding.txtMarquee.setSelected(true);

                            progressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<MessageResponce> call, Throwable t) {

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
    void checkExpiry(String mobile)
    {
        if(Utilities.isNetworkAvailable(activity))
        {
            HomeMessageApi homeMessageApi = RetrofitClientInstance.getRetrofitInstanceServer().
                    create(HomeMessageApi.class);

            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            homeMessageApi.getExpiryDate(mobile).
                    enqueue(new Callback<ArrayList<ExpirydateResponce>>() {

                        @Override
                        public void onResponse(Call<ArrayList<ExpirydateResponce>> call, Response<ArrayList<ExpirydateResponce>> response) {

                            ArrayList<ExpirydateResponce> expirydateResponces = response.body();
                            Log.e( "onResponse: ",  expirydateResponces.toString());

                            if(expirydateResponces.isEmpty())
                            {
                                binding.txtExpirydate.setText("Click on city and continue for subscription");
                            }
                            else {
                                for (int i = 0; i < expirydateResponces.size(); i++) {

                                    Log.e("chkexpiry: ", expirydateResponces.get(0).date);
                                    binding.txtExpirydate.setText("Expiry date: "+expirydateResponces.get(0).date);
                                    binding.txtExpirydate.setSelected(true);
                                    progressDialog.dismiss();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<ExpirydateResponce>> call, Throwable t) {

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

    private void showDialogNoRecord() {
        // this.correct = correct;
        final Dialog resultbox = new Dialog(this);
        resultbox.setContentView(R.layout.custom_dialog_home);
        resultbox.setCanceledOnTouchOutside(false);
        Button btn_finish = (Button) resultbox.findViewById(R.id.btn_finish);
        Button btn_cancel = (Button) resultbox.findViewById(R.id.btn_resume);
        TextView text_assign = resultbox.findViewById(R.id.text_title);
        text_assign.setText(getResources().getString(R.string.message));

        btn_finish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                resultbox.cancel();
               // onBackPressed();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultbox.cancel();
            }
        });

        resultbox.show();
    }

}