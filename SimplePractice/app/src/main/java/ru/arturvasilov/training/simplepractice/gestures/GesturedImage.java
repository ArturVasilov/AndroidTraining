package ru.arturvasilov.training.simplepractice.gestures;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Vasilov
 */
public class GesturedImage extends ImageView {

    private final List<GestureListener> mListeners = new ArrayList<>();
    private GestureListener mListener;

    public GesturedImage(Context context) {
        super(context);
    }

    public GesturedImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GesturedImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GesturedImage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void addGestureListener(@NonNull GestureListener listener) {
        mListeners.add(listener);
    }

    public void removeGestureListener(@NonNull GestureListener listener) {
        mListeners.remove(listener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        for (GestureListener listener : mListeners) {
            if (listener.onEvent(event, this)) {
                mListener = listener;
                break;
            }
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mListener != null) {
            mListener.getDrawListener().draw(this, canvas);
        }
    }
}
