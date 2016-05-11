package ru.arturvasilov.training.smstestapp;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Vasilov
 */
public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.SmsHolder> {

    private final Context mContext;
    private final OnItemClick mOnItemClick;

    private final List<Sms> mSmsList = new ArrayList<>();

    private final View.OnClickListener mInternalListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            mOnItemClick.onClick(mSmsList.get(position));
        }
    };

    public SmsAdapter(Context context, @NonNull OnItemClick onItemClick) {
        mContext = context;
        mOnItemClick = onItemClick;

        Cursor cursor = context.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        if (cursor != null && !cursor.isClosed() && cursor.moveToFirst()) {
            List<Sms> smsList = Sms.fromCursor(cursor);
            mSmsList.clear();
            mSmsList.addAll(smsList);
        }
    }

    @Override
    public SmsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SmsHolder(LayoutInflater.from(mContext).inflate(R.layout.sms_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SmsHolder holder, int position) {
        Sms sms = mSmsList.get(position);
        holder.itemView.setTag(position);
        holder.bind(sms);
    }

    @Override
    public int getItemCount() {
        return mSmsList.size();
    }

    public interface OnItemClick {
        void onClick(@NonNull Sms sms);
    }

    protected class SmsHolder extends RecyclerView.ViewHolder {

        private final TextView mAddressTextView;
        private final TextView mMessageTextView;

        public SmsHolder(View itemView) {
            super(itemView);
            mAddressTextView = (TextView) itemView.findViewById(R.id.smsAddress);
            mMessageTextView = (TextView) itemView.findViewById(R.id.smsMessage);
            itemView.setOnClickListener(mInternalListener);
        }

        private void bind(@NonNull Sms sms) {
            mAddressTextView.setText(sms.getAddress());
            mMessageTextView.setText(sms.getMessage());
        }
    }

}
