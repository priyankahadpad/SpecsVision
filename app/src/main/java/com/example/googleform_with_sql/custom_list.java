package com.example.googleform_with_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.os.Build.ID;

public class custom_list extends AppCompatActivity {

    private ListView ListView;
    DatabaseHelper myDB;
    Integer indexval;
    String item;
    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);



        ListView = (ListView) findViewById(R.id.ListView1);
        myDB = new DatabaseHelper(this);


        populateListView();

        ArrayAdapter<String>adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        ListView.setAdapter(adapter);


            //select item
            ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    item = parent.getItemAtPosition(position).toString();
                    indexval = position;
                    Toast.makeText(custom_list.this, item, Toast.LENGTH_SHORT).show();
                    String value = item;
                    Intent intent = new Intent(custom_list.this, GF_Responses.class);
                    intent.putExtra("key", value);
                    startActivity(intent);

                }
            });

            //delete item
            ListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    final int which_item = position;
                    new AlertDialog.Builder(custom_list.this)
                            .setIcon(android.R.drawable.ic_delete)
                            .setTitle("Are you sure ?")
                            .setMessage("Do you want to delete this item")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                            {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    list.remove(which_item);
                                    myDB.deleteData(id);
                                    Toast.makeText(custom_list.this, "Deleted", Toast.LENGTH_SHORT).show();
                                    adapter.notifyDataSetChanged();

                                }


                            })
                            .setNegativeButton("No", null).show();

                    return true;

                }
            });

        }


    public void populateListView() {
        Cursor data = myDB.getData();
        while (data.moveToNext()){
            list.add(data.getString(1));
            myDB.close();
        }
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        ListView.setAdapter(adapter);
    }
}