package com.wd.tech.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wd.tech.R;

public class NewsAdDetailsActivity extends AppCompatActivity {
    WebView ad_webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_ad_details);
        Intent intent = getIntent();
        String adurl = intent.getStringExtra("adurl");
        ad_webView = findViewById(R.id.ad_webView);
        WebSettings webSettings = ad_webView.getSettings();
        ad_webView.loadUrl(adurl);
        ad_webView.setWebViewClient(new WebViewClient());
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
    }
}
