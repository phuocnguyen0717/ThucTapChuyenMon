package com.nguyenquangphuoc.thuctapchuyenmon_delivery.notications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.R;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.adapter.OrderViewHolder;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.model.Common;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.model.Request;

public class OrderStatus extends AppCompatActivity {
    RecyclerView recyclerViewOrder;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Request, OrderViewHolder> adapter;

    FirebaseDatabase database;
    DatabaseReference requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        //Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        recyclerViewOrder = findViewById(R.id.listOrders);
        recyclerViewOrder.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewOrder.setLayoutManager(layoutManager);

        loadOrders(Common.currentUser.getPhone());

    }

    private void loadOrders(String phone) {
        adapter = new FirebaseRecyclerAdapter<Request, OrderViewHolder>(
                Request.class,
                R.layout.layout_order,
                OrderViewHolder.class,
                requests.orderByChild("phone").equalTo(phone)
        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder orderViewHolder, Request request, int i) {
                orderViewHolder.txtOrderId.setText(adapter.getRef(i).getKey());
                orderViewHolder.txtOrderStatus.setText(convertCodeToStatus(request.getStatus()));
                orderViewHolder.txtOrderAddress.setText(request.getAddress());
                orderViewHolder.txtOrderPhone.setText(request.getPhone());
            }
        };
        recyclerViewOrder.setAdapter(adapter);
    }
    private String convertCodeToStatus(String status)
    {
        if(status.equals("0"))
            return "Placed";
        else if(status.equals("1"))
            return "On my way";
        else
            return "Shipped";
    }
}