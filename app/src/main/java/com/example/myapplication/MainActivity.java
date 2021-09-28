package com.example.myapplication;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

    public class MainActivity extends AppCompatActivity {
        private static final  String TAG="wwwwwww";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


        }
        public  void click(View btn){

            Log.i(TAG,"click:AAAAAAAAA");
            EditText input = findViewById(R.id.input);
            String inp = input.getText().toString();
           if(inp.length()>0) {
               Log.i(TAG, "click: inp=" + inp);
               float num = Float.parseFloat(inp);
               float r = 0;
               if (btn.getId() == R.id.dollar) {
                   r = num * 0.28f;
               } else if (btn.getId() == R.id.euro) {
                   r = num * 0.21f;
               } else if (btn.getId() == R.id.dor) {
                   r = num * 501;
               }
               Log.i(TAG, "click: inp=" + inp);
               TextView show = findViewById(R.id.output);
               show.setText(String.valueOf(r));
           }else {

                   Toast.makeText(this, "请输入数字", Toast.LENGTH_SHORT).show();

               }

           }
        }

