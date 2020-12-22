package com.campus.smartcanteen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.campus.smartcanteen.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupTabFragment extends Fragment {

    EditText edtNomorHP,edtNama,edtEmail,edtPassword;
    Button signup;
    float v=0;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);



            edtNomorHP = (EditText) root.findViewById(R.id.edtNomorHP);
            edtNama = (EditText) root.findViewById(R.id.edtNama);
            edtEmail = (EditText) root.findViewById(R.id.edtEmail);
            edtPassword = (EditText) root.findViewById(R.id.edtPassword);
            signup = (Button) root.findViewById(R.id.btnSignup);

            edtNomorHP.setTranslationX(800);
            edtNama.setTranslationX(800);
            edtEmail.setTranslationX(800);
            edtPassword.setTranslationX(800);
            signup.setTranslationX(800);

            edtNomorHP.setAlpha(v);
            edtNama.setAlpha(v);
            edtEmail.setAlpha(v);
            edtPassword.setAlpha(v);
            signup.setAlpha(v);

            edtNomorHP.animate().translationX(0).alpha(1).setDuration(1200).setStartDelay(300).start();
            edtNama.animate().translationX(0).alpha(1).setDuration(1200).setStartDelay(500).start();
            edtEmail.animate().translationX(0).alpha(1).setDuration(1200).setStartDelay(700).start();
            edtPassword.animate().translationX(0).alpha(1).setDuration(1200).setStartDelay(900).start();
            signup.animate().translationX(0).alpha(1).setDuration(1200).setStartDelay(1100).start();

        //Firebase Init
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        signup.setOnClickListener(new View.OnClickListener() {
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
                            Toast.makeText(getActivity(), "Nomor HP Telah Terdaftar!", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            User user = new User(edtNama.getText().toString(),edtPassword.getText().toString());
                            table_user.child(edtNomorHP.getText().toString()).setValue(user);
                            Toast.makeText(getActivity(), "Sign Up Berhasil", Toast.LENGTH_SHORT).show();
                            onStop();
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

