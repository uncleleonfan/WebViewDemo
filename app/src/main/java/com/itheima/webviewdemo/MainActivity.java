package com.itheima.webviewdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.web_view);
        mProgress = (ProgressBar) findViewById(R.id.progress);

        mWebView.loadUrl("https://www.baidu.com");
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.setWebChromeClient(mWebChromeClient);
    }
    
    private WebViewClient mWebViewClient = new WebViewClient() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Toast.makeText(MainActivity.this, "onPageStarted", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Toast.makeText(MainActivity.this, "onPageFinished", Toast.LENGTH_SHORT).show();
        }
    };

    private WebChromeClient mWebChromeClient = new WebChromeClient() {

        @Override
        public void onReceivedTitle(WebView view, String title) {
            Toast.makeText(MainActivity.this, "onReceivedTitle " + title, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgress.setVisibility(newProgress == 100 ? View.GONE : View.VISIBLE);
            mProgress.setProgress(newProgress);
        }
    };
}
