package com.elegion.android.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.afollestad.materialdialogs.MaterialDialog;
import com.elegion.android.R;

import droidkit.content.IntValue;
import droidkit.content.TypedBundle;

/**
 * @author Artur Vasilov
 */
public class LoadingDialog extends DialogFragment {

    private static final String TAG = LoadingDialog.class.getSimpleName();

    @NonNull
    public static LoadingDialog create(@StringRes int textId) {
        Bundle bundle = new Bundle();
        TypedBundle.from(bundle, Args.class).text().set(textId);
        LoadingDialog dialog = new LoadingDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    public void show(@NonNull FragmentManager manager) {
        show(manager, TAG);
    }

    public void cancel() {
        dismiss();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String text = getString(TypedBundle.from(getArguments(), Args.class).text().get());
        return new MaterialDialog.Builder(getActivity())
                .progress(true, 0)
                .title(R.string.please_wait)
                .content(text)
                .build();
    }

    public interface Args {
        IntValue text();
    }
}
