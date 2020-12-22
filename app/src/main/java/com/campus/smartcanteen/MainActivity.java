package com.campus.smartcanteen;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.campus.smartcanteen.Common.Common;
import com.campus.smartcanteen.MenuRecycler.MenuTodayAdapter;
import com.campus.smartcanteen.MenuRecycler.MenuTodayClass;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView menuTodayrecycler;
    MenuTodayAdapter menuTodayAdapter;
    RelativeLayout relatifDepan,relatifBelakang;
    TextView Nama,txtFullName;
    ImageView Gambar;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //konfig navbar untuk menampilkan drawer layout
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //navigation drawer menu

        //hide menu navigasi
        //Menu menu = navigationView.getMenu();
        //menu.findItem(R.id.nav_logout).setVisible(false);
        //end menu hide

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //set item home selected
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        //Set Name For User
        View headerView = navigationView.getHeaderView(0);
        txtFullName = headerView.findViewById(R.id.txtFullName);
        txtFullName.setText(Common.currentUser.getNama().toString());

        //Home Kantin Depan Dan Belakang
        relatifDepan = findViewById(R.id.relatif_depan);
        relatifBelakang = findViewById(R.id.relatif_belakang);
        relatifDepan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent relatifDepan = new Intent(MainActivity.this,KantinDepan.class);
                startActivity(relatifDepan);
            }
        });

        relatifBelakang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent relatifBelakang = new Intent(MainActivity.this,KantinBelakang.class);
                startActivity(relatifBelakang);
            }
        });

        //end navigation drawer menu

        List<MenuTodayClass> MenuTodayFoodList = new ArrayList<>();
        MenuTodayFoodList.add(new MenuTodayClass("Mie Ayam Bakso",R.drawable.mieayambakso));
        MenuTodayFoodList.add(new MenuTodayClass("Nasi Goreng",R.drawable.nasigoreng));
        MenuTodayFoodList.add(new MenuTodayClass("Soto Ayam",R.drawable.soto));

        menuTodayrecycler = findViewById(R.id.menu_today_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        menuTodayrecycler.setLayoutManager(layoutManager);
        menuTodayAdapter = new MenuTodayAdapter(this,MenuTodayFoodList);
        menuTodayrecycler.setAdapter(menuTodayAdapter);

    }



    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    //penyeleksian kondisi ketika item navbar diklik
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Intent intent;

        switch (menuItem.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_kadep:
                intent = new Intent(MainActivity.this, KantinDepan.class);
                startActivity(intent);
                break;
            case R.id.nav_kabel:
                intent = new Intent(MainActivity.this, KantinBelakang.class);
                startActivity(intent);
                break;
            case R.id.nav_order_status :
                intent = new Intent(MainActivity.this, OrderStatus.class);
                startActivity(intent);
                break;
            case R.id.nav_logout :
                intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}