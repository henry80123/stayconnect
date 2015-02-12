package com.studentproject.stayconnect;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
/**
 * Created by Paul on 11/2/15.
 */
public class DisplayDoctorListActivity extends Activity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaydoctorlist_layout);
        init();
    }

    private void init() {
        mWebView = (WebView) findViewById(R.id.doctorList_webView);
        loadPDF();
    }

    private void loadPDF() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.loadUrl("http://docs.google.com/gview?embedded=true&url=http://www.stagecoachbus.com/PdfUploads/Timetable_28768_5.pdf");



    }

}




