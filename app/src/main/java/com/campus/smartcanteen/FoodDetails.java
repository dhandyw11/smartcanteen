package com.campus.smartcanteen;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.campus.smartcanteen.Database.Database;
import com.campus.smartcanteen.Model.Foods;
import com.campus.smartcanteen.Model.Order;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodDetails extends AppCompatActivity {
    TextView nama_makanan,harga_makanan,deskripsi;
    ImageView gambar_makanan;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Button btnCart;
    ElegantNumberButton numberButton;

    String foodId = "";

    //Firebase
    FirebaseDatabase database;
    DatabaseReference foods;

    Foods currentFood;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        //Firebase
        database = FirebaseDatabase.getInstance();
        foods = database.getReference("Foods");

        //Init View
        numberButton = (ElegantNumberButton) findViewById(R.id.numberBtn);
        btnCart = (Button) findViewById(R.id.btnCart);


        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        foodId,
                        currentFood.getNama(),
                        numberButton.getNumber(),
                        currentFood.getHarga()
                ));
                Toast.makeText(FoodDetails.this, "Dimasukan dalam Kerajang", Toast.LENGTH_SHORT).show();
            }
        });

        deskripsi = findViewById(R.id.deskripsi_makanan);
        nama_makanan = findViewById(R.id.nama_makanan_detail);
        harga_makanan = findViewById(R.id.harga_makanan);
        gambar_makanan = findViewById(R.id.gambar_makanan_detail);

        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapseAppBar);

        //Get FoodId from Intent
        if (getIntent() !=null){
            foodId = getIntent().getStringExtra("FoodId");
        }
        if (!foodId.isEmpty()){
            getDetailsFood(foodId);
        }
        else {
            Toast.makeText(this, "Error : foodId = "+foodId, Toast.LENGTH_SHORT).show();
        }
    }

    //getDetailsFood
    private void getDetailsFood(String foodId){
        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentFood = snapshot.getValue(Foods.class);

                Picasso.get().load(currentFood.getGambar()).into(gambar_makanan);

                collapsingToolbarLayout.setTitle(currentFood.getNama());

                harga_makanan.setText(currentFood.getHarga());
                nama_makanan.setText(currentFood.getNama());
                deskripsi.setText(currentFood.getDeskripsi());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
