package com.webview.app;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //declaring variables
    WebView fullpage;
    RelativeLayout relativeLayout;
    Button refresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing variables
        fullpage=(WebView) findViewById(R.id.fullpage);
        relativeLayout=findViewById(R.id.connection);
        refresh=findViewById(R.id.retrybtn);


        //set actions

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectionCheck();
            }
        });

        fullpage.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                connectionCheck();
                super.onReceivedError(view, request, error);
            }
        });
        fullpage.loadUrl("https://amaderdeshbangla.blogspot.com");
        WebSettings webSettings=fullpage.getSettings();
        webSettings.setJavaScriptEnabled(true);

        connectionCheck();
    }

    @Override
    public void onBackPressed() {
        if (fullpage.canGoBack()){
            fullpage.goBack();

        }else {
            super.onBackPressed();
        }
    }
    public void connectionCheck(){
        ConnectivityManager connectivityManager=(ConnectivityManager) this.getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo mobileData=connectivityManager.getActiveNetworkInfo();

        //WIFI connection check showing error so I comment it, I'll solve it later..
//        NetworkInfo wifiConnection=connectivityManager.getActiveNetworkInfo(TYPE_WIFI);

        if (mobileData !=null){
            fullpage.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
            Toast.makeText(this, "You are connected to the Internet", Toast.LENGTH_LONG).show();
            fullpage.reload();
        }else{
            relativeLayout.setVisibility(View.VISIBLE);
            fullpage.setVisibility(View.GONE);
            Toast.makeText(this, "Please turn on your wifi or mobile data", Toast.LENGTH_LONG).show();
        }




    }
}