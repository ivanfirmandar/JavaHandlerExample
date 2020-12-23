package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button btn;
    Handler handler;
    Thread thread2;
    Thread thread3;
    private int numberOfClick = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);
        btn = findViewById(R.id.buttonView);
        handler = new Handler();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    int localClickNumber = numberOfClick;
                    final TextView[] tvarray = {findViewById(R.id.textView),
                            findViewById(R.id.textView1),
                            findViewById(R.id.textView2),
                            findViewById(R.id.textView3),
                            findViewById(R.id.textView4)
                    };
                    Handler handler2 = new Handler();
                    @Override
                    public void run() {
                        tvarray[localClickNumber].setTextColor(Color.BLACK);
                        Log.d("this Thread",Thread.currentThread().toString());
                        Log.d("Number of Click"," " + numberOfClick);
                        for (int i = 0; i < 10; i++) {
                            final int currentCount = i;
                            Log.d(Thread.currentThread().toString()," " + i);
                            SystemClock.sleep(1000);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tvarray[localClickNumber].setText("" + currentCount);
                                }
                            });
                        }
                    }
                }).start();
                numberOfClick++;
            }
        });
    }
}