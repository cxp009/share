package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "tzbc";
    HtmlService htmlService;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherThread.start();
    }

    Thread weatherThread = new Thread(new Runnable() {
        @Override
        public void run() {
            getWeather();
        }
    });

    private void getWeather() {
        String path = "https://www.waihui321.com/";
        try {
            String html = HtmlService.getHtml(path);
            Log.i(TAG, "html : " + html);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
