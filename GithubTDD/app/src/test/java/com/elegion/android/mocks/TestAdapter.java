package com.elegion.android.mocks;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.elegion.android.base.BaseAdapter;

import java.util.List;

/**
 * @author Artur Vasilov
 */
public class TestAdapter extends BaseAdapter<TestHolder, TestValue> {

    public TestAdapter(@NonNull List<TestValue> items) {
        super(items);
    }

    @Override
    public TestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TestHolder(parent);
    }

    @Override
    public void onBindViewHolder(TestHolder holder, int position) {
    }
}
