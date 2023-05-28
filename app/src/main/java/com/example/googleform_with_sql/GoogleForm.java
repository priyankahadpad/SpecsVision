package com.example.googleform_with_sql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import android.view.View.OnClickListener;

import static android.widget.Toast.LENGTH_LONG;

public class GoogleForm extends AppCompatActivity {

     private TextView textView;
     private Button FetchGF, btnadd,btnview;

     DatabaseHelper myDB;


    private static String ip = "192.168.1.103";
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "GoogleFormdb";
    private static String username = "sa";
    private static String password = "pinki.h";
    private static String url = "jdbc:jtds:sqlserver://" + ip + ":" + port + "/" + database;

    private Connection connection = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_form);




        textView = (TextView) findViewById(R.id.textView);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username, password);
            textView.setText("SUCCESS");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            textView.setText("ERROR");
        } catch (
                SQLException e) {
            e.printStackTrace();
            textView.setText("FAILURE");
        }


        //SQLite Dtabase
        btnadd = (Button) findViewById(R.id.adddata);
        btnview = (Button) findViewById(R.id.viewdata);
        myDB = new DatabaseHelper(this);


        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newentry = textView.getText().toString();
                if (textView.length() != 0) {
                    //mdatabasehelper.addData(newentry);
                    Adddata(newentry);
                    textView.setText("");
                } else {
                    Toast.makeText(GoogleForm.this, "click on Fetch button", Toast.LENGTH_SHORT).show();
                }
            }
            public void Adddata(String newEntry) {
                boolean insertData = myDB.addData(newEntry);
                if (insertData) {
                    Toast.makeText(GoogleForm.this, "Data Inserted Succesfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GoogleForm.this, "Failed to Add Data", Toast.LENGTH_SHORT).show();
                }
            }
        });


                btnview.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(GoogleForm.this, custom_list.class);
                        startActivity(i);
                    }
                });





    }


    public void sqlButton(View view) {
        if (connection != null) {
            Statement statement = null;
            try {
                String query = "select * from GoogleFormtb where Status='Not Used'";
                Statement smt = connection.createStatement();
                ResultSet rs1 = smt.executeQuery(query);


                if (rs1.next()) {
                    textView.setText(rs1.getString(1));
                    String Query = "update GoogleFormtb set Status='used' WHERE Links= '" + textView.getText() + "'";
                    Statement smt1 = connection.createStatement();
                    smt1.executeQuery(Query);


                } else {
                    Toast.makeText(GoogleForm.this, "All Links Are Used!..Please Try Again!!!", LENGTH_LONG).show();
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }



        } else {
            textView.setText("Connection is null");
        }
    }


}
