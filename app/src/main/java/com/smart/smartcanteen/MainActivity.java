package com.smart.smartcanteen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.smartcanteen.adapter.KantinDepanFoodAdapter;
import com.smart.smartcanteen.adapter.popularFoodAdapter;
import com.smart.smartcanteen.model.KantinDepanFood;
import com.smart.smartcanteen.model.popularFood;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView popularRecycler, kantinDepanRecycler;
    popularFoodAdapter PopularFoodAdapter;
    KantinDepanFoodAdapter kantindepanFoodAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Dummy Data For Out Model Class
        List<popularFood> popularFoodList = new ArrayList<>();

        popularFoodList.add(new popularFood("Ayam Geprek","Rp 15.000", R.drawable.ayamgeprek));
        popularFoodList.add(new popularFood("Ayam Goreng","Rp 12.000", R.drawable.ayamgoreng));
        popularFoodList.add(new popularFood("Rendang Goreng","Rp 10.000", R.drawable.rendang));
        popularFoodList.add(new popularFood("Ayam Geprek","Rp 15.000", R.drawable.ayamgeprek));
        popularFoodList.add(new popularFood("Ayam Goreng","Rp 12.000", R.drawable.ayamgoreng));
        popularFoodList.add(new popularFood("Rendang Goreng","Rp 10.000", R.drawable.rendang));

        setPopularRecycler(popularFoodList);

        List<KantinDepanFood> kantinDepanFoodList = new ArrayList<>();

        kantinDepanFoodList.add(new KantinDepanFood("Mie Goreng","Rp 18.000", R.drawable.miegoreng, "4.9", "Bu Marfuah"));
        kantinDepanFoodList.add(new KantinDepanFood("Mie Goreng","Rp 16.000", R.drawable.miegoreng, "4.5", "Pak Toni"));
        kantinDepanFoodList.add(new KantinDepanFood("Mie Goreng","Rp 12.000", R.drawable.miegoreng, "4.7", "Bu Donny"));
        kantinDepanFoodList.add(new KantinDepanFood("Mie Goreng","Rp 16.500", R.drawable.miegoreng, "4.2", "Pak Dunna"));
        kantinDepanFoodList.add(new KantinDepanFood("Mie Goreng","Rp 17.000", R.drawable.miegoreng, "4.4", "Bu Donno"));
        kantinDepanFoodList.add(new KantinDepanFood("Mie Goreng","Rp 20.000", R.drawable.miegoreng, "4.7", "Pak Juna"));
        setkantinDepanRecycler(kantinDepanFoodList);

    }

    private void setPopularRecycler(List<popularFood> popularFoodList){

        popularRecycler = findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        PopularFoodAdapter = new popularFoodAdapter(this, popularFoodList);
        popularRecycler.setAdapter(PopularFoodAdapter);

    }

    private void setkantinDepanRecycler(List<KantinDepanFood> kantinDepanFoodList){

        kantinDepanRecycler = findViewById(R.id.kantin_depan_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        kantinDepanRecycler.setLayoutManager(layoutManager);
        kantindepanFoodAdapter = new KantinDepanFoodAdapter(this, kantinDepanFoodList);
        kantinDepanRecycler.setAdapter(kantindepanFoodAdapter);

    }
}