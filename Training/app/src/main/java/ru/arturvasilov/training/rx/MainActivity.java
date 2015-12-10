package ru.arturvasilov.training.rx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.arturvasilov.training.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Observable.just(1, 2, 3, 4, 5)
                .compose(new Observable.Transformer<Integer, String>() {
                    @Override
                    public Observable<String> call(Observable<Integer> integerObservable) {
                        return integerObservable.map(new Func1<Integer, String>() {
                            @Override
                            public String call(Integer integer) {
                                return String.valueOf(integer);
                            }
                        });
                    }
                });
        */
    }
}
