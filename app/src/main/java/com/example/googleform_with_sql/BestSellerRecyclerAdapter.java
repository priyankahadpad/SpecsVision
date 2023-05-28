package com.example.googleform_with_sql;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BestSellerRecyclerAdapter extends RecyclerView.Adapter <BestSellerRecyclerAdapter.BestSellerViewHolder> {

    private Context context;
    private List<BestSeller> bestSellerList;

    public BestSellerRecyclerAdapter(Context context, List<BestSeller> bestSellerList) {
        this.context = context;
        this.bestSellerList = bestSellerList;
    }




    @Override
    public BestSellerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.best_seller_recycler_item,parent,false);
        final BestSellerViewHolder viewHolder= new BestSellerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BestSellerRecyclerAdapter.BestSellerViewHolder holder, int position) {

        holder.itemName.setText(bestSellerList.get(position).getName());
        holder.price.setText(bestSellerList.get(position).getPrice());
        holder.rating.setText(bestSellerList.get(position).getRating());
        holder.type.setText(bestSellerList.get(position).getType());
        holder.itemImage.setImageResource(bestSellerList.get(position).getImage());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,ProductDetails.class);
                i.putExtra("name",bestSellerList.get(position).getName());
                i.putExtra("rating",bestSellerList.get(position).getRating());
                i.putExtra("price",bestSellerList.get(position).getPrice());
                i.putExtra("image",bestSellerList.get(position).getImage());
                context.startActivity(i);




            }
        });
    }

    @Override
    public int getItemCount() {
        return bestSellerList.size();
    }

    public static class BestSellerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView itemImage;
        TextView price,rating,itemName, type;
        LinearLayout linearLayout_BestSeller;

        public BestSellerViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout_BestSeller = itemView.findViewById(R.id.best_Seller);
            itemImage = itemView.findViewById(R.id.eyeglasses);
            price = itemView.findViewById(R.id.price);
            rating = itemView.findViewById(R.id.rating);
            itemName = itemView.findViewById(R.id.name);
            type = itemView.findViewById(R.id.type);
        }

        @Override
        public void onClick(View v) {


        }
    }
}

