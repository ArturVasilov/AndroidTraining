package ru.arturvasilov.training.touch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import ru.arturvasilov.training.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //SingleDrawingView drawingView = new SingleDrawingView(this);
        //setContentView(drawingView);

        //View view = new MultiTouchDrawingView(this);
        //setContentView(view);

        setContentView(R.layout.activity_main);

        ImageView imageView = new ScaleDetectingImageView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.main_layout);
        layout.addView(imageView, params);
    }
}
