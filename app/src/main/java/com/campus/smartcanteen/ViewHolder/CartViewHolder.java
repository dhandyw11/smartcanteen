package com.campus.smartcanteen.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.campus.smartcanteen.Interface.ItemClickListener;
import com.campus.smartcanteen.Model.Order;
import com.campus.smartcanteen.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtCartNama, txtCartHarga;
    public ImageView imgCartCount;

    private ItemClickListener itemClickListener;

    public void setTxtCartNama(TextView txtCartNama) {
        this.txtCartNama = txtCartNama;
    }

    public CartViewHolder (View itemView) {
        super(itemView);
        txtCartNama = itemView.findViewById(R.id.cart_item_nama);
        txtCartHarga = itemView.findViewById(R.id.cart_item_harga);
        imgCartCount = itemView.findViewById(R.id.cart_item_count);

    }

    @Override
    public void onClick(View v) {

    }
}

