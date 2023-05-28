package com.example.googleform_with_sql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class shopping extends AppCompatActivity {

    private RecyclerView bestSellerRecyclerView;
    BestSellerRecyclerAdapter bestSellerRecyclerAdapter;
    List<BestSeller> bestSellerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        bestSellerRecyclerView = findViewById(R.id.best_Seller_recycler);


        bestSellerList = new ArrayList<>();

        BestSeller bestSeller1 = new BestSeller("Vincent Chase Polarized",R.drawable.eyeg2,"₹1499","Frame Width:144 mm","4.6");
        BestSeller bestSeller2 = new BestSeller("John Jacobs",R.drawable.eyeg4,"₹1599","Frame Width:142 mm","4.5");
        BestSeller bestSeller3 = new BestSeller("John Jacobs",R.drawable.product,"₹1000","Frame Width:132 mm","3.9");
        BestSeller bestSeller4 = new BestSeller("John Jacobs",R.drawable.eyeg3,"₹1800","Frame Width:138 mm","4.0");
        BestSeller bestSeller5 = new BestSeller("John Jacobs",R.drawable.eyeg1,"₹1800","Frame Width:130 mm","4.0");

        bestSellerList.add(bestSeller1);
        bestSellerList.add(bestSeller2);
        bestSellerList.add(bestSeller3);
        bestSellerList.add(bestSeller4);
        bestSellerList.add(bestSeller5);

        bestSellerRecyclerAdapter = new BestSellerRecyclerAdapter(this,bestSellerList);
        bestSellerRecyclerView.setHasFixedSize(true);
        bestSellerRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        bestSellerRecyclerView.setAdapter(bestSellerRecyclerAdapter);
        bestSellerRecyclerAdapter.notifyDataSetChanged();

    }
}