package com.nguyenquangphuoc.thuctapchuyenmon_delivery.notications;

import android.app.ProgressDialog;
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
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.model.User;

public class SignUpActivity extends AppCompatActivity {
    EditText edtPhone,edtName,edtPassword,edtAddress;
    Button btnReg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sign_up);

        AnhXa();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_user = database.getReference("User");

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(SignUpActivity.this);
                mDialog.setMessage("Please Waiting...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // kiem tra user ton tai?
                        String Phone = edtPhone.getText().toString().trim();
                        String Password = edtPassword.getText().toString().trim();
                        String Name = edtName.getText().toString().trim();
                        String Address = edtAddress.getText().toString().trim();

                        if (Phone.isEmpty() || Password.isEmpty() || Name.isEmpty() || Address.isEmpty()) {
                            mDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "Vui lòng điền đầy đủ thông tin. ", Toast.LENGTH_SHORT).show();
                        }

                        else {
                            mDialog.dismiss();
                            User user = new User(edtName.getText().toString(), edtPassword.getText().toString(), edtAddress.getText().toString());
                            table_user.child(edtPhone.getText().toString()).setValue(user);
                            Toast.makeText(SignUpActivity.this,
                                    "Register Successfully",
                                    Toast.LENGTH_SHORT).show();
                            finish();
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
        edtAddress = findViewById(R.id.edtAddressRegister);
        edtName = findViewById(R.id.edtNameRegister);
        edtPassword = findViewById(R.id.edtPassRegister);
        edtPhone = findViewById(R.id.edtPhoneRegister);
        btnReg = findViewById(R.id.btnReg);
    }
}

