package com.smart.smartcanteen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.smartcanteen.R;
import com.smart.smartcanteen.model.KantinDepanFood;


import java.util.List;


public class KantinDepanFoodAdapter extends RecyclerView.Adapter<KantinDepanFoodAdapter.KantinDepanFoodViewHolder> {

    Context context;
    List<KantinDepanFood>kantinDepanFoodList;


    public KantinDepanFoodAdapter(Context context, List<KantinDepanFood> kantinDepaFoodList) {
        this.context = context;
        this.kantinDepanFoodList = kantinDepaFoodList;
    }

    @NonNull
    @Override
    public KantinDepanFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.kantin_depan_food_row_item, parent, false);
        return new KantinDepanFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KantinDepanFoodViewHolder holder, int position) {

        holder.foodImage.setImageResource(kantinDepanFoodList.get(position).getImageUrl());
        holder.nama.setText(kantinDepanFoodList.get(position).getNama());
        holder.harga.setText(kantinDepanFoodList.get(position).getHarga());
        holder.rating.setText(kantinDepanFoodList.get(position).getRating());
        holder.namakantin.setText(kantinDepanFoodList.get(position).getNamakantin());
    }

    @Override
    public int getItemCount() {
        return kantinDepanFoodList.size();
    }


    public static final class KantinDepanFoodViewHolder extends RecyclerView.ViewHolder{

        ImageView foodImage;
        TextView harga, nama, rating, namakantin;

        public KantinDepanFoodViewHolder(@NonNull View itemView){

            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            harga = itemView.findViewById(R.id.harga);
            nama = itemView.findViewById(R.id.nama);
            rating = itemView.findViewById(R.id.rating);
            namakantin = itemView.findViewById(R.id.nama_kantin);
        }
    }
}
