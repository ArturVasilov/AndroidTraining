package com.elegion.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elegion.android.R;
import com.elegion.android.base.BaseAdapter;
import com.elegion.android.content.Repository;

import java.util.ArrayList;

/**
 * @author Artur Vasilov
 */
public class RepositoriesAdapter extends BaseAdapter<RepositoriesAdapter.Holder, Repository> {

    public RepositoriesAdapter() {
        super(new ArrayList<Repository>());
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repositoty_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Repository repository = getItem(position);
        holder.mName.setText(repository.getName());
        holder.mLanguage.setText(repository.getLanguage());
    }

    static class Holder extends RecyclerView.ViewHolder {

        private final TextView mName;
        private final TextView mLanguage;

        public Holder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.repositoryName);
            mLanguage = (TextView) itemView.findViewById(R.id.repositoryLanguage);
        }
    }

}
