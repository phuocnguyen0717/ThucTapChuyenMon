package com.nguyenquangphuoc.thuctapchuyenmon_delivery.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenquangphuoc.thuctapchuyenmon_delivery.R;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.inteface.ItemClickListener;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtOrderId , txtOrderStatus , txtOrderPhone, txtOrderAddress;
    private ItemClickListener itemClickListener;

    public OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        txtOrderAddress = itemView.findViewById(R.id.txt_orderAddress);
        txtOrderId = itemView.findViewById(R.id.txt_orderId);
        txtOrderPhone = itemView.findViewById(R.id.txt_orderPhone);
        txtOrderStatus = itemView.findViewById(R.id.txt_orderStatus);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onClick(v , getAdapterPosition(), false);
    }
}
