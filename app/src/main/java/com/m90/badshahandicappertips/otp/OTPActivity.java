package com.m90.badshahandicappertips.otp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.androidnetworking.error.ANError;
import com.m90.badshahandicappertips.R;
import com.m90.badshahandicappertips.constants.SessionHelper;
import com.m90.badshahandicappertips.constants.Utility;
import com.m90.badshahandicappertips.databinding.ActivityOTPBinding;
import com.m90.badshahandicappertips.home.HomeActivity;
import com.m90.badshahandicappertips.interfaces.ApiStatusCallBack;
import com.m90.badshahandicappertips.myconfig.MyConfig;
import com.m90.badshahandicappertips.myconfig.OTPServices;
import com.m90.badshahandicappertips.otp.api.OTPApi;
import com.m90.badshahandicappertips.otp.model.OTPResponce;
import com.m90.badshahandicappertips.retrofit.RetrofitClientInstance;
import com.m90.badshahandicappertips.utils.PrefManager;
import com.m90.badshahandicappertips.utils.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.m90.badshahandicappertips.utils.Utilities.SendOTP;

public class OTPActivity extends AppCompatActivity {

    ActivityOTPBinding binding;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis;

    //String OTP="1234";
    String OTP="";
    Activity activity;

    ProgressDialog progressDialog;
    PrefManager prefManager;
    SessionHelper sessionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_o_t_p);

        activity=OTPActivity.this;
        progressDialog=new ProgressDialog(OTPActivity.this);
        prefManager=new PrefManager(OTPActivity.this);
        sessionHelper=new SessionHelper(OTPActivity.this);
        binding.submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isValidPhoneNumber(binding.etMobile.getText().toString()))
                {
                  //  Utility.launchActivity(OTPActivity.this, HomeActivity.class, true);

                    OTP = GenerateRandomNumber(6);
                    Log.e( "onClick: ",  OTP);

                    String message = "Thank you for visiting BADSHA HANDICAPPER TIPS app! \n Your OTP Is :" + OTP;

                    binding.layout1.setVisibility(View.GONE);
                    binding.layout2.setVisibility(View.VISIBLE);
                   timerForOtp(binding.etMobile.getText().toString(), message);
                 /* binding.layout1.setVisibility(View.GONE);
                  binding.layout2.setVisibility(View.VISIBLE);*/


               }
                else {
                    Toast.makeText(OTPActivity.this, "Enter proper mobile number", Toast.LENGTH_SHORT).show();
                }

            }
        });
        binding.submit2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if(binding.etOtp.getText().toString().equals(""))
                            {
                                Toast.makeText(OTPActivity.this, "Enter otp", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                if (binding.etOtp.getText().toString().equals(OTP))
                                {
                                    checkLogin(binding.etMobile.getText().toString(),"0");

                                } else {
                                    Toast.makeText(OTPActivity.this, "Enter proper otp", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    });


    }


    private void updateCountDownText(long millisUntilFinished) {
        int minutes = (int) (millisUntilFinished / 1000) / 60;
        int seconds = (int) (millisUntilFinished / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%d:%d", minutes, seconds);
        //text_otp_expire.setText(timeLeftFormatted);
        Spannable WordtoSpan = new SpannableString("OTP expire after "+timeLeftFormatted+"  ");
        WordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), 17, 21,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.textOtpExpire.setText(WordtoSpan);

        //text_otp_expire.setText("OTP expire after  "+timeLeftFormatted+"  Seconds");

    }

    private void timerForOtp(String mobileNumber,String message) {


        binding.tvSmsRecv.setVisibility(View.GONE);
        SpannableString span = new SpannableString("Didn't receive SMS ? Resend");
        span.setSpan(new ForegroundColorSpan(Color.GRAY), 21, 27, 0);
        binding.tvSmsRecv.setTextColor(Color.parseColor("#48494b"));

        mCountDownTimer = new CountDownTimer(120000, 1000) {
            // mCountDownTimer = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {

                mTimeLeftInMillis=millisUntilFinished;
                updateCountDownText(millisUntilFinished);
                //120 sec=120000ms
                if ((millisUntilFinished / 1000) == 117) {

                    /*binding.layout1.setVisibility(View.GONE);
                    binding.layout2.setVisibility(View.VISIBLE);*/
                    sendOTP(mobileNumber,message);

                }
                else {

                  //  sendCodeButton.setVisibility(View.VISIBLE);
                }

            }

            public void onFinish() {
                //sendOTP(mobileNumber, message);
                //Toast.makeText(AuthenticationActivity.this,"Time Out!! Resend OTP",Toast.LENGTH_LONG).show();
                SpannableString span = new SpannableString("Didn't receive SMS ? Resend");
                span.setSpan(new ForegroundColorSpan(Color.BLUE), 21, 27, 0);
               binding.tvSmsRecv.setText(span, TextView.BufferType.SPANNABLE);
                binding.tvSmsRecv.setVisibility(View.VISIBLE);
                //tv_sms_recv.setTextColor(Color.parseColor("#002266"));
                binding.tvSmsRecv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        OTP = GenerateRandomNumber(6);
                        Log.e("ChkOTP",""+OTP);
                        String message2 = "Thank you for visiting BADSHA HANDICAPPER TIPS app! \n Your OTP Is :" + OTP;
                        binding.layout1.setVisibility(View.GONE);
                        binding.layout2.setVisibility(View.VISIBLE);
                        timerForOtp(mobileNumber,message2);

                    }
                });

            }

        }.start();
    }


    public static boolean isValidPhoneNumber(String mobile) {
        String regEx = "^[0-9]{10}$";
        return mobile.matches(regEx);
    }



    String GenerateRandomNumber(int charLength) {
        return String.valueOf(charLength < 1 ? 0 : new Random()
                .nextInt((9 * (int) Math.pow(10, charLength - 1)) - 1)
                + (int) Math.pow(10, charLength - 1));
    }



    void checkLogin(String mobile, String active)
    {
        if(Utilities.isNetworkAvailable(activity))
        {
            OTPApi loginApi = RetrofitClientInstance.getRetrofitInstanceServer().
                    create(OTPApi.class);

            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            // pbLoading.setProgressStyle(R.id.abbreviationsBar);
            progressDialog.show();
            loginApi.checkLogin(mobile,active).
                    enqueue(new Callback<OTPResponce>() {

                        @Override
                        public void onResponse(Call<OTPResponce> call, Response<OTPResponce> response) {

                              OTPResponce otpResponce = response.body();

                             /* if(response.body()==null)
                              {

                                  prefManager.setMobile(binding.etMobile.getText().toString());
                                  Utility.launchActivity(OTPActivity.this, HomeActivity.class, true);
                                  //  Toast.makeText(activity, "mmmm", Toast.LENGTH_SHORT).show();
                              }
                              else {*/
                                  //Log.e( "onResponsedbid: ",otpResponce.toString());
                                  sessionHelper.setLogin(true);
                                  prefManager.setMobile(binding.etMobile.getText().toString());
                                  Utility.launchActivity(OTPActivity.this, HomeActivity.class, true);

                             // }

                           // Log.e("chkdb: ", deliveryBoyResponse.getResult().toString());
                                progressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<OTPResponce> call, Throwable t) {

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
/*
    private void login1(final String mobile_number, final String password){

        if (Utility.isNetworkAvailable(this)) {

            progressDialog.setMessage("Please wait");
            progressDialog.show();
            progressDialog.setCancelable(false);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                    MyConfig.getLoginUrl(mobile_number, password),
                    null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                    try {
                       // sessionHelper.setLogin(true);
                        String userid = response.getJSONObject("data").getString("id");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    progressDialog.dismiss();
                    //Starting profile activity
                    Intent intent = new Intent(activity, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();

                    Toast.makeText(activity, "Invalid username/password", Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request
                    params.put("mobile_number", mobile_number);
                    params.put("password", password);

                    //returning parameter
                    return params;
                }
            };
            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
        }
        else {

            Toast.makeText(this, getResources().getString(R.string.checkinternaet), Toast.LENGTH_SHORT).show();

        }
    }*/

    void sendOTP(String mobileNumber, String message) {
        OTPServices.getInstance(OTPActivity.this).SendOTP(mobileNumber, message, new ApiStatusCallBack() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(OTPActivity.this, "sent otp", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(ANError anError) {
                Log.e( "onError: ", anError.toString());
             //   Toast.makeText(OTPActivity.this, "Failed "+anError.toString(), Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onUnknownError(Exception e) {
               // Toast.makeText(OTPActivity.this, "Error ", Toast.LENGTH_SHORT).show();
            }
        });
    }

}