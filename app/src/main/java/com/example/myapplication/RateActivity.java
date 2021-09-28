package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class RateActivity extends AppCompatActivity {
    private static final String TAG = "Rate";
    EditText rmb;
    TextView show;
    private float  dollarRate = 0.1f;
    private float  euroRate = 0.2f;
    private float  wonRate = 0.3f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        rmb = findViewById(R.id.input);
        show = findViewById(R.id.output);

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
public  void openOne(View btn){
    Log.i("open", "openOne: ");
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
        if(item.getItemId()==R.id.menu_set){
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

           if (requestCode == 1 && resultCode == 2){
                Bundle bundle = data.getExtras();
                dollarRate = bundle.getFloat("key_dollar",0.1f);
                euroRate = bundle.getFloat("key_euro",0.1f);
                wonRate = bundle.getFloat("key_won",0.1f);
                Log.i(TAG, "onActivityResult: dollarRate="+dollarRate);
                Log.i(TAG, "onActivityResult: euroRate="+euroRate);
                Log.i(TAG, "onActivityResult: wonRate="+wonRate);
            }
        super.onActivityResult(requestCode, resultCode, data);
        }

    }




