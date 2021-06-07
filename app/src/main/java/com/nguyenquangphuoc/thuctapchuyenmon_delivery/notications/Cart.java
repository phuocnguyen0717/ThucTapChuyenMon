package com.nguyenquangphuoc.thuctapchuyenmon_delivery.notications;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.R;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.adapter.CartAdapter;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.database.Database;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.model.Common;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.model.Order;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.model.Request;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class Cart extends AppCompatActivity {
    RecyclerView recyclerViewCart;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests;

    TextView txtTotalPrice;
    FButton btnPlace;

    List<Order> cart = new ArrayList<>();
    CartAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        //init
        recyclerViewCart = findViewById(R.id.recycler_cart);
        recyclerViewCart.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewCart.setLayoutManager(layoutManager);

        txtTotalPrice = findViewById(R.id.total);
        btnPlace = findViewById(R.id.btnPlaceOrder);

        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
        loadListFood();
    }
    private void showAlertDialog()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cart.this);
        alertDialog.setTitle("One more step");
        alertDialog.setMessage("Enter your address: ");

        final EditText edtAddress = new EditText(Cart.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        edtAddress.setLayoutParams(lp);
        alertDialog.setView(edtAddress); //add edit Text to alert dialog
        alertDialog.setIcon(R.drawable.ic_baseline_shopping_cart_24);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Create new Request
                Request request = new Request(
                        Common.currentUser.getPhone(),
                        Common.currentUser.getName(),
                        edtAddress.getText().toString(),
                        txtTotalPrice.getText().toString(),
                        cart

                );
                //Submit to Firebase
                requests.child(String.valueOf(System.currentTimeMillis())).setValue(request);
                new Database(getBaseContext()).cleanCarts();
                Toast.makeText(Cart.this,"Thanks, Order Place !!!!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
                alertDialog.show();
    }
    private void loadListFood() {
        cart = new Database(this).getCart();
        adapter = new CartAdapter(cart,this);
        recyclerViewCart.setAdapter(adapter);

        //calculate total price
        int total = 0 ;
        for(Order order:cart)
            total += (Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));
        Locale locale = new Locale("vn","VN");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        txtTotalPrice.setText(fmt.format(total)

        );
    }
}