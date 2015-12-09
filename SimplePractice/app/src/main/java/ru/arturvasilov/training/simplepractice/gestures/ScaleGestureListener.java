package ru.arturvasilov.training.simplepractice.gestures;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

/**
 * @author Artur Vasilov
 */
public class ScaleGestureListener implements GestureListener {

    private float mScaleFactor = 1.0f;

    private final ScaleGestureDetector mGestureDetector;

    private final ImageView mImageView;

    public ScaleGestureListener(Context context, @NonNull ImageView imageView) {
        mGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
        mImageView = imageView;
    }

    @Override
    public boolean onEvent(@NonNull MotionEvent event, @NonNull View view) {
        boolean result = mGestureDetector.onTouchEvent(event);
        if (result) {
            mImageView.invalidate();
        }
        return result;
    }

    @Override
    public DrawListener getDrawListener() {
        return new ScaleDrawListener();
    }

    private class ScaleDrawListener implements DrawListener {

        @Override
        public void draw(@NonNull ImageView imageView, @NonNull Canvas canvas) {
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                canvas.save();
                canvas.drawColor(Color.WHITE);
                canvas.scale(mScaleFactor, mScaleFactor);
                drawable.draw(canvas);
                canvas.restore();
            }
        }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

            mImageView.invalidate();
            return true;
        }
    }
}
