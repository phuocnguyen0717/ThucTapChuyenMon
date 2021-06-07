package com.nguyenquangphuoc.thuctapchuyenmon_delivery.notications;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.R;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.model.Common;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.model.User;

public class SignInActivity extends AppCompatActivity {
    EditText edtPhone,edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sign_in);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");
        AnhXa();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String password = edtPassword.getText().toString().trim();
                ProgressDialog mDialog = new ProgressDialog(SignInActivity.this);
                mDialog.setMessage("Please Wating...");
                mDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //kiem tra user ton tai trong csdl?
                        mDialog.dismiss();
                        if(snapshot.child(edtPhone.getText().toString()).exists()) {
                            User user = snapshot.child(edtPhone.getText().toString()).getValue(User.class);

                                if (user.getPassword() != null && user.getPassword().equals(password)){
                                {
                                    Intent homeItent = new Intent(SignInActivity.this,Home.class);
                                    Common.currentUser = user ;
                                    startActivity(homeItent);
                                    finish();
                                }
                            } else {
                                Toast.makeText(SignInActivity.this,
                                        "Sign in failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SignInActivity.this,
                                    "User not exist",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
    private void AnhXa() {
        edtPhone = findViewById(R.id.edtPhoneLog);
        edtPassword = findViewById(R.id.edtPassLog);
        btnLogin = findViewById(R.id.btnLogin);
    }
}
