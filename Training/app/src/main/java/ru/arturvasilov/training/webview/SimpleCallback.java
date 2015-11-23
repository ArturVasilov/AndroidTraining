package ru.arturvasilov.training.webview;

import android.webkit.JavascriptInterface;

/**
 * @author Artur Vasilov
 */
public class SimpleCallback extends BaseJavascriptCallback {

    @JavascriptInterface
    public void call(String json) {
        //TODO : do something
    }

    @Override
    public String name() {
        return "SomeCallbackName";
    }
}
