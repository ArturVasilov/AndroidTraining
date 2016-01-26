package ru.samples.itis.githubclient.widget;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    private OnItemClickListener<D> mItemClickListener;

    public BaseRecyclerAdapter(@NonNull List<D> values) {
        mValues = values;
    }

    public void setNewValues(@NonNull List<D> values) {
        mValues.clear();
        mValues.addAll(values);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(@Nullable OnItemClickListener<D> listener) {
        mItemClickListener = listener;
    }

    public void add(@NonNull D value) {
        mValues.add(value);
        notifyDataSetChanged();
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(getLayoutId(), parent, false);
        return createHolder(view);
    }

    @Override
    public void onBindViewHolder(H holder, int position) {
        D value = mValues.get(position);
        holder.itemView.setTag(position);
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

    protected abstract void populateHolder(@NonNull H holder, @NonNull D value, int position);

    public interface OnItemClickListener<D> {
        void onItemClick(int position, D value);
    }

    protected abstract class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                if (mItemClickListener != null) {
                    int position = (int) v.getTag();
                    D value = mValues.get(position);
                    mItemClickListener.onItemClick(position, value);
                }
            });
        }
    }

}
