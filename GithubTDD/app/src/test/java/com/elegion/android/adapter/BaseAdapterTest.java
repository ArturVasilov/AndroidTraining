package com.elegion.android.adapter;

import android.support.v7.widget.RecyclerView;

import com.elegion.android.base.BaseAdapter;
import com.elegion.android.mocks.TestAdapter;
import com.elegion.android.mocks.TestHolder;
import com.elegion.android.mocks.TestValue;
import com.elegion.android.widget.EmptyRecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * @author Artur Vasilov
 */
@RunWith(JUnit4.class)
public class BaseAdapterTest {

    private List<TestValue> mValues;

    @Before
    public void setUp() throws Exception {
        mValues = new ArrayList<>();
        mValues.add(new TestValue(5));
        mValues.add(new TestValue(10));
        mValues.add(new TestValue(15));
        mValues.add(new TestValue(20));
    }

    @Test
    public void testCount() throws Exception {
        BaseAdapter<TestHolder, TestValue> adapter = new TestAdapter(mValues);
        assertEquals(4, adapter.getItemCount());
    }

    @Test
    public void testGet() throws Exception {
        BaseAdapter<TestHolder, TestValue> adapter = new TestAdapter(mValues);
        TestValue value = adapter.getItem(2);
        assertEquals(15, value.getValue());
    }

    @Test
    public void testAttach() throws Exception {
        EmptyRecyclerView recyclerView = mock(EmptyRecyclerView.class);
        doNothing().when(recyclerView).setAdapter(any(RecyclerView.Adapter.class));

        BaseAdapter<TestHolder, TestValue> adapter = spy(new TestAdapter(mValues));
        doNothing().when(adapter).refreshRecycler();
        adapter.attachToRecyclerView(recyclerView);

        verify(recyclerView).setAdapter(any(RecyclerView.Adapter.class));
    }

    @Test
    public void testAddItem() throws Exception {
        BaseAdapter<TestHolder, TestValue> adapter = spy(new TestAdapter(mValues));
        doNothing().when(adapter).refreshRecycler();
        adapter.add(new TestValue(5));
        assertEquals(5, adapter.getItemCount());

        verify(adapter).refreshRecycler();
    }

    @Test
    public void testSetNewValues() throws Exception {
        BaseAdapter<TestHolder, TestValue> adapter = spy(new TestAdapter(mValues));
        doNothing().when(adapter).refreshRecycler();

        List<TestValue> values = new ArrayList<TestValue>() {{
           add(new TestValue(10));
           add(new TestValue(17));
           add(new TestValue(19));
        }};

        adapter.setNewValues(values);

        assertEquals(3, adapter.getItemCount());
        verify(adapter).refreshRecycler();
    }

    @Test
    public void testClearItems() throws Exception {
        BaseAdapter<TestHolder, TestValue> adapter = spy(new TestAdapter(mValues));
        doNothing().when(adapter).refreshRecycler();

        adapter.clear();

        assertEquals(0, adapter.getItemCount());
        verify(adapter).refreshRecycler();
    }

}
