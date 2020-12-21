package com.m90.badshahandicappertips.payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import com.m90.badshahandicappertips.R;
import com.m90.badshahandicappertips.databinding.ActivityFinalPaymentBinding;

public class FinalPaymentActivity extends AppCompatActivity {

    ActivityFinalPaymentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_final_payment);

        String text=getIntent().getStringExtra("text");
        binding.txtTitle2.setText(text);

   }
}
