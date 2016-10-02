package com.itheima.webviewdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView mWebView;

    private ProgressBar mProgress;

    private Button mPre;

    private Button mNext;

    private static final int QUIT_TIME_INTERNAL = 2000;
    private float mQuitStartTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        setClient();
        initWebSettings();
        //mWebView.loadUrl("http://www.itcast.cn");
//        mWebView.loadUrl("http://www.zhinengshe.com/");
        mWebView.loadUrl("file:///android_asset/demo.html");

        mWebView.addJavascriptInterface(new JSCallAndroid() {

            @JavascriptInterface//注意:此处一定要加该注解,否则在4.1+系统上运行失败
            @Override
            public void onCallback() {
                Toast.makeText(MainActivity.this, "Js called Android!", Toast.LENGTH_SHORT).show();
            }
        }, "demo");
    }

    private void initWebSettings() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
    }

    private void setClient() {
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.setWebChromeClient(mWebChromeClient);
    }
    
    private void initEvent() {
        mPre.setOnClickListener(this);
        mNext.setOnClickListener(this);
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.web_view);
        mProgress = (ProgressBar) findViewById(R.id.progress);
        mPre = (Button) findViewById(R.id.pre);
        mNext = (Button) findViewById(R.id.next);
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
        if (mWebView.canGoForward()) {
            mWebView.goForward();
        } else {
            Toast.makeText(MainActivity.this, "已经是最后一页", Toast.LENGTH_SHORT).show();
        }
    }

    private void onPre() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            Toast.makeText(MainActivity.this, "已经是第一页", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            if (System.currentTimeMillis() - mQuitStartTime < QUIT_TIME_INTERNAL) {
                finish();
            } else {
                Toast.makeText(MainActivity.this, "再点击一次退出应用", Toast.LENGTH_SHORT).show();
                mQuitStartTime = System.currentTimeMillis();
            }
        }
    }

    public interface JSCallAndroid {
        @JavascriptInterface
        public void onCallback();
    }
}
