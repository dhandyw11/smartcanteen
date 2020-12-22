package com.campus.smartcanteen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.campus.smartcanteen.Common.Common;
import com.campus.smartcanteen.Model.Request;
import com.campus.smartcanteen.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;

public class OrderStatus extends AppCompatActivity {
    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    public TextView txtResult;
    public Result result;


    //Firebase Database
    FirebaseDatabase database;
    DatabaseReference requests;

    FirebaseRecyclerAdapter<Request, OrderViewHolder> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        //Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        recyclerView = findViewById(R.id.listOrder);
//        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrder(Common.currentUser.getNomorHP());


    }

    private void loadOrder(String nomorHP) {
        adapter = new FirebaseRecyclerAdapter<Request, OrderViewHolder>(
                Request.class,
                R.layout.order_layout,
                OrderViewHolder.class,
                requests.orderByChild("nomorHP").equalTo(nomorHP)
        ) {
            @Override
            protected void populateViewHolder(final OrderViewHolder viewHolder, final Request model, final int position) {

                viewHolder.txtOrderId.setText(adapter.getRef(position).getKey());
                viewHolder.txtOrderStatus.setText(Common.convertCodeToStatus(model.getStatus()));
                viewHolder.txtOrderAlamat.setText(model.getAlamat());
                viewHolder.txtOrderNomorHP.setText(model.getNomorHP());
                viewHolder.btnScan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requests.removeValue();
                        Intent scan = new Intent(OrderStatus.this,ScannedBarcode.class);
                        startActivity(scan);

                    }
                });
            }
        };

        Toast.makeText(this, "Value: ", Toast.LENGTH_SHORT).show();
        recyclerView.setAdapter(adapter);
    }

    public String convertCodeToStatus(String status) {

        if (status.equals("0")) {
            return "Belum Bayar";
        }

        else if (status.equals("1")) {
            return "Sudah Bayar";
        }

        else {
            return "Error : QRCode doesn't Exist";
        }
    }
}
