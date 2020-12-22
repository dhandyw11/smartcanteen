package com.campus.smartcanteen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.campus.smartcanteen.Model.Request;
import com.campus.smartcanteen.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;

public class ScannedBarcode extends AppCompatActivity {
    RecyclerView recyclerView;
    private CodeScanner mCodeScanner;
    private TextView txt_result;
    public FirebaseRecyclerAdapter<Request, OrderViewHolder> adapter;
    public TextView OrderId;

    //Firebase
    FirebaseDatabase database;
    DatabaseReference requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_layout);

        //Init Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");



        final CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                ScannedBarcode.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ScannedBarcode.this,result.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
//        checkCameraPermission();
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ScannedBarcode.this, "Terimakasih Sudah Melakukan Pembayaran!", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(ScannedBarcode.this,MainActivity.class);
                startActivity(home);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

//    private void checkCameraPermission(){
//        Dexter.withActivity(this)
//                .withPermission(Manifest.permission.CAMERA)
//                .withListener(new PermissionListener() {
//                    @Override
//                    public void onPermissionGranted(PermissionGrantedResponse response) {
//                        mCodeScanner.startPreview();
//                    }
//
//                    @Override
//                    public void onPermissionDenied(PermissionDeniedResponse response) {
//
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission,
//                                                                   PermissionToken token) {
//                        token.continuePermissionRequest();
//                    }
//                })
//                .check();
//    }
}

