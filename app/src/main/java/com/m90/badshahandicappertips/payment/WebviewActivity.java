package com.m90.badshahandicappertips.payment;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.m90.badshahandicappertips.R;

public class WebviewActivity extends AppCompatActivity {


    ImageView back_about, img_home;
    TextView txt_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_webview);
        WebView browser = (WebView) findViewById(R.id.webview);
        back_about =  findViewById(R.id.back_about);
        img_home =  findViewById(R.id.img_home);
        txt_title =  findViewById(R.id.txt_title);

        loadWebViewLoad(browser);

        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            onBackPressed();            }
        });
        back_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
      //browser.loadUrl("http://www.tutorialspoint.com");

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
      //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void loadWebViewLoad(WebView webview) {
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setSupportMultipleWindows(true);
        webview.setWebViewClient(new WebViewClient());
        webview.setWebChromeClient(new WebChromeClient());
       // webview.loadUrl("http://www.tutorialspoint.com");
        webview.loadUrl("https://rzp.io/l/WTprC2d0");
    }
}
