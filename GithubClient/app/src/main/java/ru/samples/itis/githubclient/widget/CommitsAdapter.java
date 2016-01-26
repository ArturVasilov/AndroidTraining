package ru.samples.itis.githubclient.widget;

import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.arturvasilov.sqlite.SQLite;
import ru.arturvasilov.sqlite.observers.TableObserver;
import ru.samples.itis.githubclient.R;
import ru.samples.itis.githubclient.content.Commit;
import ru.samples.itis.githubclient.content.Repository;
import ru.samples.itis.githubclient.content.tables.CommitsTable;

/**
 * @author Artur Vasilov
 */
public class CommitsAdapter extends RecyclerView.Adapter<CommitsAdapter.Holder> implements TableObserver {

    private final List<Commit> mCommits;

    private final Repository mRepository;

    public CommitsAdapter(@NonNull List<Commit> commits, @NonNull Repository repository) {
        mCommits = commits;
        mRepository = repository;
    }

    @ViewType
    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ViewType.HEADER : ViewType.COMMIT_ITEM;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Commit commit = mCommits.get(position);
        holder.populateHolder(position, commit);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, @ViewType int viewType) {
        @LayoutRes int layoutId = viewType == ViewType.HEADER ? R.layout.commits_header : R.layout.commit_item;
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
    }

    @Override
    public int getItemCount() {
        return mCommits.size() + 1;
    }

    @IntDef({ViewType.HEADER, ViewType.COMMIT_ITEM})
    private @interface ViewType {
        int HEADER = 1;
        int COMMIT_ITEM = 2;
    }

    @Override
    public void onTableChanged() {
        List<Commit> commits = SQLite.get()
                .query(CommitsTable.TABLE)
                .all()
                .where(CommitsTable.REPOSITORY_NAME + "=?")
                .whereArgs(new String[]{mRepository.getName()})
                .execute();

        mCommits.clear();
        mCommits.addAll(commits);
        notifyDataSetChanged();
    }

    protected class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }

        protected void populateHolder(int position, @NonNull Commit commit) {
            if (position == 0) {

            } else {

            }
        }
    }

}
