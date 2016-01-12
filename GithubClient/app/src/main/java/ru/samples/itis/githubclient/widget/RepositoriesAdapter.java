package ru.samples.itis.githubclient.widget;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.List;

import ru.samples.itis.githubclient.content.Repository;

/**
 * @author Artur Vasilov
 */
public class RepositoriesAdapter extends BaseRecyclerAdapter<RepositoriesAdapter.RepositoryHolder, Repository> {

    public RepositoriesAdapter(@NonNull List<Repository> values) {
        super(values);
    }

    @LayoutRes
    @Override
    protected int getLayoutId() {
        return 0;
    }

    @NonNull
    @Override
    protected RepositoryHolder createHolder(@NonNull View view) {
        return new RepositoryHolder(view);
    }

    @Override
    protected void populateHolder(@NonNull RepositoryHolder holder, @NonNull Repository value, int position) {

    }

    protected class RepositoryHolder extends BaseRecyclerAdapter.Holder {

        public RepositoryHolder(View itemView) {
            super(itemView);
        }
    }

}
