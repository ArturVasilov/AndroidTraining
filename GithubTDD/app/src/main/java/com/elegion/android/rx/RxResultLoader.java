package com.elegion.android.rx;

import android.content.Context;
import android.content.Loader;
import android.database.ContentObserver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * @author Daniel Serdyukov
 */
class RxResultLoader<T> extends Loader<RxResult<T>> {

    private final ContentObserver mContentObserver = new ForceLoadContentObserver();

    private final Observable<T> mObservable;

    private final Uri mContentUri;

    private final AtomicBoolean mUriAlreadyRegistered = new AtomicBoolean(false);

    private boolean mOnNextCalled;

    private T mResult;

    public RxResultLoader(@NonNull Context context, @NonNull Observable<T> observable, @Nullable Uri contentUri) {
        super(context);
        mObservable = observable;
        mContentUri = contentUri;
    }

    @Override
    public void deliverResult(RxResult<T> data) {
        if (isReset()) {
            return;
        }
        if (data != null) {
            mResult = data.getResult();
        }
        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        if (mContentUri != null && mUriAlreadyRegistered.compareAndSet(false, true)) {
            getContext().getContentResolver().registerContentObserver(mContentUri, true, mContentObserver);
        }
        if (takeContentChanged() || mResult == null) {
            forceLoad();
        }
    }

    @Override
    protected void onForceLoad() {
        mObservable.subscribe(new Action1<T>() {
            @Override
            public void call(T t) {
                mOnNextCalled = true;
                deliverResult(RxResult.onNext(t));
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                deliverResult(RxResult.<T>onError(throwable));
            }
        }, new Action0() {
            @Override
            public void call() {
                if (!mOnNextCalled) {
                    deliverResult(null);
                }
            }
        });
    }

    @Override
    protected void onReset() {
        if (mContentUri != null && mUriAlreadyRegistered.compareAndSet(true, false)) {
            getContext().getContentResolver().unregisterContentObserver(mContentObserver);
        }
        super.onReset();
    }

}
