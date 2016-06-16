/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.hellojni;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;


public class HelloJni extends Activity {

    static {
        //System.loadLibrary("hello-jni");
        System.loadLibrary("bitmaps");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView firstImage = (ImageView) findViewById(R.id.firstImage);
        firstImage.setImageResource(R.drawable.test_bitmap);

        ImageView secondImage = (ImageView) findViewById(R.id.secondImage);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test_bitmap);
        bitmap = nativeTransform(bitmap);
        secondImage.setImageBitmap(bitmap);

        //Toast.makeText(this, stringFromJNI(), Toast.LENGTH_LONG).show();
    }

    //public native String stringFromJNI();

    public native Bitmap nativeTransform(Bitmap bitmap);
}
