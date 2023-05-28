package com.example.googleform_with_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.Connection;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QR_Generator extends AppCompatActivity {

    EditText qrvalue;
    EditText qrvalue1;
    Button generateBtn, SaveBtn , Back;
    ImageView qrImage;

    //google form
    Connection connect;
    String ConnectionResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r__generator);


        qrvalue = findViewById(R.id.qrInput);
        qrvalue1 = findViewById(R.id.qrInput1);
        generateBtn = findViewById(R.id.generateBtn);
        qrImage = findViewById(R.id.qrPlaceHolder);
        SaveBtn = findViewById(R.id.SaveBtn);
        SaveBtn.setVisibility(View.INVISIBLE);
        Back = findViewById(R.id.Back);



        generateBtn.setOnClickListener(v -> {
            String data = qrvalue.getText().toString();
            String data1 = qrvalue1.getText().toString();

            if (data.isEmpty() || data1.isEmpty()) {
                qrvalue.setError("Value Required!!");
                qrvalue1.setError("Value Required!!");


            }else {

                QRGEncoder qrgEncoder = new QRGEncoder(data + "\nLink:"+ "\n" +data1, QRGContents.Type.TEXT, 500);
                try {
                    Bitmap qrBits = qrgEncoder.getBitmap();
                    qrImage.setImageBitmap(qrBits);
                    Toast.makeText(QR_Generator.this,"QR Code Generated",Toast.LENGTH_LONG).show();
                    SaveBtn.setVisibility(View.VISIBLE);
                    SaveBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MediaStore.Images.Media.insertImage(getContentResolver(),qrBits,"Code_scanner",null);
                            Toast.makeText(QR_Generator.this,"Saved To Gallery", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        
    }

    public void Back (View view){
        Intent intent =new Intent(QR_Generator.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    }
