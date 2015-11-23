package ru.arturvasilov.training.webview;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.webkit.WebView;

/**
 * @author Artur Vasilov
 */
public abstract class BaseJavascriptCallback {

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    public void attach(@NonNull WebView webView) {
        webView.addJavascriptInterface(this, name());
    }

    public void detach(@NonNull WebView webView) {
        webView.removeJavascriptInterface(name());
    }

    protected String name() {
        return getClass().getSimpleName();
    }

}
