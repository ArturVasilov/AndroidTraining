package ru.arturvasilov.training.touch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author Artur Vasilov
 */
public class MultiTouchDrawingView extends View {

    private static final int SIZE = 60;

    private static final int[] COLORS = { Color.BLUE, Color.GREEN, Color.MAGENTA,
            Color.BLACK, Color.CYAN, Color.GRAY, Color.RED, Color.DKGRAY,
            Color.LTGRAY, Color.YELLOW };

    private final SparseArray<PointF> mActivePointers;
    private final Paint mPaint;

    public MultiTouchDrawingView(Context context) {
        super(context);

        mActivePointers = new SparseArray<>();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // set painter color to a color you like
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();
        int pointerId = event.getPointerId(pointerIndex);
        int actionMasked = event.getActionMasked();

        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: {
                PointF point = new PointF();
                point.x = event.getX(pointerIndex);
                point.y = event.getY(pointerIndex);
                mActivePointers.put(pointerId, point);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                for (int size = event.getPointerCount(), i = 0; i < size; i++) {
                    PointF point = mActivePointers.get(event.getPointerId(i));
                    if (point != null) {
                        point.x = event.getX(i);
                        point.y = event.getY(i);
                    }
                }
                break;
            }

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL: {
                mActivePointers.remove(pointerId);
                break;
            }
        }

        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int size = mActivePointers.size(), i = 0; i < size; i++) {
            PointF point = mActivePointers.valueAt(i);
            if (point != null) {
                mPaint.setColor(COLORS[i % 9]);
                canvas.drawCircle(point.x, point.y, SIZE, mPaint);
            }
        }
    }
}
