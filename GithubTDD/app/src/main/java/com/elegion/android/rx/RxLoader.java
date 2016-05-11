package com.elegion.android.rx;

import android.app.LoaderManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.observers.Observers;

/**
 * @author Daniel Serdyukov
 */
public class RxLoader<T> {

    private final Context mContext;

    private final Reference<LoaderManager> mLmRef;

    @IdRes
    private final int mLoaderId;

    private final Observable<T> mObservable;

    private RxLoader(@NonNull Context context, @NonNull LoaderManager lm, @IdRes int loaderId,
                     @NonNull Observable<T> observable) {
        mContext = context;
        mLmRef = new WeakReference<>(lm);
        mLoaderId = loaderId;
        mObservable = observable;
    }

    @NonNull
    public static <T> RxLoader<T> create(@NonNull Context context, @NonNull LoaderManager lm, int loaderId,
                                         @NonNull Observable<T> observable) {
        return new RxLoader<>(context.getApplicationContext(), lm, loaderId, observable);
    }

    public void restart(@NonNull Action1<T> onNext) {
        restart(Observers.create(onNext));
    }

    public void restart(@NonNull Action1<T> onNext, @NonNull Action1<Throwable> onError) {
        restart(Observers.create(onNext, onError));
    }

    public void restart(@NonNull Observer<T> observer) {
        final LoaderManager lm = mLmRef.get();
        if (lm != null) {
            synchronized (LoaderManager.class) {
                lm.restartLoader(mLoaderId, Bundle.EMPTY, new RxLcImpl<>(mContext, mObservable, observer, null));
            }
        }
    }

}
