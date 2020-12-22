package com.campus.smartcanteen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.campus.smartcanteen.Common.Common;
import com.campus.smartcanteen.Database.Database;
import com.campus.smartcanteen.Model.Order;
import com.campus.smartcanteen.Model.Request;
import com.campus.smartcanteen.ViewHolder.CartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    //Firebase Database
    FirebaseDatabase database;
    DatabaseReference requests;

    TextView txtTotalHarga;
    Button btn_place_order;

    List<Order> cart = new ArrayList<>();

    CartAdapter cartAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        //Init
        recyclerView = findViewById(R.id.list_cart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalHarga = findViewById(R.id.total_harga);
        btn_place_order = findViewById(R.id.btn_place_order);

        btn_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        loadListFood();
    }

    //showAlertDialog
    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cart.this);
        alertDialog.setTitle("Satu Langkah Lagi!");
        alertDialog.setMessage("Bungkus atau Makan Disini?");

        final EditText editAlamat = new EditText(Cart.this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        editAlamat.setLayoutParams(layoutParams);
        alertDialog.setView(editAlamat);
        alertDialog.setIcon(R.drawable.ic_baseline_shopping_cart_24);


        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Request request = new Request(
                        Common.currentUser.getNomorHP(),
                        Common.currentUser.getNama(),
                        editAlamat.getText().toString(),
                        txtTotalHarga.getText().toString(),
                        cart
                );
                //Submit to Firebase
                //Kita akan menggunakan System.CurrentMillis untuk Key
                requests.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(request);

                //Menghapus Cart
                new Database(getBaseContext()).cleanCart();
                Toast.makeText(Cart.this, "Terimakasih, Silahkan Memesan!", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(Cart.this,MainActivity.class);
                startActivity(home);
                finish();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();

    }

    private void loadListFood(){
        cart = new Database(this).getCarts();
        cartAdapter = new CartAdapter(cart,this);
        recyclerView.setAdapter(cartAdapter);

        //Menghitung Jumlah Total Harga
        int total = 0;
        for (Order order:cart) {
            total += (Integer.parseInt(order.getHarga()))*(Integer.parseInt(order.getJumlah()));
        }
        Locale locale = new Locale("id","ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        txtTotalHarga.setText(format.format(total));
    }
}
