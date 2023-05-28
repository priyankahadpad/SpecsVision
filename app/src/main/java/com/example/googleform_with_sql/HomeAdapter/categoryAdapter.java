package com.example.googleform_with_sql.HomeAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googleform_with_sql.Dashboard;
import com.example.googleform_with_sql.R;
import com.example.googleform_with_sql.shopping;

import java.util.ArrayList;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.categoryViewHolder> {

    ArrayList<categoryHelperClass> categoryLocation;
    private Context context;



    public categoryAdapter(ArrayList<categoryHelperClass> categoryLocation, Context context) {
        this.categoryLocation = categoryLocation;
        this.context = context;
    }

    @NonNull
    @Override
    public categoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_card_design,parent,false);
        categoryViewHolder categoryViewHolder = new categoryViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull categoryViewHolder holder, int position) {

        categoryHelperClass categoryHelperClass = categoryLocation.get(position);
        holder.image.setImageResource(categoryHelperClass.getImage());
        holder.title.setText(categoryHelperClass.getTitle());

    }

    @Override
    public int getItemCount() {
        return categoryLocation.size();
    }

    public class categoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView title;


        public categoryViewHolder(@NonNull View itemView) {
            super(itemView);

            //
            image = itemView.findViewById(R.id.category_img);
            title = itemView.findViewById(R.id.category_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (getLayoutPosition() == 0) {
                Intent intent = new Intent(context.getApplicationContext(), shopping.class);
                context.startActivity(intent);
            }
            else {
                Toast.makeText(context,"Not Set Content!",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
