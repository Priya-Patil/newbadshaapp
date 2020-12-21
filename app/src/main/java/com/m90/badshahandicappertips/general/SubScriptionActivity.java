package com.m90.badshahandicappertips.general;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.m90.badshahandicappertips.R;
import com.m90.badshahandicappertips.constants.Utility;
import com.m90.badshahandicappertips.databinding.ActivitySubBinding;
import com.m90.badshahandicappertips.general.api.CheckIsPaidApi;
import com.m90.badshahandicappertips.general.api.IsRaceAvailableApi;
import com.m90.badshahandicappertips.general.model.IsPaidModel;
import com.m90.badshahandicappertips.general.model.IsRaceAvailableModel;
import com.m90.badshahandicappertips.payment.FinalPaymentActivity;
import com.m90.badshahandicappertips.payment.WebviewActivity;
import com.m90.badshahandicappertips.retrofit.RetrofitClientInstance;
import com.m90.badshahandicappertips.utils.PrefManager;
import com.m90.badshahandicappertips.utils.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubScriptionActivity extends AppCompatActivity {

    ActivitySubBinding binding;
    Activity activity;
    ProgressDialog progressDialog;

    PrefManager prefManager;
    String expirydate;

    private void checkDateExtendedOneDay(int total_days) {
        // SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date today = Calendar.getInstance().getTime();
        String startDate = sdf.format(today);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(startDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Log.e(TAG, "onPaymentSuccess: Duration "+programModel.getDuration() );
        c.add(Calendar.DATE, total_days);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        String endDate = sdf.format(c.getTime());
        expirydate = sdf.format(c.getTime());
        Log.e( "onPaymentSuccess: exp " , expirydate);

        binding.txt11.setText(binding.txt11.getText().toString()+"   "+ expirydate);
    }
    private void checkDateExtended29Day(int total_days) {
        // SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date today = Calendar.getInstance().getTime();
        String startDate = sdf.format(today);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(startDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Log.e(TAG, "onPaymentSuccess: Duration "+programModel.getDuration() );
        c.add(Calendar.DATE, total_days);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        String endDate = sdf.format(c.getTime());
        expirydate = sdf.format(c.getTime());
        Log.e( "onPaymentSuccess: exp " , expirydate);

        binding.txt22.setText(binding.txt22.getText().toString()+"   "+ expirydate);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sub);

        activity= SubScriptionActivity.this;
        progressDialog=new ProgressDialog(activity);
        prefManager=new PrefManager(activity);
        String cityid=getIntent().getStringExtra("cityid");
        checkIsRaceAvailable(cityid);
        binding.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bb=new Bundle();
                bb.putString("text", "1 DAYS AT\nJUST 50/-");
                Utility.launchActivity(SubScriptionActivity.this, FinalPaymentActivity.class,
                        false, bb);

                // Utility.launchActivity(SubScriptionActivity.this, WebviewActivity.class, false);
                // Utility.launchActivity(SubScriptionActivity.this, NewSubsribeActivity.class, false);
            }
        });
         binding.btnSub2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Bundle bb=new Bundle();
                        bb.putString("text", "29 DAYS AT\nJUST 390/-");
                        Utility.launchActivity(SubScriptionActivity.this, FinalPaymentActivity.class,
                                false, bb);
                        // Utility.launchActivity(SubScriptionActivity.this, WebviewActivity.class, false);
                        // Utility.launchActivity(SubScriptionActivity.this, NewSubsribeActivity.class, false);
                    }
                });

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        checkDateExtendedOneDay(1);
        checkDateExtended29Day(29);

    }

    private void showDialogNoRecord() {
        // this.correct = correct;
        final Dialog resultbox = new Dialog(this);
        resultbox.setContentView(R.layout.custom_dialog_home);
        resultbox.setCanceledOnTouchOutside(false);
        Button btn_finish = (Button) resultbox.findViewById(R.id.btn_finish);
        Button btn_cancel = (Button) resultbox.findViewById(R.id.btn_resume);
        TextView text_assign = resultbox.findViewById(R.id.text_title);
        text_assign.setText("Race not available!..Do you want to go back?");

        btn_finish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                resultbox.cancel();
               onBackPressed();
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


   /* void checkRace(String id)
    {
        if(Utilities.isNetworkAvailable(activity))
        {
            CheckIsPaidApi isRaceAvailableApi = RetrofitClientInstance.getRetrofitInstanceServer().
                    create(CheckIsPaidApi.class);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            isRaceAvailableApi.checkRace(id).
                    enqueue(new Callback<ArrayList<IsPaidModel>>() {

                        @Override
                        public void onResponse(Call<ArrayList<IsPaidModel>> call,
                                               Response<ArrayList<IsPaidModel>> response) {

                            ArrayList<IsPaidModel> isRaceAvailableModels = response.body();

                            Log.e("chkres: ", isRaceAvailableModels.get(0).active1);
                            String active=isRaceAvailableModels.get(0).active1;
                            if(active.equals("0"))
                            {
                                showDialogNoRecord();
                            }
                            else {

                                binding.parentlayout.setVisibility(View.VISIBLE);
                            }

                            progressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<ArrayList<IsPaidModel>> call, Throwable t) {

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
*/

    void checkIsPaid(String id)
    {
        if(Utilities.isNetworkAvailable(activity))
        {
            CheckIsPaidApi checkIsPaidApi = RetrofitClientInstance.getRetrofitInstanceServer().
                    create(CheckIsPaidApi.class);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            checkIsPaidApi.checkRace(id).
                    enqueue(new Callback<ArrayList<IsPaidModel>>() {

                        @Override
                        public void onResponse(Call<ArrayList<IsPaidModel>> call,
                                               Response<ArrayList<IsPaidModel>> response) {

                            ArrayList<IsPaidModel> isPaidModels = response.body();

                          //  Log.e("chkres: ", isPaidModels.get(0).getCreated_at());
                            String active= isPaidModels.get(0).active1;
                            if(active.equals("1"))
                            {
                                Utility.launchActivity(SubScriptionActivity.this, NewSubsribeActivity.class,
                                        true);

                                // showDialogNoRecord();
                            }
                            else {

                                //Utility.launchActivity(SubScriptionActivity.this, WebviewActivity.class, false);
                                binding.parentlayout.setVisibility(View.VISIBLE);
                            }
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<ArrayList<IsPaidModel>> call, Throwable t) {

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


    void checkIsRaceAvailable(String id)
    {
        if(Utilities.isNetworkAvailable(activity))
        {
            IsRaceAvailableApi isRaceAvailableApi = RetrofitClientInstance.getRetrofitInstanceServer().
                    create(IsRaceAvailableApi.class);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            isRaceAvailableApi.checkRace(id).
                    enqueue(new Callback<ArrayList<IsRaceAvailableModel>>() {

                        @Override
                        public void onResponse(Call<ArrayList<IsRaceAvailableModel>> call,
                                               Response<ArrayList<IsRaceAvailableModel>> response) {

                            ArrayList<IsRaceAvailableModel> isPaidModels = response.body();

                          //  Log.e("chkres: ", isPaidModels.get(0).getCreated_at());
                            String active= isPaidModels.get(0).active1;
                            if(active.equals("1"))
                            {

                                checkIsPaid(prefManager.getMobile());
                                // showDialogNoRecord();
                            }
                            else {

                               showDialogNoRecord();
                            }
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<ArrayList<IsRaceAvailableModel>> call, Throwable t) {

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



    public void loadPage (View view) {
        WebView browser = new WebView( this ) ;
        browser.getSettings().setJavaScriptEnabled( true ) ;
        browser.loadUrl( "file:///android_asset/pay.html" ) ;
        setContentView(browser) ;
        WebSettings ws = browser.getSettings() ;
        ws.setJavaScriptEnabled( true ) ;
        browser.addJavascriptInterface( new Object() {
            @JavascriptInterface // For API 17+
            public void performClick (String strl) {
                Toast. makeText (SubScriptionActivity. this, strl , Toast. LENGTH_SHORT ).show() ;
            }
        } , "ok" ) ;
    }

}