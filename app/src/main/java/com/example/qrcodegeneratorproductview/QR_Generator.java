package com.example.qrcodegeneratorproductview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.qrcodegeneratorandbillingsystem.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.IOException;

public class QR_Generator extends AppCompatActivity {
    PrintBluetooth pb =new PrintBluetooth();
    private Button button6,button2;
    private EditText editText1, in_printer_id;
    private ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_generator);
        button6=findViewById(R.id.button6);
        button2=findViewById(R.id.button2);
        editText1=findViewById(R.id.editText1);
        imageView1=findViewById(R.id.imageView1);
        in_printer_id=findViewById(R.id.in_printer_id);

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText1.getText().toString();
                if(text!=null && !text.isEmpty()){
                    try{
                        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
                        BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,500,500);
                        BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap((bitMatrix));
                        imageView1.setImageBitmap(bitmap);
                    }
                    catch(WriterException e){
                        e.printStackTrace();
                    }
                }
            }

        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrintBluetooth.printer_id = in_printer_id.getText().toString();
                Bitmap qrBit = printQRCode(in_printer_id.getText().toString());
                try{
                    pb.findBT();
                    pb.openBT();
                    pb.printQrCode(qrBit);
                    pb.closeBT();
                }catch(IOException l){
                    l.printStackTrace();
                }
            }
        });
    }
    private Bitmap printQRCode(String textToQR){
        MultiFormatWriter multiFormatWriter= new MultiFormatWriter();
        try{
            BitMatrix bitMatrix=multiFormatWriter.encode(textToQR,BarcodeFormat.QR_CODE,300,300);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            return bitmap;
        }catch(WriterException e){
            e.printStackTrace();
            return null;
        }

    }
}