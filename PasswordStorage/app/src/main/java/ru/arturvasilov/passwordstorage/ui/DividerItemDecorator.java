package ru.arturvasilov.passwordstorage.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Artur Vasilov
 */
public class DividerItemDecorator extends RecyclerView.ItemDecoration {

    private final Drawable mDivider;

    public DividerItemDecorator(@NonNull Context context, @DrawableRes int dividerResId) {
        mDivider = ContextCompat.getDrawable(context, dividerResId);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mDivider == null) {
            return;
        }
        if (parent.getChildAdapterPosition(view) < 1) {
            return;
        }
        outRect.top = mDivider.getIntrinsicHeight();
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mDivider == null) {
            super.onDrawOver(c, parent, state);
            return;
        }

        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();

        for (int i = 1; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int size = mDivider.getIntrinsicHeight();
            final int top = child.getTop() - params.topMargin;
            final int bottom = top + size;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

}
