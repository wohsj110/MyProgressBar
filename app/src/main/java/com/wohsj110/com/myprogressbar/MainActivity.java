package com.wohsj110.com.myprogressbar;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private MyProgressBar myprogressbar;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myprogressbar = (MyProgressBar) findViewById(R.id.myprogressbar);
        handler = new Handler();
        myprogressbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!myprogressbar.isFinish()){
                    myprogressbar.toggle();
                }
            }
        });

        downLoad();
    }

    private void downLoad() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 101; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    final int finalI = i;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            myprogressbar.setProgress(finalI);
                            if (finalI == 100){
                                myprogressbar.finishLoad();
                            }
                        }
                    });
                }
            }
        }).start();
    }
}

