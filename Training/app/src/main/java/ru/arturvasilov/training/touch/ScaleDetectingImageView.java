package ru.arturvasilov.training.touch;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import ru.arturvasilov.training.R;

/**
 * @author Artur Vasilov
 */
public class ScaleDetectingImageView extends ImageView {

    private float mScaleFactor = 1.0f;

    private Drawable mDrawable;
    private ScaleGestureDetector mGestureDetector;

    public ScaleDetectingImageView(Context context) {
        super(context);
        init();
    }

    public ScaleDetectingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScaleDetectingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ScaleDetectingImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mDrawable = ContextCompat.getDrawable(getContext(), R.mipmap.ic_launcher);
        mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
        mGestureDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor);
        mDrawable.draw(canvas);
        canvas.restore();
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

            invalidate();
            return true;
        }
    }
}
