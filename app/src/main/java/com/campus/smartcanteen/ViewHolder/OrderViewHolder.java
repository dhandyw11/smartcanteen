package com.campus.smartcanteen.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.campus.smartcanteen.Interface.ItemClickListener;
import com.campus.smartcanteen.R;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtOrderId, txtOrderStatus, txtOrderNomorHP, txtOrderAlamat;
    public Button btnScan;
    public ItemClickListener itemClickListener;


    public OrderViewHolder (View itemView) {
        super(itemView);

        txtOrderId = itemView.findViewById(R.id.order_id);
        txtOrderStatus = itemView.findViewById(R.id.order_status);
        txtOrderAlamat = itemView.findViewById(R.id.order_alamat);
        txtOrderNomorHP = itemView.findViewById(R.id.order_nomor_hp);

        btnScan = itemView.findViewById(R.id.order_scan);



    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
