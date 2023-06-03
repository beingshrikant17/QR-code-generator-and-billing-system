package com.example.qrcodegeneratorproductview;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.qrcodegeneratorandbillingsystem.R;

public class MainActivity extends AppCompatActivity {
    private Button button,button5,button8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQR_Generator();
            }
        });

        try {
            button5 = (Button)findViewById(R.id.button5);
            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openDatabase();
                }
            });}catch(NullPointerException a)
        {
            Toast.makeText(null, "", Toast.LENGTH_SHORT).show();
        }

        button8 = (Button)findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQR_scanner();
            }
        });

    }
    public void openQR_Generator(){
        Intent intent =new Intent(this,QR_Generator.class);
        startActivity(intent);
    }
    public void openDatabase(){
        Intent intent =new Intent(this,Database.class);
        startActivity(intent);
    }
    public void openQR_scanner(){
        Intent intent =new Intent(this,QR_scanner.class);
        startActivity(intent);
    }
}