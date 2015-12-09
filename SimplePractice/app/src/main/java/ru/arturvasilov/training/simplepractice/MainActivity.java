package ru.arturvasilov.training.simplepractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ru.arturvasilov.training.simplepractice.gestures.GesturedImage;
import ru.arturvasilov.training.simplepractice.gestures.ScaleGestureListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GesturedImage image = (GesturedImage) findViewById(R.id.image);
        image.addGestureListener(new ScaleGestureListener(this, image));
    }

}
