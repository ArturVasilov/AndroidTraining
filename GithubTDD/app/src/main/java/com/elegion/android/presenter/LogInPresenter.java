package com.elegion.android.presenter;

import android.app.LoaderManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.elegion.android.R;
import com.elegion.android.activity.RepositoriesActivity;
import com.elegion.android.api.ApiFactory;
import com.elegion.android.app.Settings;
import com.elegion.android.base.BasePresenter;
import com.elegion.android.content.Authorization;
import com.elegion.android.rx.RxLoader;
import com.elegion.android.rx.ShowLoadingFinallyDo;
import com.elegion.android.rx.ShowLoadingOnSubscribe;
import com.elegion.android.view.LogInView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.elegion.android.utils.AuthorizationUtils.createAuthorizationParam;
import static com.elegion.android.utils.AuthorizationUtils.createAuthorizationString;

/**
 * @author Artur Vasilov
 */
public class LogInPresenter extends BasePresenter<LogInView> {

    public LogInPresenter(@NonNull Context context, @NonNull LoaderManager manager, @NonNull LogInView view) {
        super(context, manager, view);
    }

    public void tryLogIn(@NonNull String login, @NonNull String password) {
        RxLoader.create(getContext(), getLoaderManager(), R.id.log_in_loader,
                populateObservable(getService().authorize(createAuthorizationString(login, password), createAuthorizationParam())))
                .restart(new Action1<Authorization>() {
                             @Override
                             public void call(Authorization authorization) {
                                 handleAuthorized(getSettings(), authorization);
                             }
                         }, new Action1<Throwable>() {
                             @Override
                             public void call(Throwable throwable) {
                                 handleError(throwable);
                             }
                         }
                );
    }

    @VisibleForTesting
    void handleAuthorized(@NonNull Settings settings, @NonNull Authorization authorization) {
        settings.token().set(authorization.getToken());
        ApiFactory.setup(getSettings());
        getView().hideError();
        logIn();
    }

    @VisibleForTesting
    void handleError(@NonNull Throwable throwable) {
        getView().showError();
    }

    @VisibleForTesting
    void logIn() {
        getContext().startActivity(RepositoriesActivity.makeIntent(getContext()));
    }

    @VisibleForTesting
    @NonNull
    Observable<Authorization> populateObservable(@NonNull Observable<Authorization> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new ShowLoadingOnSubscribe(getView()))
                .finallyDo(new ShowLoadingFinallyDo(getView()));
    }
}
