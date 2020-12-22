package com.campus.smartcanteen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.campus.smartcanteen.Common.Common;
import com.campus.smartcanteen.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginTabFragment extends Fragment {

    EditText edtNomorHP, edtPassword;
    TextView lupapassword;
    Button login;
    float v=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        edtNomorHP = (EditText) root.findViewById(R.id.edtNomorHP);
        edtPassword = (EditText) root.findViewById(R.id.edtPassword);
        lupapassword = (TextView) root.findViewById(R.id.lupapassword);
        login = (Button) root.findViewById(R.id.btnLogin);

        edtNomorHP.setTranslationX(800);
        edtPassword.setTranslationX(800);
        lupapassword.setTranslationX(800);
        login.setTranslationX(800);

        edtNomorHP.setAlpha(v);
        edtPassword.setAlpha(v);
        lupapassword.setAlpha(v);
        login.setAlpha(v);

        edtNomorHP.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        edtPassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        lupapassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        //Firebase Init
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");
        
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(getActivity());
                mDialog.setMessage("Mohon Tunggu...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.child(edtNomorHP.getText().toString()).exists()){
                            mDialog.dismiss();
                            User user = snapshot.child(edtNomorHP.getText().toString()).getValue(User.class);
                            user.setNomorHP(edtNomorHP.getText().toString());
                            if (user.getPassword().equals(edtPassword.getText().toString())){
                                Toast.makeText(getActivity(), "Sign In Telah Berhasil!", Toast.LENGTH_SHORT).show();
                                Intent dashboard = new Intent(getActivity(),MainActivity.class);
                                Common.currentUser = user;
                                startActivity(dashboard);
                                onStop();
                            }
                            else{
                                Toast.makeText(getActivity(), "Password Yang Anda Masukan Salah!", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else {
                            mDialog.dismiss();
                            Toast.makeText(getActivity(), "User Tidak Ada Dalam Database!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        return root;
    }
}
