package ru.samples.itis.githubclient.api;

import rx.Subscriber;

/**
 * @author Artur Vasilov
 */
public class BaseSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        // do nothing
    }

    @Override
    public void onError(Throwable e) {
        // do nothing
    }

    @Override
    public void onNext(T t) {
        // do nothing
    }
}
