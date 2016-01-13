package ru.samples.itis.githubclient.widget;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import ru.arturvasilov.sqlite.SQLite;
import ru.arturvasilov.sqlite.observers.TableObserver;
import ru.samples.itis.githubclient.R;
import ru.samples.itis.githubclient.content.Repository;
import ru.samples.itis.githubclient.content.tables.RepositoryTable;

/**
 * @author Artur Vasilov
 */
public class RepositoriesAdapter extends BaseRecyclerAdapter<RepositoriesAdapter.RepositoryHolder, Repository> implements TableObserver {

    public RepositoriesAdapter() {
        super(SQLite.get().query(RepositoryTable.TABLE).all().execute());
    }

    @LayoutRes
    @Override
    protected int getLayoutId() {
        return R.layout.repositoty_item;
    }

    @NonNull
    @Override
    protected RepositoryHolder createHolder(@NonNull View view) {
        return new RepositoryHolder(view);
    }

    @Override
    protected void populateHolder(@NonNull RepositoryHolder holder, @NonNull Repository value, int position) {
        holder.mRepositoryName.setText(value.getName());
    }

    @Override
    public void onTableChanged() {
        List<Repository> repositories = SQLite.get().query(RepositoryTable.TABLE).all().execute();
        setNewValues(repositories);
    }

    protected class RepositoryHolder extends BaseRecyclerAdapter.Holder {

        private TextView mRepositoryName;

        public RepositoryHolder(View itemView) {
            super(itemView);
            mRepositoryName = (TextView) itemView.findViewById(R.id.repositoryName);
        }
    }

}
