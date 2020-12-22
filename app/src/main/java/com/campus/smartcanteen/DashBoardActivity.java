package com.campus.smartcanteen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.campus.smartcanteen.DRVinterface.LoadMore;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DashBoardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private StaticRvAdapter staticRvAdapter;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    List<DynamicRVModel> items = new ArrayList();
    DynamicRVAdapter dynamicRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

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

        //end navigation drawer menu

        //static design untuk recycler view 1
        final ArrayList<StaticRvModel> item = new ArrayList<>();
        item.add(new StaticRvModel(R.drawable.nasigorengtoon, "Nasi Goreng"));
        item.add(new StaticRvModel(R.drawable.nasiayamtoon, "Ayam"));
        item.add(new StaticRvModel(R.drawable.sototoon, "Soto"));
        item.add(new StaticRvModel(R.drawable.masakanpadang, "Masakan Padang"));
        item.add(new StaticRvModel(R.drawable.mieayambakso, "Mie Ayam Bakso"));

        recyclerView = findViewById(R.id.rv_1);
        staticRvAdapter = new StaticRvAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRvAdapter);

        //dynamic design untuk recycler view 2
        items.add(new DynamicRVModel("Ayam"));
        items.add(new DynamicRVModel("Ayam"));
        items.add(new DynamicRVModel("Ayam"));
        items.add(new DynamicRVModel("Ayam"));
        items.add(new DynamicRVModel("Ayam"));
        items.add(new DynamicRVModel("Ayam"));
        items.add(new DynamicRVModel("Ayam"));
        items.add(new DynamicRVModel("Ayam"));
        items.add(new DynamicRVModel("Ayam"));
        items.add(new DynamicRVModel("Ayam"));
        items.add(new DynamicRVModel("Ayam"));
        items.add(new DynamicRVModel("Ayam"));
        items.add(new DynamicRVModel("Ayam"));
        items.add(new DynamicRVModel("Ayam"));
        items.add(new DynamicRVModel("Ayam"));
        items.add(new DynamicRVModel("Ayam"));
        items.add(new DynamicRVModel("Ayam"));
        items.add(new DynamicRVModel("Ayam"));
        items.add(new DynamicRVModel("Ayam"));
        items.add(new DynamicRVModel("Ayam"));

        RecyclerView drv = findViewById(R.id.rv_2);
        drv.setLayoutManager(new LinearLayoutManager(this));
        dynamicRVAdapter = new DynamicRVAdapter(drv, this,items);
        drv.setAdapter(dynamicRVAdapter);

        dynamicRVAdapter.setLoadMore(new LoadMore() {
            @Override
            public void onLoadMore() {
                if (items.size() <= 10) {
                    item.add(null);
                    dynamicRVAdapter.notifyItemInserted(items.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size()-1);
                            dynamicRVAdapter.notifyItemRemoved(items.size());

                            int index = items.size();
                            int end = index+10;
                            for (int i = index; i<end; i++){
                                String name = UUID.randomUUID().toString();
                                DynamicRVModel item = new DynamicRVModel(name);
                                items.add(item);
                            }
                            dynamicRVAdapter.notifyDataSetChanged();
                            dynamicRVAdapter.setLoded();
                        }
                    }, 4000);
                }
                else
                    Toast.makeText(DashBoardActivity.this, "Data Completed", Toast.LENGTH_SHORT).show();
            }
        });
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
                intent = new Intent(DashBoardActivity.this,KantinDepan.class);
                startActivity(intent);
                break;
            case R.id.nav_kabel:
                intent = new Intent(DashBoardActivity.this, KantinBelakang.class);
                startActivity(intent);
                break;
                //case logout belum ditambahkan
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}