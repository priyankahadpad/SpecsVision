package com.example.googleform_with_sql.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googleform_with_sql.R;

import java.util.ArrayList;

public class Most_ViewedAdapter extends RecyclerView.Adapter <Most_ViewedAdapter.MostViewedHolder> {

    ArrayList<Most_ViewedHelperClass> MostViewedLocation;

    public Most_ViewedAdapter(ArrayList<Most_ViewedHelperClass> mostViewedLocation) {
        MostViewedLocation = mostViewedLocation;
    }

    @NonNull
    @Override
    public MostViewedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_card_design,parent,false);
        MostViewedHolder mostViewedHolder = new MostViewedHolder(view);

        return mostViewedHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewedHolder holder, int position) {

        Most_ViewedHelperClass most_viewedHelperClass = MostViewedLocation.get(position);
        holder.image.setImageResource(most_viewedHelperClass.getImage());
        holder.title.setText(most_viewedHelperClass.getTitle());
        holder.desc.setText(most_viewedHelperClass.getDescription());

    }

    @Override
    public int getItemCount() {
        return MostViewedLocation.size();
    }


    public static class MostViewedHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title,desc;

        public MostViewedHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.mv_image);
            title = itemView.findViewById(R.id.mv_title);
            desc = itemView.findViewById(R.id.mv_desc);
        }
    }


}
