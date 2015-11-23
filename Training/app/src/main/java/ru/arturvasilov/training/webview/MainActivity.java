package ru.arturvasilov.training.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import ru.arturvasilov.training.R;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private BaseJavascriptCallback mSimpleCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_ac);

        mWebView = (WebView) findViewById(R.id.webView);
        mSimpleCallback = new SimpleCallback();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSimpleCallback.attach(mWebView);
        mWebView.loadUrl("https://translate.google.ru/?hl=ru");
    }

    @Override
    protected void onPause() {
        mSimpleCallback.detach(mWebView);
        super.onPause();
    }
}
