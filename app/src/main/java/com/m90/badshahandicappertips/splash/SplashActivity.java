package com.m90.badshahandicappertips.splash;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.m90.badshahandicappertips.MainActivity;
import com.m90.badshahandicappertips.R;
import com.m90.badshahandicappertips.constants.SessionHelper;
import com.m90.badshahandicappertips.constants.Utility;
import com.m90.badshahandicappertips.home.HomeActivity;
import com.m90.badshahandicappertips.otp.OTPActivity;
import com.m90.badshahandicappertips.utils.PrefManager;
import com.m90.badshahandicappertips.utils.Utilities;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    Handler handler;
    PrefManager prefManager;
    ProgressDialog progressDialog;
    SessionHelper sessionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_splash);

        prefManager = new PrefManager(this);
        progressDialog = new ProgressDialog(this);
        sessionHelper = new SessionHelper(this);

        animation();
    }

    public void animation() {
       // progressDialog.show();
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sessionHelper.isLoggedIn()) {

                    Utility.launchActivity(SplashActivity.this, HomeActivity.class, true);
                }
                else {
                    Utility.launchActivity(SplashActivity.this, OTPActivity.class, true);
                }
          }
        }, 3000);
    }

}