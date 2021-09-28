package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RateActivity extends AppCompatActivity  implements Runnable{
    private static final String TAG = "Rate";
    EditText rmb;
    TextView show;
    Handler handler;
    private float  dollarRate = 0.1f;
    private float  euroRate = 0.2f;
    private float  wonRate = 0.3f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        rmb = findViewById(R.id.input);
        show = findViewById(R.id.output);
        SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
         dollarRate = sp.getFloat("dollar_rate", 0.1f);
        euroRate = sp.getFloat("euro_rate", 0.1f);
       wonRate = sp.getFloat("won_rate", 0.1f);
        Log.i(TAG, "onCreate: dollarRate="+dollarRate);
         Log.i(TAG, "onCreate: euroRate="+euroRate);
          Log.i(TAG, "onCreate: wonRate="+wonRate);
           Thread t = new Thread( this);
            t.start();
            Handler handler = new Handler(){
                @Override
                public void handleMessage(@NonNull Message msg) {
                               if(msg.what==5){
                                   String str =(String) msg.obj;
                                   Log.i(TAG, "handleMessage: getMessage"+str);
                                   show.setText(str);
                               }
                    super.handleMessage(msg);
                }
            };


    }
    public void click(View btn) {
        EditText input = findViewById(R.id.input);
        String inp = input.getText().toString();
        if (inp.length() > 0) {

            Log.i(TAG, "click: inp=" + inp);
            float num = Float.parseFloat(inp);
            float r =0 ;

        if (btn.getId() == R.id.dollar) {
               r = num * dollarRate;
        } else if (btn.getId() == R.id.euro) {
             r = num * euroRate;
        } else if (btn.getId() == R.id.won) {
             r = num * wonRate;
        }
        Log.i(TAG, "click: inp=" + inp);
        TextView show = findViewById(R.id.output);
        show.setText(String.valueOf(r));

    }
}
public  void openOne(View btn){ openconfig();}
   private void openconfig(){
    Intent config  = new Intent(this,activity.class);

    config.putExtra("dollar_rate_key",dollarRate);
    config.putExtra("euro_rate_key",euroRate);
    config.putExtra("won_rate_key",wonRate);
    Log.i(TAG, "openOne: dollar_rate_key"+dollarRate);
    Log.i(TAG, "openOne: euro_rate_key"+euroRate);
    Log.i(TAG, "openOne: won_rate_key"+wonRate);
    //startActivity(config);
    startActivityForResult(config,1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rate,menu);
        return true;
    }

    private void extracted(@NonNull MenuItem item) {

       openconfig();}
    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

           if (requestCode == 1 && resultCode == 2) {
               Bundle bundle = data.getExtras();
               dollarRate = bundle.getFloat("key_dollar", 0.1f);
               euroRate = bundle.getFloat("key_euro", 0.1f);
               wonRate = bundle.getFloat("key_won", 0.1f);
               Log.i(TAG, "onActivityResult: dollarRate=" + dollarRate);
               Log.i(TAG, "onActivityResult: euroRate=" + euroRate);
               Log.i(TAG, "onActivityResult: wonRate=" + wonRate);

               SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
               SharedPreferences.Editor editor = sp.edit();
               editor.putFloat("dollar_rate", dollarRate);
               editor.putFloat("euro_rate", euroRate);
               editor.putFloat("won_rate", wonRate);
               editor.commit();
           }
               super.onActivityResult(requestCode, resultCode, data);

        }
public void run() {
           for(int i=1;i<3;i++) {
               Log.i(TAG, "run: run....");

               try {
                   Thread.sleep(200);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
    Log.i(TAG, "run: ....");
    Message msg = handler.obtainMessage(5);
   // msg.what = 6;
    msg.obj = "Hello";
    handler.sendMessage(msg);
    URL url = null;
    try {
        url = new URL("https://www.waihui321.com/");
         HttpURLConnection http = (HttpURLConnection) url.openConnection();
        InputStream in = http.getInputStream();
        String html = inputStream2String(in);
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
        
        
        
    }


}
private  String inputStream2String(InputStream inputStream) throws IOException {
                 final int bufferSize = 1024;
                 final char[] buffer = new char[bufferSize];
                 final StringBuilder out = new StringBuilder();
                 Reader in = new InputStreamReader(inputStream,"UTF-8");
                 for(; ;){
                     int rsz = in.read(buffer,0,buffer.length);
                     if(rsz<0)
                         break;
                         out.append(buffer,0,rsz);
                     }
                     return out.toString();
                 }

}






