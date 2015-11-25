package ru.arturvasilov.training.touch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Vasilov
 */
public class SingleDrawingView extends View {

    private final Paint mPaint;
    private final Path mPath;

    private List<PointF> mPoints = new ArrayList<>();

    public SingleDrawingView(Context context) {
        super(context);
        mPaint = new Paint();

        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(6f);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mPoints.isEmpty()) {
            return;
        }

        mPath.reset();
        PointF point = mPoints.get(0);
        mPath.moveTo(point.x, point.y);
        for (int i = 1; i < mPoints.size(); i++) {
            point = mPoints.get(i);
            mPath.lineTo(point.x, point.y);
        }

        point = mPoints.get(mPoints.size() - 1);
        mPath.addCircle(point.x, point.y, 50, Path.Direction.CW);

        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPoints.add(new PointF(eventX, eventY));
                return true;

            case MotionEvent.ACTION_MOVE:
                mPoints.add(new PointF(eventX, eventY));
                break;

            case MotionEvent.ACTION_UP:
                mPoints.clear();
                mPath.reset();
                break;

            default:
                return false;
        }

        invalidate();
        return true;
    }
}
