package ru.arturvasilov.contacts;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
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
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsHolder> {

    private final Context mContext;
    private final OnItemClick mOnItemClick;

    private final List<Contact> mContacts = new ArrayList<>();

    public ContactsAdapter(Context context, @NonNull OnItemClick onItemClick) {
        mContext = context;
        mOnItemClick = onItemClick;

        Cursor cursor = context.getContentResolver()
                .query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (cursor != null && !cursor.isClosed() && cursor.moveToFirst()) {
            List<Contact> contacts = Contact.fromCursor(cursor, context);
            mContacts.clear();
            mContacts.addAll(contacts);
        }
    }

    @Override
    public ContactsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactsHolder(LayoutInflater.from(mContext).inflate(R.layout.contacts_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ContactsHolder holder, int position) {
        Contact contact = mContacts.get(position);
        holder.itemView.setTag(position);
        holder.bind(contact);
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public interface OnItemClick {
        void onClick(@NonNull Contact contact);
    }

    protected class ContactsHolder extends RecyclerView.ViewHolder {

        private final TextView mNameTextView;
        private final TextView mPhoneTextView;

        public ContactsHolder(View itemView) {
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.contactName);
            mPhoneTextView = (TextView) itemView.findViewById(R.id.contactPhone);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag();
                    mOnItemClick.onClick(mContacts.get(position));
                }
            });
        }

        private void bind(@NonNull Contact contact) {
            mNameTextView.setText(contact.getName());
            mPhoneTextView.setText(contact.getPhoneNumber());
        }
    }

}
