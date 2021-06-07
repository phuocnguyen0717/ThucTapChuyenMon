package com.nguyenquangphuoc.thuctapchuyenmon_delivery.notications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.R;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.adapter.FoodViewHolder;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.model.Category;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.model.Food;
import com.squareup.picasso.Picasso;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.inteface.ItemClickListener;

public class FoodList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodlist;

    String categoryId ="";
    public FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);


        //firebase
        database = FirebaseDatabase.getInstance();
        foodlist =database.getReference("Food");

        recyclerView = findViewById(R.id.recyclerView_Food);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //get Intent here

        if(getIntent() !=null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if(!categoryId.isEmpty() && categoryId != null){
            loadListFood(categoryId);
        }
    }

    private void loadListFood(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,
                R.layout.food_item,
                FoodViewHolder.class,
                foodlist.orderByChild("MenuId").equalTo(categoryId) //like : Select * from Food where MenuId =
                ) {
            @Override
            protected void populateViewHolder(FoodViewHolder foodViewHolder, Food food, int i) {
                foodViewHolder.txtFoodName.setText(food.getNameFood());
                Picasso.with(getBaseContext()).load(food.getImage()).into(foodViewHolder.imageViewfood);

                final Food local = food;
                foodViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent foodDetail = new Intent(FoodList.this,FoodDetail.class);
                        foodDetail.putExtra("FoodId",adapter.getRef(position).getKey());
                        startActivity(foodDetail);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}