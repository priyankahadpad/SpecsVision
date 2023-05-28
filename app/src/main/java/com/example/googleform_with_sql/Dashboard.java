package com.example.googleform_with_sql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googleform_with_sql.HomeAdapter.Most_ViewedAdapter;
import com.example.googleform_with_sql.HomeAdapter.Most_ViewedHelperClass;
import com.example.googleform_with_sql.HomeAdapter.categoryAdapter;
import com.example.googleform_with_sql.HomeAdapter.categoryHelperClass;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    RecyclerView mv_recycler;
    RecyclerView category_recycler;
    RecyclerView.Adapter adapter;
    RecyclerView.Adapter adapter1;


    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Hooks

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);


        //Tool Bar

        //navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


        imageView = (ImageView) findViewById(R.id.imageView3);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this, MainActivity.class);
                startActivity(i);
            }
        });

        mv_recycler = findViewById(R.id.mv_recycler);
        mv_recycler();

        category_recycler = findViewById(R.id.category_recycler);
        category_recycler();
    }

    private void category_recycler() {
        category_recycler.setHasFixedSize(true);
        category_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        ArrayList<categoryHelperClass> categoryLocation = new ArrayList<>();
        categoryLocation.add(new categoryHelperClass(R.drawable.image1,"Eye Glasses"));
        categoryLocation.add(new categoryHelperClass(R.drawable.sunglasses,"SunGlasses"));
        categoryLocation.add(new categoryHelperClass(R.drawable.mv4lense,"Contact Lenses"));
        categoryLocation.add(new categoryHelperClass(R.drawable.round,"Round Eye Glasses"));

        adapter1 = new categoryAdapter(categoryLocation,this);
        category_recycler.setAdapter(adapter1);
    }

    private void mv_recycler() {

        mv_recycler.setHasFixedSize(true);
        mv_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<Most_ViewedHelperClass> MostViewedLocation = new ArrayList<>();

        MostViewedLocation.add(new Most_ViewedHelperClass(R.drawable.img2_cop,"Eye Glasses","ALL-NEW TRENDY STYLES FOR YOUR EYES"));
        MostViewedLocation.add(new Most_ViewedHelperClass(R.drawable.mv2,"SunGlasses","ALL-NEW TRENDY STYLES FOR YOUR EYES"));
        MostViewedLocation.add(new Most_ViewedHelperClass(R.drawable.mv3_rayban,"Rayban","ALL-NEW TRENDY STYLES FOR YOUR EYES"));

        adapter = new Most_ViewedAdapter(MostViewedLocation);

        mv_recycler.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }
}