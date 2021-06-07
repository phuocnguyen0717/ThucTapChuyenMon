package com.nguyenquangphuoc.thuctapchuyenmon_delivery.notications;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nguyenquangphuoc.thuctapchuyenmon_delivery.R;

public class MainActivity extends AppCompatActivity {
    Button btnSignIn,btnSignUp;
    TextView txtSlogan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIn = new Intent(MainActivity.this,SignInActivity.class);
                startActivity(signIn);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(signUp);
            }
        });
    }

    private void AnhXa() {
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);
        txtSlogan = findViewById(R.id.txtSlogan);
    }
}