package com.campus.smartcanteen.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.campus.smartcanteen.Interface.ItemClickListener;
import com.campus.smartcanteen.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView nama_makanan;
    public ImageView gambar_makanan;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public FoodViewHolder(View itemView) {
        super(itemView);
        nama_makanan = itemView.findViewById(R.id.nama_makanan_list);
        gambar_makanan = itemView.findViewById(R.id.gambar_makanan_list);

        itemView.setOnClickListener(this);
    }

    public void onClick (View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

}
