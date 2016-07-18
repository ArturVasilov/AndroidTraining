package ru.arturvasilov.passwordstorage.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.arturvasilov.passwordstorage.R;
import ru.arturvasilov.passwordstorage.data.AuthData;

/**
 * @author Artur Vasilov
 */
public class AuthDataAdapter extends RecyclerView.Adapter<AuthDataAdapter.ViewHolder> {

    private final List<AuthData> mAuthData;

    private final OnItemClickListener mOnItemClickListener;

    private final View.OnClickListener mInternalListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = (int) view.getTag();
            mOnItemClickListener.onItemClick(mAuthData.get(position));
        }
    };

    public AuthDataAdapter(@NonNull OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        mAuthData = new ArrayList<>();
    }

    public void changeDataSet(@NonNull List<AuthData> authData) {
        mAuthData.clear();
        mAuthData.addAll(authData);

        //TODO : remove
        if (mAuthData.isEmpty()) {
            mAuthData.add(new AuthData("Test description", "test_login", "test_email", "test_password"));
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.auth_data_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(mInternalListener);
        holder.mDescription.setText(mAuthData.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mAuthData.size();
    }

    public interface OnItemClickListener {

        void onItemClick(@NonNull AuthData authData);

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            mDescription = (TextView) itemView.findViewById(R.id.description);
        }
    }

}
