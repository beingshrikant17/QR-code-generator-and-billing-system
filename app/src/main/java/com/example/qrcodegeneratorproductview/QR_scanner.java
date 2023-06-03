package com.example.qrcodegeneratorproductview;


import android.Manifest;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.qrcodegeneratorandbillingsystem.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.LinkedList;

public class QR_scanner extends AppCompatActivity {
    String Product ;
    String Price ;
    PrintBluetooth pb1 = new PrintBluetooth();
    private Button button,button3;
    Databasehelper DB ;


    private String stringFilePath = Environment.getExternalStorageDirectory().getPath() + "/Download/Qr_app_bill.pdf";
    File file =new File(stringFilePath);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);
        button = findViewById(R.id.button);
        //button3 = findViewById(R.id.button3);


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        button.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(QR_scanner.this);
                intentIntegrator.setDesiredBarcodeFormats(intentIntegrator.QR_CODE);
                intentIntegrator.setCameraId(0);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setPrompt("Scanning");
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setBarcodeImageEnabled(true);
                intentIntegrator.initiateScan();
            }
        });
        /*button3.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String nameTXT = Product;
                int priceTXT =Integer.parseInt(Price);

                Boolean checkupdatedata = DB.updateuserdata(nameTXT,priceTXT);
                if(checkupdatedata==true)
                    Toast.makeText(QR_scanner.this, "Data updated!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(QR_scanner.this, "Not updated", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
    /*public void buttonCreatePDF(View view) throws DocumentException {
        LinkedList<String> Product_ll=new LinkedList<String>();
        LinkedList<Integer>Price_ll= new LinkedList<Integer>();
        Product_ll.add(Product);
        Price_ll.add(Integer.parseInt(Price));
        Iterator<String>itr= Product_ll.iterator();
        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Document document = new Document();
        while(itr.hasNext()){
        document.add((Element) Product_ll);
        document.add((Element) Price_ll);}

        pdfDocument.finishPage(page);
        try {
            pdfDocument.writeTo(new FileOutputStream(file));
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error in creating", Toast.LENGTH_SHORT).show();
        }
        pdfDocument.close();
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null && result.getContents() != null) {
            String splitString[] = result.getContents().split("-");
            Product = splitString[0];
            Price = splitString[1];
            new AlertDialog.Builder(QR_scanner.this).setTitle("Scan result")
                    .setMessage("Product: " + splitString[0] + " & " + "Price: " + splitString[1])
                    .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                            ClipData data = ClipData.newPlainText("result", result.getContents());
                            manager.setPrimaryClip(data);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}