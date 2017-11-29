package com.parkitalia.android.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.parkitalia.android.R;
import com.parkitalia.android.activities.MyAccount;
import com.parkitalia.android.activities.ServicesMapActivity;

public class ServicesWebView extends Activity {
    String url;
    WebView webView;
    LinearLayout myAccount,findMyCar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_web_view);
        webView =(WebView)findViewById(R.id.webview);
        myAccount =(LinearLayout)findViewById(R.id.linearlayout_account);
        findMyCar =(LinearLayout)findViewById(R.id.button_find_my_car);
        Intent intent =getIntent();
        url=intent.getStringExtra("Url");
        webView.setWebViewClient(new MyBrowser());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);

        myAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ServicesWebView.this, MyAccount.class);
                startActivity(intent1);
            }
        });
        findMyCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ServicesWebView.this, ServicesMapActivity.class);
                intent1.putExtra("lat",-1);
                intent1.putExtra("lng",2);
                startActivity(intent1);
            }
        });
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
