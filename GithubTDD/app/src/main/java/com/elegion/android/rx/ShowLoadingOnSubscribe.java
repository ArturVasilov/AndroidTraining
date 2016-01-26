package com.elegion.android.rx;

import android.support.annotation.NonNull;

import com.elegion.android.base.BaseView;

import rx.functions.Action0;

/**
 * @author Artur Vasilov
 */
public class ShowLoadingOnSubscribe implements Action0 {

    @NonNull
    private final BaseView mBaseView;

    public ShowLoadingOnSubscribe(@NonNull BaseView baseView) {
        mBaseView = baseView;
    }

    @Override
    public void call() {
        mBaseView.showLoading();
    }
}
