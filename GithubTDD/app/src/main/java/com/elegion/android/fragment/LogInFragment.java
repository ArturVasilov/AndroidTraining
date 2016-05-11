package com.elegion.android.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.elegion.android.R;
import com.elegion.android.base.BaseFragment;
import com.elegion.android.presenter.LogInPresenter;
import com.elegion.android.view.LogInView;

import droidkit.annotation.InjectView;
import droidkit.annotation.OnClick;

/**
 * @author Artur Vasilov
 */
public class LogInFragment extends BaseFragment implements LogInView {

    @InjectView(R.id.loginInputLayout)
    private TextInputLayout mLoginLayout;

    @InjectView(R.id.loginEdit)
    private EditText mLoginEdit;

    @InjectView(R.id.passwordEdit)
    private EditText mPasswordEdit;

    private LogInPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDialog = LoadingDialog.create(R.string.log_in_progress);
        mPresenter = new LogInPresenter(getActivity(), getLoaderManager(), this);
    }

    @Override
    public void showError() {
        mLoginLayout.setError(getString(R.string.error));
    }

    @Override
    public void hideError() {
        mLoginLayout.setError("");
    }

    @OnClick(R.id.logInButton)
    public void onLogIn() {
        String login = mLoginEdit.getText().toString();
        String password = mPasswordEdit.getText().toString();

        mPresenter.tryLogIn(login, password);
    }

    @LayoutRes
    @Override
    protected int getLayoutId() {
        return R.layout.log_in_fragment;
    }

}
