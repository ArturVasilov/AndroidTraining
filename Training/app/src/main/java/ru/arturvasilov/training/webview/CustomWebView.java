package ru.arturvasilov.training.webview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author Artur Vasilov
 */
public class CustomWebView extends WebView {

    private WebViewClient mWebViewClient;
    private WebChromeClient mWebChromeClient;

    public CustomWebView(Context context) {
        super(context);
        init();
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setWebViewClient(mWebViewClient);
        setWebChromeClient(mWebChromeClient);
    }

    @Override
    protected void onDetachedFromWindow() {
        setWebViewClient(null);
        setWebChromeClient(null);
        super.onDetachedFromWindow();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        mWebViewClient = new CustomWebViewClient();
        mWebChromeClient = new CustomWebChromeClient();

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        final WebSettings webSettings = getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSaveFormData(false);
    }
}
