package com.example.qrcodegeneratorproductview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qrcodegeneratorandbillingsystem.R;

public class Database extends AppCompatActivity {
    EditText pr_name,pr_price,pr_quantity;
    Button insert,update,delete,view;
    Databasehelper DB ;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        pr_name = findViewById(R.id.pr_name);
        pr_price =findViewById(R.id.pr_price);
        pr_quantity = findViewById(R.id.pr_quantity);

        insert=findViewById(R.id.btn_insert);
        delete=findViewById(R.id.btn_delete);
        view=findViewById(R.id.btn_view);


        DB =new Databasehelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = pr_name.getText().toString();
                int priceTXT = Integer.parseInt(pr_price.getText().toString());
                int quantityTXT = Integer.parseInt(pr_quantity.getText().toString());
                if (nameTXT.isEmpty()) {
                    Toast.makeText(Database.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                DB.insertuserdata(nameTXT, priceTXT, quantityTXT);
                Toast.makeText(Database.this, "Details has been added.", Toast.LENGTH_SHORT).show();
                pr_name.setText("");
                pr_price.setText("");
                pr_quantity.setText("");
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = pr_name.getText().toString();

                Boolean checkdeletedata=DB.deletedata(nameTXT);
                if (checkdeletedata==true)
                {
                    Toast.makeText(Database.this, "Data Deleted !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Database.this, "Data not deleted !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res= DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(Database.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("id :"+res.getString(0)+"\n");
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("Price:"+res.getString(2)+"\n");
                    buffer.append("Quantity:"+res.getString(3)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Database.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}