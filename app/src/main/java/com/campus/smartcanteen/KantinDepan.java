package com.campus.smartcanteen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.campus.smartcanteen.Interface.ItemClickListener;
import com.campus.smartcanteen.Model.Kategori;
import com.campus.smartcanteen.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class KantinDepan extends AppCompatActivity {

    private static String TAG="FoodList";

    FirebaseRecyclerAdapter<Kategori, MenuViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference kategori;

    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kantin_depan);

        //Init Database
        database = FirebaseDatabase.getInstance();
        kategori = database.getReference("Kategori");

        //Floating Button Cart
        FloatingActionButton floatingActionButton = findViewById(R.id.float_cart);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(KantinDepan.this,Cart.class);
                startActivity(cartIntent);
            }
        });

        recycler_menu = findViewById(R.id.kantin_depan_recycler);
//        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);

        loadMenu();
    }

    private void loadMenu() {
        adapter = new FirebaseRecyclerAdapter<Kategori, MenuViewHolder>
                (Kategori.class, R.layout.menu_item, MenuViewHolder.class, kategori) {

            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Kategori model, int position) {
                viewHolder.menu_makanan.setText(model.getNama());
                Picasso.get().load(model.getGambar()).into(viewHolder.menu_gambar_makanan);
                final Kategori clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Get KategoriId and Set To FoodList Activity
                        Intent foodList = new Intent(KantinDepan.this,FoodList.class);
                        //Because KategoriId is key, so we just get Key of this Item
                        foodList.putExtra("KategoriId", adapter.getRef(position).getKey());
//                        Log.d(TAG, "KantinDepan Value of Key :"+adapter.getRef(position).getKey());
//                        Toast.makeText(KantinDepan.this, "Value of key :"+adapter.getRef(position).getKey(), Toast.LENGTH_SHORT).show();
                        startActivity(foodList);
                    }
                });

            }
        };
        recycler_menu.setAdapter(adapter);
    }
}