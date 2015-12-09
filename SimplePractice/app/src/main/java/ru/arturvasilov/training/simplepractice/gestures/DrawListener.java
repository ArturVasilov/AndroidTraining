package ru.arturvasilov.training.simplepractice.gestures;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.widget.ImageView;

/**
 * @author Artur Vasilov
 */
public interface DrawListener {

    void draw(@NonNull ImageView imageView, @NonNull Canvas canvas);

}
