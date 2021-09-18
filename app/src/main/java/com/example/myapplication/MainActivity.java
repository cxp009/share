package com.example.myapplication;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

    public class MainActivity extends AppCompatActivity {
        private static final  String TAG="wwwwwww";
        int score=0;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.thirdtwo);
        }
        public  void click(View btn){
            Log.i(TAG,"onClick:AAAAAAAAA");
            if(btn.getId()==R.id.btn3){
                score+=3;
            }else if(btn.getId()==R.id.btn2){
                score+=2;
            }else if(btn.getId()==R.id.btn1){
                score++;
            }else {
                score=0;
            }
            TextView out=findViewById(R.id.score1);
            out.setText(String.valueOf(score));
        }
        public  void clickB(View btn){
            Log.i(TAG,"onClick:AAAAAAAAA");
            if(btn.getId()==R.id.btn03){
                score+=3;
            }else if(btn.getId()==R.id.btn02){
                score+=2;
            }else if(btn.getId()==R.id.btn01){
                score++;
            }else {
                score=0;
            }
            TextView out=findViewById(R.id.score2);
            out.setText(String.valueOf(score));
        }
    }
