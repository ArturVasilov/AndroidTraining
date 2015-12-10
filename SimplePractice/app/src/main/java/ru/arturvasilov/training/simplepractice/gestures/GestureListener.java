package ru.arturvasilov.training.simplepractice.gestures;

import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author Artur Vasilov
 */
public interface GestureListener {

    boolean onEvent(@NonNull MotionEvent event, @NonNull View view);

    DrawListener getDrawListener();

}
