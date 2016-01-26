package com.elegion.android.view;

import android.support.annotation.NonNull;

import com.elegion.android.base.BaseView;
import com.elegion.android.content.Repository;

import java.util.List;

/**
 * @author Artur Vasilov
 */
public interface RepositoriesView extends BaseView {

    void showRepositories(@NonNull List<Repository> repositories);

    void showEmpty();
}
