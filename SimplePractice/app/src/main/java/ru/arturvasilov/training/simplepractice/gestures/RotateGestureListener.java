package ru.arturvasilov.training.simplepractice.gestures;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * @author Artur Vasilov
 */
public class RotateGestureListener implements GestureListener {

    private static final int INVALID_POINTER_ID = -1;

    private float mFirstX;
    private float mFirstY;
    private float mSecondX;
    private float mSecondY;

    private int mFirsPointerId;
    private int mSecondPointerId;

    private float mAngle;

    public RotateGestureListener() {
        mFirsPointerId = INVALID_POINTER_ID;
        mSecondPointerId = INVALID_POINTER_ID;
    }

    @Override
    public boolean onEvent(@NonNull MotionEvent event, @NonNull View view) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mFirsPointerId = event.getPointerId(event.getActionIndex());
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                mSecondPointerId = event.getPointerId(event.getActionIndex());
                mSecondX = event.getX(event.findPointerIndex(mFirsPointerId));
                mSecondY = event.getY(event.findPointerIndex(mFirsPointerId));
                mFirstX = event.getX(event.findPointerIndex(mSecondPointerId));
                mFirstY = event.getY(event.findPointerIndex(mSecondPointerId));
                break;

            case MotionEvent.ACTION_MOVE:
                if (mFirsPointerId != INVALID_POINTER_ID && mSecondPointerId != INVALID_POINTER_ID) {
                    float nfX, nfY, nsX, nsY;
                    nsX = event.getX(event.findPointerIndex(mFirsPointerId));
                    nsY = event.getY(event.findPointerIndex(mFirsPointerId));
                    nfX = event.getX(event.findPointerIndex(mSecondPointerId));
                    nfY = event.getY(event.findPointerIndex(mSecondPointerId));

                    mAngle = angleBetweenLines(mFirstX, mFirstY, mSecondX, mSecondY, nfX, nfY, nsX, nsY);
                }
                break;

            case MotionEvent.ACTION_UP:
                mFirsPointerId = INVALID_POINTER_ID;
                break;

            case MotionEvent.ACTION_POINTER_UP:
                mSecondPointerId = INVALID_POINTER_ID;
                break;

            case MotionEvent.ACTION_CANCEL:
                mFirsPointerId = INVALID_POINTER_ID;
                mSecondPointerId = INVALID_POINTER_ID;
                break;
        }
        return true;
    }

    private float angleBetweenLines(float fX, float fY, float sX, float sY, float nfX, float nfY, float nsX, float nsY) {
        float angle1 = (float) Math.atan2((fY - sY), (fX - sX));
        float angle2 = (float) Math.atan2((nfY - nsY), (nfX - nsX));

        float angle = ((float) Math.toDegrees(angle1 - angle2)) % 360;
        if (angle < -180.f) angle += 360.0f;
        if (angle > 180.f) angle -= 360.0f;
        return angle;
    }

    @Override
    public DrawListener getDrawListener() {
        return new RotateDrawListener();
    }

    private class RotateDrawListener extends DrawListenerStub {

        @Override
        public void draw(@NonNull ImageView imageView, @NonNull Canvas canvas) {
            imageView.setRotation(mAngle);
        }
    }
}
