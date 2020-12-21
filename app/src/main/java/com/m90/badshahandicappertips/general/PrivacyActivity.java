package com.m90.badshahandicappertips.general;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.m90.badshahandicappertips.R;


public class PrivacyActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView back_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_privacy);

        bindViews();
        btnListeners();
    }

    private void bindViews() {
        back_about = findViewById(R.id.img_back);

    }

    private void btnListeners() {
        back_about.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;



        }
    }
}
