package ru.arturvasilov.training.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * @author Artur Vasilov
 */
public class SimpleTouchButton extends Button {

    public SimpleTouchButton(Context context) {
        super(context);
    }

    public SimpleTouchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleTouchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        return result;
    }
}
