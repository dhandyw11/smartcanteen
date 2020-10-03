package com.smart.smartcanteen.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.smartcanteen.DetailsActivity;
import com.smart.smartcanteen.R;
import com.smart.smartcanteen.model.popularFood;

import java.util.List;


public class popularFoodAdapter extends RecyclerView.Adapter<popularFoodAdapter.popularFoodViewHolder> {

    Context context;
    List<popularFood>popularFoodList;


    public popularFoodAdapter(Context context, List<popularFood> popularFoodList) {
        this.context = context;
        this.popularFoodList = popularFoodList;
    }

    @NonNull
    @Override
    public popularFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.popular_food_row_item, parent, false);
        return new popularFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull popularFoodViewHolder holder, int position) {

        holder.foodImage.setImageResource(popularFoodList.get(position).getImageUrl());
        holder.nama.setText(popularFoodList.get(position).getNama());
        holder.harga.setText(popularFoodList.get(position).getHarga());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailsActivity.class);
                context.startActivities(new Intent[]{i});
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularFoodList.size();
    }


    public static final class popularFoodViewHolder extends RecyclerView.ViewHolder{

        ImageView foodImage;
        TextView harga, nama;

        public popularFoodViewHolder(@NonNull View itemView){

            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            harga = itemView.findViewById(R.id.harga);
            nama = itemView.findViewById(R.id.nama);
        }
    }
}
