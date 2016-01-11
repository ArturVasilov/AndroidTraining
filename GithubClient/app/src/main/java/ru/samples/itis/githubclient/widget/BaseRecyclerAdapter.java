package ru.samples.itis.githubclient.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Artur Vasilov
 */
public abstract class BaseRecyclerAdapter<H extends BaseRecyclerAdapter.Holder, D> extends RecyclerView.Adapter<H> {

    private final List<D> mValues;

    public BaseRecyclerAdapter(@NonNull List<D> values) {
        mValues = values;
    }

    public void setNewValues(@NonNull List<D> values) {
        mValues.clear();
        mValues.addAll(values);
        notifyDataSetChanged();
    }

    public void add(@NonNull D value) {
        mValues.add(value);
        notifyDataSetChanged();
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(getLayoutId(), parent);
        return createHolder(view);
    }

    @Override
    public void onBindViewHolder(H holder, int position) {
        D value = mValues.get(position);
        populateHolder(holder, value, position);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @NonNull
    protected abstract H createHolder(@NonNull View view);

    protected abstract void populateHolder(H holder, D value, int position);

    protected abstract class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }

}
