package com.dba_droid.androidcancurencyapplication;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.concurrent.TimeUnit;

public class HandlerActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button btnStart;
    private Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        progressBar = findViewById(R.id.progress);
        btnStart = findViewById(R.id.btn_start);
        handler = new Handler() {
            public void handleMessage(Message msg) {
                int progress = msg.what;
                progressBar.setProgress(progress);
                if (progress == 20) {
                    btnStart.setEnabled(true);
                }
            }
        };
    }

    public void onclick(View v) {
        btnStart.setEnabled(false);
        Thread t = new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    downloadFile();
                    handler.sendEmptyMessage(i);
                }
            }
        });
        t.start();
    }

    void downloadFile() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
