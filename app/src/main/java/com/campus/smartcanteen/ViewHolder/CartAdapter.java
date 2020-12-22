package com.campus.smartcanteen.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.campus.smartcanteen.Model.Order;
import com.campus.smartcanteen.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private List<Order> list = new ArrayList<>();
    private Context context;

    //Constructor

    public CartAdapter(List<Order> list,Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_layout,parent,false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        TextDrawable drawable = TextDrawable.builder().
                buildRound("" + list.get(position).getJumlah(), Color.RED);
        holder.imgCartCount.setImageDrawable(drawable);

        Locale locale = new Locale("id","ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        int harga = (Integer.parseInt(list.get(position).getHarga()))*(Integer.parseInt(list.get(position).getJumlah()));
        holder.txtCartHarga.setText(format.format(harga));

        holder.txtCartNama.setText(list.get(position).getProductName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
