package com.campus.smartcanteen.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.campus.smartcanteen.Interface.ItemClickListener;
import com.campus.smartcanteen.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView menu_makanan;
    public ImageView menu_gambar_makanan;

    private ItemClickListener itemClickListener;

    public MenuViewHolder (View itemView) {
        super(itemView);

        menu_makanan = itemView.findViewById(R.id.menu_makanan);
        menu_gambar_makanan = itemView.findViewById(R.id.menu_gambar_makanan);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
