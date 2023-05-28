package com.example.googleform_with_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetails extends AppCompatActivity {

    TextView productName,productPrice,productRating;
    ImageView backButton,like,big_image;
    Button buyNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent i = getIntent();

        String name = i.getStringExtra("name");
        String price = i.getStringExtra("price");
        String rating = i.getStringExtra("rating");

        productName=findViewById(R.id.prod_name);
        productPrice=findViewById(R.id.detail_price);
        productRating=findViewById(R.id.rating1);
        big_image=findViewById(R.id.big_image);
        backButton=findViewById(R.id.back_button);
        buyNow=findViewById(R.id.buy_button);
        like=findViewById(R.id.like_button);


        productName.setText(name);
        productRating.setText(rating);
        productPrice.setText(price);
        big_image.setImageResource(i.getIntExtra("image",0));


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(ProductDetails.this,shopping.class);
                startActivity(i);
                finish();
            }
        });


        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductDetails.this,"Product price is"+price,Toast.LENGTH_SHORT).show();
            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductDetails.this,"Thanks for like.",Toast.LENGTH_SHORT).show();
            }
        });

    }
}