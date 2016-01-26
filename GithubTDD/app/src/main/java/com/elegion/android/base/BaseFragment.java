package com.elegion.android.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.android.R;
import com.elegion.android.fragment.LoadingDialog;

/**
 * @author Artur Vasilov
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    protected LoadingDialog mDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialog = LoadingDialog.create(R.string.please_wait);
    }

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @CallSuper
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showLoading() {
        mDialog.show(getFragmentManager());
    }

    @Override
    public void hideLoading() {
        mDialog.cancel();
    }

    @LayoutRes
    protected abstract int getLayoutId();

}
