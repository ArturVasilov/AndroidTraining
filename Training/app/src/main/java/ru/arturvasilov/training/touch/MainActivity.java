package ru.arturvasilov.training.touch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
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

        Button button = new SimpleTouchButton(this);
        button.setText("AAAAAAAAAA\nAAAAAAAAAA\nAAAAAAAAAAAAAAA\nAAAAAAAAAA\nAA");

        FrameLayout frameLayout = new LayoutView(this);
        frameLayout.addView(button);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        RootView rootView = new RootView(this);
        rootView.addView(frameLayout, params);

        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.main_layout);
        layout.addView(rootView, params2);
    }
}
