package com.nguyenquangphuoc.thuctapchuyenmon_delivery.notications;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.R;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.database.Database;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.model.Food;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.model.Order;
import com.squareup.picasso.Picasso;

public class FoodDetail extends AppCompatActivity {
    TextView food_name_detail,food_price_detail,food_description_detail;
    ImageView food_image_detail;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String foodId = "";

    FirebaseDatabase database;
    DatabaseReference food;

    Food currentFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        //Firebase
        database = FirebaseDatabase.getInstance();
        food = database.getReference("Food");
        AnhXa();

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Database(FoodDetail.this).addToCart(new Order(
                        foodId,
                        currentFood.getNameFood(),
                        numberButton.getNumber(),
                        currentFood.getPrice(),
                        currentFood.getDisCount()
                ));
                Toast.makeText(FoodDetail.this,
                        "Added To Cart" ,
                        Toast.LENGTH_SHORT).show();
            }
        });


        //Get foodId from Intent
        if (getIntent() != null)
            foodId = getIntent().getStringExtra("FoodId");
        if(!foodId.isEmpty()){
            getDetailFood(foodId);
        }
    }
    private void AnhXa() {
        numberButton = findViewById(R.id.number_button);
        btnCart = findViewById(R.id.btnCart);
        food_description_detail = findViewById(R.id.food_description);
        food_name_detail = findViewById(R.id.food_name_detail);
        food_price_detail = findViewById(R.id.food_price_detail);
        food_image_detail = findViewById(R.id.image_foodDetail);
        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
    }

    private void getDetailFood(String foodId) {
        food.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                currentFood = snapshot.getValue(Food.class);

                //set Image
                Picasso.with(getBaseContext()).load(currentFood.getImage()).into(food_image_detail);

                collapsingToolbarLayout.setTitle(currentFood.getNameFood());
                food_price_detail.setText(currentFood.getPrice());
                food_name_detail.setText(currentFood.getNameFood());
                food_description_detail.setText(currentFood.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}