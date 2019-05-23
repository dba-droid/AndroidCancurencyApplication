package com.dba_droid.androidcancurencyapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = findViewById(R.id.progress);

        new ProgressAsyncTask().execute(50);

    }


    //correct version - static class
    private class ProgressAsyncTask extends AsyncTask<Integer, Integer, String> {

        private final String LOG_TAG = "LOG_TAG";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(LOG_TAG, "onPreExecute, thread name - " + Thread.currentThread().getName());
        }

        @Override
        protected String doInBackground(Integer... maxProgress) {

            Log.i(LOG_TAG, "doInBackground, thread name - " + Thread.currentThread().getName());
            for (int i = 0; i < maxProgress[0]; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return "finish loading !";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (pb != null) {
                pb.setProgress(values[0]);
            }
            Log.i(LOG_TAG, "onProgressUpdate values: " + values[0] + ", thread name - " + Thread.currentThread().getName());
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            Log.i(LOG_TAG, "onPostExecute result: " + result + ", thread name - " + Thread.currentThread().getName());
            super.onPostExecute(result);
        }

    }

}
