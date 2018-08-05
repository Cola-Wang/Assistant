package com.miki.assistant.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.miki.assistant.R;
import com.miki.assistant.base.BaseActivity;

/**
 * 包名:      com.miki.assistant.ui
 * 文件名:     WebViewActivity.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/5 16:45
 * 描述:      浏览器
 */

public class WebViewActivity extends BaseActivity {

    private WebView mWebView;
    private ProgressBar mProgress;

    private String title;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initView();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.mWebView);
        mProgress = (ProgressBar) findViewById(R.id.mProgress);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        url = intent.getStringExtra("url");

        if (!TextUtils.isEmpty(title)) {
            getSupportActionBar().setTitle(title);
        }

        if (!TextUtils.isEmpty(url)) {
            initWebView();
        }

    }

    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        //支持JS
        settings.setJavaScriptEnabled(true);
        //支持缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        //设置缓存
        settings.setAppCacheEnabled(true);

        mWebView.setWebChromeClient(new WebViewChromeClient());
        mWebView.loadUrl(url);

        //本地浏览
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }

    public class WebViewChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mProgress.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.destroy();
        }
    }
}
