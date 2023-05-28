package com.example.googleform_with_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.net.ssl.SSLEngineResult;

public class MainActivity extends AppCompatActivity {
    Button Step1;
    Button Step2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Step1 = (Button) findViewById(R.id.Step1);
        Step2 = (Button) findViewById(R.id.Step2);


        Step1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoogleForm();

            }
        });


        Step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQR_Code();
            }
        });


    }



    private void openQR_Code() {
        Intent intent=new Intent(this,QR_Generator.class);
        startActivity(intent);
    }

    private void openGoogleForm() {
        Intent intent=new Intent(this,GoogleForm.class);
        startActivity(intent);
    }

}




