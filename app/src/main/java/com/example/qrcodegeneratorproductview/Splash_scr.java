package com.example.qrcodegeneratorproductview;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.qrcodegeneratorandbillingsystem.R;
import com.example.qrcodegeneratorproductview.MainActivity;

public class Splash_scr extends AppCompatActivity {
    ImageView imageview1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_scr);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i =new Intent(Splash_scr.this, MainActivity.class);
                startActivity(i);
            }
        },3000);
        imageview1=findViewById(R.id.imageView1);
        imageview1.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
    }
}