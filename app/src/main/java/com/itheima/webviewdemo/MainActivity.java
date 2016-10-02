package com.itheima.webviewdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView mWebView;

    private ProgressBar mProgress;

    private Button mPre;

    private Button mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.web_view);
        mProgress = (ProgressBar) findViewById(R.id.progress);
        mPre = (Button) findViewById(R.id.pre);
        mNext = (Button) findViewById(R.id.next);

        mPre.setOnClickListener(this);
        mNext.setOnClickListener(this);

        mWebView.loadUrl("http://www.itcast.cn");
//        mWebView.loadUrl("http://www.zhinengshe.com/");
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.setWebChromeClient(mWebChromeClient);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
    }
    
    private WebViewClient mWebViewClient = new WebViewClient() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            Toast.makeText(MainActivity.this, "onPageStarted", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
//            Toast.makeText(MainActivity.this, "onPageFinished", Toast.LENGTH_SHORT).show();
        }
    };

    private WebChromeClient mWebChromeClient = new WebChromeClient() {

        @Override
        public void onReceivedTitle(WebView view, String title) {
//            Toast.makeText(MainActivity.this, "onReceivedTitle " + title, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgress.setVisibility(newProgress == 100 ? View.GONE : View.VISIBLE);
            mProgress.setProgress(newProgress);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pre:
                onPre();
                break;
            case R.id.next:
                onNext();
                break;
        }
    }

    private void onNext() {
        mWebView.goForward();
    }

    private void onPre() {
        mWebView.goBack();
    }


}
