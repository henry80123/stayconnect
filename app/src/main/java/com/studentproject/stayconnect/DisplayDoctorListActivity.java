package com.studentproject.stayconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by HenryChiang on 15-02-23.
 */
public class DisplayDoctorListActivity extends Activity {

    private WebView mWebView;
    private String whichOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_doctorlist_layout);
        Intent i = getIntent();
        initLoadPDF(i.getStringExtra("url"));
    }

    private void initLoadPDF(String url) {
        mWebView = (WebView) findViewById(R.id.doctorList_webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.loadUrl(url);
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void finish() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
    }
}
