package com.m90.badshahandicappertips.general;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.m90.badshahandicappertips.R;
import com.m90.badshahandicappertips.constants.SessionHelper;
import com.m90.badshahandicappertips.constants.Utility;
import com.m90.badshahandicappertips.databinding.ActivitySecondBinding;
import com.m90.badshahandicappertips.general.adapter.SubscribeAdapter;
import com.m90.badshahandicappertips.general.api.ArchivesApi;
import com.m90.badshahandicappertips.general.api.IsRaceAvailableApi;
import com.m90.badshahandicappertips.general.model.ArchivesModel;
import com.m90.badshahandicappertips.general.model.IsRaceAvailableModel;
import com.m90.badshahandicappertips.home.HomeActivity;
import com.m90.badshahandicappertips.home.model.HomeModel;
import com.m90.badshahandicappertips.retrofit.RetrofitClientInstance;
import com.m90.badshahandicappertips.splash.SplashActivity;
import com.m90.badshahandicappertips.utils.PrefManager;
import com.m90.badshahandicappertips.utils.Utilities;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewSubsribeActivity extends AppCompatActivity implements View.OnClickListener {


    ActivitySecondBinding binding;
    // quiz
    private int[] myImageList = new int[]{R.drawable.lnew, R.drawable.lnew,
            R.drawable.lnew,
            R.drawable.lnew,R.drawable.lnew,
            R.drawable.lnew,R.drawable.lnew,R.drawable.lnew,R.drawable.lnew};

    private String[] myImageNameList = new String[]{"RESULT","BADSHA HANDICAPPER\n         CHOICES",
            "DAY BEST HORSE","MEDIA TIPS","BEND POSITION RATING","SPEED RATING","SCALE RATING","TRACK WORK",
            "RACE CARD"
            };

    ArrayList<HomeModel> imageModelYouTubeArrayList ;
    SubscribeAdapter subscribeAdapter;
    RelativeLayout rl_home,rl_center, rl_archives, rl_RaceCard, rl_TrackWork, rl_ScaleRating,
            rl_SpeedRating, rl_BendPositionRating,rl_MediaChoice,rl_BestTips, rl_Choices,rl_Results,
            rl_QueryFeedback,rl_PrivacyPolicy, rl_Logout;

    String city;
    SessionHelper sessionHelper;
    PrefManager prefManager;
    Activity activity;
    ProgressDialog progressDialog;
    String rvenue, rdate;
    void btnListener()
    {
        binding.imgOpenDrawer.setOnClickListener(this);
        rl_home.setOnClickListener(this);
        rl_center.setOnClickListener(this);
        rl_archives.setOnClickListener(this);
        rl_RaceCard.setOnClickListener(this);
        rl_TrackWork.setOnClickListener(this);
        rl_ScaleRating.setOnClickListener(this);
        rl_SpeedRating.setOnClickListener(this);
        rl_BendPositionRating.setOnClickListener(this);
        rl_MediaChoice.setOnClickListener(this);
        rl_BestTips.setOnClickListener(this);
        rl_Choices.setOnClickListener(this);
        rl_Results.setOnClickListener(this);
        rl_QueryFeedback.setOnClickListener(this);
        rl_PrivacyPolicy.setOnClickListener(this);
        rl_Logout.setOnClickListener(this);
    }
    void bindViews()
    {
         rl_home=findViewById(R.id.rl_home);
        rl_center=findViewById(R.id.rl_center);
        rl_archives=findViewById(R.id.rl_archives);
        rl_RaceCard=findViewById(R.id.rl_RaceCard);
        rl_TrackWork=findViewById(R.id.rl_TrackWork);
        rl_ScaleRating=findViewById(R.id.rl_ScaleRating);
        rl_SpeedRating=findViewById(R.id.rl_SpeedRating);
        rl_BendPositionRating=findViewById(R.id.rl_BendPositionRating);
        rl_MediaChoice=findViewById(R.id.rl_MediaChoice);
        rl_BestTips=findViewById(R.id.rl_BestTips);
        rl_Choices=findViewById(R.id.rl_Choices);
        rl_Results=findViewById(R.id.rl_Results);
        rl_QueryFeedback=findViewById(R.id.rl_QueryFeedback);
        rl_PrivacyPolicy=findViewById(R.id.rl_PrivacyPolicy);
        rl_Logout=findViewById(R.id.rl_Logout);
        sessionHelper=new SessionHelper(NewSubsribeActivity.this);
        prefManager=new PrefManager(NewSubsribeActivity.this);
        progressDialog=new ProgressDialog(NewSubsribeActivity.this);
        activity=NewSubsribeActivity.this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second);

        bindViews();
        btnListener();

        imageModelYouTubeArrayList = arrayDailyQuiz();
        binding.rlCard.setLayoutManager(new LinearLayoutManager(NewSubsribeActivity.this,
                LinearLayoutManager.VERTICAL, true));
        binding.rlCard.setItemAnimator(new DefaultItemAnimator());
        binding.rlCard.setHasFixedSize(true);
        binding.txt1.setOnClickListener(this);
        binding.txt2.setOnClickListener(this);

        subscribeAdapter = new SubscribeAdapter(this, imageModelYouTubeArrayList,
                new SubscribeAdapter.ItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        if(imageModelYouTubeArrayList.get(position).getName().equals("RESULT"))
                        {
                            Toast.makeText(NewSubsribeActivity.this, ""+imageModelYouTubeArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
                            Bundle bundle2=new Bundle();
                            Toast.makeText(NewSubsribeActivity.this, "Result", Toast.LENGTH_SHORT).show();
                            bundle2.putString("rvenue", rvenue);
                            bundle2.putString("rdate", rdate);
                            bundle2.putString("active1", "1");
                            Utility.launchActivity(NewSubsribeActivity.this, ResultActivity.class,
                                    false, bundle2);

                        }

                        if(imageModelYouTubeArrayList.get(position).getName().equals("BADSHA HANDICAPPER\n         CHOICES"))
                        {
                            Toast.makeText(NewSubsribeActivity.this, ""+imageModelYouTubeArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
                           /* Bundle bundle=new Bundle();
                            bundle.putString("url", getResources().getString(R.string.base_url)+"BADSHAHANDICAPPERCHOICES");
                            Utility.launchActivity(NewSubsribeActivity.this, GeneralWebviewActivity.class,
                                    false, bundle);*/
                            Bundle bc=new Bundle();
                            bc.putString("rvenue", rvenue);
                            bc.putString("rdate", rdate);
                            bc.putString("url", getResources().getString(R.string.base_url)+"BADSHAHANDICAPPERCHOICES?active1=1&");
                            Utility.launchActivity(NewSubsribeActivity.this, RatingActivity.class,
                                    false, bc);
                        }

                        if(imageModelYouTubeArrayList.get(position).getName().equals("DAY BEST HORSE"))
                        {
                            Toast.makeText(NewSubsribeActivity.this, ""+imageModelYouTubeArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
                            Bundle bundle=new Bundle();
                            Log.e( "chkurl: ", getResources().getString(R.string.base_url)+"BestTips?active1=0&venue="+rvenue+"&date="+rdate);
                            bundle.putString("url", getResources().getString(R.string.base_url)+"BestTips?active1=1&venue="+rvenue+"&date="+rdate);
                            Utility.launchActivity(NewSubsribeActivity.this, GeneralWebviewActivity.class,
                                    false, bundle);
                        }

                        if(imageModelYouTubeArrayList.get(position).getName().equals("MEDIA TIPS"))
                        {
                            Toast.makeText(NewSubsribeActivity.this, ""+imageModelYouTubeArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
                            Bundle bundle=new Bundle();
                          //  bundle.putString("url", getResources().getString(R.string.base_url)+"MediaTips");
                            bundle.putString("url", getResources().getString(R.string.base_url)+"MediaTips?active1=1&venue="+rvenue+"&date="+rdate);
                            Utility.launchActivity(NewSubsribeActivity.this, GeneralWebviewActivity.class,
                                    false, bundle);
                        }

                        if(imageModelYouTubeArrayList.get(position).getName().equals("BEND POSITION RATING"))
                        {
                            Toast.makeText(NewSubsribeActivity.this, ""+imageModelYouTubeArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
                            Bundle bundle=new Bundle();
                            bundle.putString("rvenue", rvenue);
                            bundle.putString("rdate", rdate);
                            bundle.putString("url", getResources().getString(R.string.base_url)+"BendPositionRating?active1=1&");

                            Utility.launchActivity(NewSubsribeActivity.this, RatingActivity.class,
                                    false, bundle);
                        }
                        if(imageModelYouTubeArrayList.get(position).getName().equals("SPEED RATING"))
                        {
                            Toast.makeText(NewSubsribeActivity.this, ""+imageModelYouTubeArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
                            Bundle bundle=new Bundle();
                            bundle.putString("rvenue", rvenue);
                            bundle.putString("rdate", rdate);
                            bundle.putString("url", getResources().getString(R.string.base_url)+"SpeedRating?active1=1&");

                            Utility.launchActivity(NewSubsribeActivity.this, RatingActivity.class,
                                    false, bundle);
                        }
                        if(imageModelYouTubeArrayList.get(position).getName().equals("SCALE RATING"))
                        {
                            Log.e( "onCreate11: ",rvenue+"    "+rdate );

                            Toast.makeText(NewSubsribeActivity.this, ""+imageModelYouTubeArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
                            Bundle bundle=new Bundle();
                            bundle.putString("rvenue", rvenue);
                            bundle.putString("rdate", rdate);
                            bundle.putString("url", getResources().getString(R.string.base_url)+"ScaleRating?active1=1&");
                            Utility.launchActivity(NewSubsribeActivity.this, RatingActivity.class,
                                    false, bundle);
                        }
                        if(imageModelYouTubeArrayList.get(position).getName().equals("TRACK WORK"))
                        {
                            Toast.makeText(NewSubsribeActivity.this, ""+imageModelYouTubeArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
                            Bundle bundle=new Bundle();
                            bundle.putString("rvenue", rvenue);
                            bundle.putString("rdate", rdate);
                            Utility.launchActivity(NewSubsribeActivity.this, TrackworkActivity.class,
                                    false, bundle);

                        }if(imageModelYouTubeArrayList.get(position).getName().equals("RACE CARD"))
                        {
                            Toast.makeText(NewSubsribeActivity.this, ""+imageModelYouTubeArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
                            Bundle bundle=new Bundle();
                            bundle.putString("rvenue", rvenue);
                            bundle.putString("rdate", rdate);
                            bundle.putString("active1", "1");
                            Utility.launchActivity(NewSubsribeActivity.this, RaceCardActivity.class,
                                    false, bundle);
                        }
                    }
                });
        binding.rlCard.setAdapter(subscribeAdapter);


        if(prefManager.getCityid().equals("1"))
        {
            city="Pune";
        }
        if(prefManager.getCityid().equals("2"))
        {
            city="Mumbai";
        }
        if(prefManager.getCityid().equals("3"))
        {
            city="Bangalore";
        }
        if(prefManager.getCityid().equals("4"))
        {
            city="Hyderabad";
        }
        if(prefManager.getCityid().equals("5"))
        {
            city="Kolkata";
        }
        if(prefManager.getCityid().equals("6"))
        {
            city="Mysore";
        }
        if(prefManager.getCityid().equals("7"))
        {
            city="Madras";
        }
        if(prefManager.getCityid().equals("8"))
        {
            city="Delhi";
        }
        if(prefManager.getCityid().equals("9"))
        {
            city="Ooty";
        }


        getVenue(city);

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

          case R.id.img_openDrawer:

              binding.drlOpener.openDrawer(Gravity.LEFT);

              break;

              case R.id.rl_home:
                binding.drlOpener.closeDrawers();
                Utility.launchActivity(NewSubsribeActivity.this, HomeActivity.class,
                        false);
                break;

              case R.id.rl_center:
                binding.drlOpener.closeDrawers();
                Utility.launchActivity(NewSubsribeActivity.this, HomeActivity.class,
                        false);
                break;

             case R.id.rl_archives:
                binding.drlOpener.closeDrawers();
                Utility.launchActivity(NewSubsribeActivity.this, AchivesActivity.class,
                        false);
                 //Toast.makeText(this, "Bunty's part", Toast.LENGTH_SHORT).show();
                break;

             case R.id.rl_RaceCard:
                 binding.drlOpener.closeDrawers();
                 Toast.makeText(NewSubsribeActivity.this, "Race card", Toast.LENGTH_SHORT).show();
                 Bundle bundle=new Bundle();
                 bundle.putString("rvenue", rvenue);
                 bundle.putString("rdate", rdate);
                 bundle.putString("active1", "1");
                 Utility.launchActivity(NewSubsribeActivity.this, RaceCardActivity.class,false);

                 break;

             case R.id.rl_TrackWork:
                binding.drlOpener.closeDrawers();
                 Bundle b=new Bundle();
                 b.putString("rvenue", rvenue);
                 b.putString("rdate", rdate);
                 Utility.launchActivity(NewSubsribeActivity.this, TrackworkActivity.class,
                         false, b);

                /* Bundle bundle1=new Bundle();
                 bundle1.putString("url", getResources().getString(R.string.base_url)+"TraceWork?active1=0&venue="+rvenue+"&date="+rdate);
                  Utility.launchActivity(NewSubsribeActivity.this, GeneralWebviewActivity.class,
                             false, bundle1);
*/
                break;

             case R.id.rl_ScaleRating:
                binding.drlOpener.closeDrawers();
                     Toast.makeText(NewSubsribeActivity.this, "SCALE RATING", Toast.LENGTH_SHORT).show();
                 /* Bundle bs=new Bundle();
                  bs.putString("url", getResources().getString(R.string.base_url)+"ScaleRating?active1=0&venue="+rvenue+"&date="+rdate);
                  Utility.launchActivity(NewSubsribeActivity.this, GeneralWebviewActivity.class,
                           false, bs);
*/
                 Bundle bs=new Bundle();
                 bs.putString("rvenue", rvenue);
                 bs.putString("rdate", rdate);
                 bs.putString("url", getResources().getString(R.string.base_url)+"ScaleRating?active1=1&");
                 Utility.launchActivity(NewSubsribeActivity.this, RatingActivity.class,
                         false, bs);
                 break;


                case R.id.rl_SpeedRating:
                binding.drlOpener.closeDrawers();
                        Toast.makeText(NewSubsribeActivity.this, "SPEED RATING", Toast.LENGTH_SHORT).show();
                       /* Bundle bsp=new Bundle();
                        bsp.putString("url", getResources().getString(R.string.base_url)+"SpeedRating?active1=0&venue="+rvenue+"&date="+rdate);
                        Utility.launchActivity(NewSubsribeActivity.this, GeneralWebviewActivity.class,
                                false, bsp);*/
                    Bundle bsp=new Bundle();
                    bsp.putString("rvenue", rvenue);
                    bsp.putString("rdate", rdate);
                    bsp.putString("url", getResources().getString(R.string.base_url)+"SpeedRating?active1=1&");

                    Utility.launchActivity(NewSubsribeActivity.this, RatingActivity.class,
                            false, bsp);
               break;

             case R.id.rl_BendPositionRating:
                binding.drlOpener.closeDrawers();
                     Toast.makeText(NewSubsribeActivity.this, "BEND POSITION RATING", Toast.LENGTH_SHORT).show();
                    /* Bundle bbp=new Bundle();
                   bbp.putString("url", getResources().getString(R.string.base_url)+"BendPositionRating?active1=0&venue="+rvenue+"&date="+rdate);
                  Utility.launchActivity(NewSubsribeActivity.this, GeneralWebviewActivity.class,
                             false, bbp);*/

                 Bundle bbp=new Bundle();
                 bbp.putString("rvenue", rvenue);
                 bbp.putString("rdate", rdate);
                 bbp.putString("url", getResources().getString(R.string.base_url)+"BendPositionRating?active1=1&");

                 Utility.launchActivity(NewSubsribeActivity.this, RatingActivity.class,
                         false, bbp);

                break;

             case R.id.rl_MediaChoice:
                binding.drlOpener.closeDrawers();
                 Bundle bm=new Bundle();
                 bm.putString("url", getResources().getString(R.string.base_url)+"MediaTips?active1=1&venue="+rvenue+"&date="+rdate);
                 Utility.launchActivity(NewSubsribeActivity.this, GeneralWebviewActivity.class,
                         false, bm);
                break;
            case R.id.rl_BestTips:
                binding.drlOpener.closeDrawers();
                    Toast.makeText(NewSubsribeActivity.this, "DAY BEST HORSE", Toast.LENGTH_SHORT).show();
                    Bundle bb=new Bundle();
                Log.e( "chkurl: ", getResources().getString(R.string.base_url)+"BestTips?active1=1&venue="+rvenue+"&date="+rdate);
                bb.putString("url", getResources().getString(R.string.base_url)+"BestTips?active1=1&venue="+rvenue+"&date="+rdate);
                Utility.launchActivity(NewSubsribeActivity.this, GeneralWebviewActivity.class,
                            false, bb);

                break;

             case R.id.rl_Choices:
                binding.drlOpener.closeDrawers();
                    /* Bundle bc=new Bundle();
                 bc.putString("url", getResources().getString(R.string.base_url)+"BADSHAHANDICAPPERCHOICES?active1=0&venue="+rvenue+"&date="+rdate);
                 Utility.launchActivity(NewSubsribeActivity.this, GeneralWebviewActivity.class,false, bc);*/
                 Bundle bc=new Bundle();
                 bc.putString("rvenue", rvenue);
                 bc.putString("rdate", rdate);
                 bc.putString("url", getResources().getString(R.string.base_url)+"BADSHAHANDICAPPERCHOICES?active1=1&");
                 Utility.launchActivity(NewSubsribeActivity.this, RatingActivity.class,
                         false, bc);

                break;

             case R.id.rl_Results:
                 binding.drlOpener.closeDrawers();
                 Bundle bundle2=new Bundle();
                 Toast.makeText(NewSubsribeActivity.this, "Result", Toast.LENGTH_SHORT).show();
                 bundle2.putString("rvenue", rvenue);
                 bundle2.putString("rdate", rdate);
                 bundle2.putString("active1", "1");
                 Utility.launchActivity(NewSubsribeActivity.this, ResultActivity.class,
                         false, bundle2);

                 break;
             case R.id.rl_QueryFeedback:
                binding.drlOpener.closeDrawers();

                break;

             case R.id.rl_PrivacyPolicy:
                binding.drlOpener.closeDrawers();
                Utility.launchActivity(NewSubsribeActivity.this, PrivacyActivity.class,
                        false);
                break;
             case R.id.rl_TermsAndConditions:
                binding.drlOpener.closeDrawers();
                break;

             case R.id.rl_Guidlines:
                binding.drlOpener.closeDrawers();

                break;


            case R.id.txt1:

                Utility.launchActivity(this, PrivacyActivity.class,false);
                break;

            case R.id.txt2:
                Utility.launchActivity(this, AboutActivity.class,false);
                break;

                case R.id.rl_Logout:
                binding.drlOpener.closeDrawers();

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder( NewSubsribeActivity.this);

                    // set title
                    alertDialogBuilder.setTitle("Logout");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Click yes to logout!")
                            .setCancelable(false)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    sessionHelper.setLogin(false);
                                    Utility.launchActivity(NewSubsribeActivity.this, SplashActivity.class,
                                            true);
                                }
                            })
                            .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();


                break;

        }
    }


    void getVenue(String venue)
    {
        if(Utilities.isNetworkAvailable(activity))
        {
            ArchivesApi archivesApi = RetrofitClientInstance.getRetrofitInstanceServer().
                    create(ArchivesApi.class);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            archivesApi.getVenue(venue).
                    enqueue(new Callback<ArrayList<ArchivesModel>>() {

                        @Override
                        public void onResponse(Call<ArrayList<ArchivesModel>> call,
                                               Response<ArrayList<ArchivesModel>> response) {
                            ArrayList<ArchivesModel> archivesModels = response.body();
                            //String active= isPaidModels.get(0).active1;
                            rvenue=archivesModels.get(0).venue;
                            rdate=archivesModels.get(0).date;
                            Log.e("archivesModels: ", archivesModels.get(0).venue);
                            progressDialog.dismiss();
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
            //Utilities.setError(layout1,layout2,txt_error,getResources().getString(R.string.check_internet));

        }
    }



}