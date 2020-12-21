package com.m90.badshahandicappertips.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;

import com.m90.badshahandicappertips.general.PrivacyActivity;
import com.m90.badshahandicappertips.R;
import com.m90.badshahandicappertips.databinding.ActivityProfileBinding;
import com.m90.badshahandicappertips.home.HomeActivity;
import com.m90.badshahandicappertips.utils.Utilities;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        String text = "<font color=#000000>I agree, </font> <font color=#5B18CF> Terms and conditions</font><font color=#000000> and</font><font color=#5B18CF> Privacy Policy</font>";
        binding.txtprivacy.setText(Html.fromHtml(text));
        binding.txtprivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Utilities.launchActivity(ProfileActivity.this, PrivacyActivity.class,false);

            }
        });


        binding.submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Utilities.launchActivity(ProfileActivity.this, HomeActivity.class,false);

            }
        });
    }
}