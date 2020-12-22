package com.campus.smartcanteen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.campus.smartcanteen.Interface.ItemClickListener;
import com.campus.smartcanteen.Model.Foods;
import com.campus.smartcanteen.ViewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FoodList extends AppCompatActivity {
    private static final String TAG = "FoodList";

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodList;

    String kategoriId = "";

    //FirebaseRecyclerAdapter
    FirebaseRecyclerAdapter<Foods, FoodViewHolder>adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        ///Firebase
        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Foods");

        recyclerView = findViewById(R.id.recycler_food_list);
//        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Get Intent Dari Tiap Kantin untuk mendapatkan kategoriId
        if (getIntent() !=null) {
            kategoriId = getIntent().getStringExtra("KategoriId");
        }
        if (!kategoriId.isEmpty() && kategoriId !=null){
            loadListFood(kategoriId);
        }
    }

    //loadListFood() implementasi
    private void loadListFood(String kategoriId) {
        adapter = new FirebaseRecyclerAdapter<Foods, FoodViewHolder>(Foods.class,
                R.layout.food_item,
                FoodViewHolder.class,
                foodList.orderByChild("MenuId").equalTo(kategoriId) //Like Select From food where MenuId =
        ){
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, final Foods model, int position) {
                viewHolder.nama_makanan.setText(model.getNama());
                Picasso.get().load(model.getGambar()).into(viewHolder.gambar_makanan);

                final Foods local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Start new Activity
                        Intent foodDetail = new Intent(FoodList.this, FoodDetails.class);
                        //Simpan Food Id di Activity
                        foodDetail.putExtra("FoodId", adapter.getRef(position).getKey());
                        startActivity(foodDetail);
                    }
                });
            }
        };
        //Set Adapter
//        Toast.makeText(this, "loadedListFood: "+adapter.getItemCount(), Toast.LENGTH_SHORT).show();
        recyclerView.setAdapter(adapter);
    }
}
