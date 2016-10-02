package com.itheima.webviewdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.web_view);

        mWebView.loadUrl("https://www.baidu.com");

        mWebView.setWebViewClient(mWebViewClient);
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
}
