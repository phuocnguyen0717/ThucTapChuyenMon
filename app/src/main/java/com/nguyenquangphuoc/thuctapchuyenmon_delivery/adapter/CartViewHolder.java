package com.nguyenquangphuoc.thuctapchuyenmon_delivery.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenquangphuoc.thuctapchuyenmon_delivery.R;
import com.nguyenquangphuoc.thuctapchuyenmon_delivery.inteface.ItemClickListener;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txt_cart_name,txt_cart_price;
    public ImageView imv_cart_count;

    private ItemClickListener itemClickListener;

    public void setTxt_cart_name(TextView txt_cart_name) {
        this.txt_cart_name = txt_cart_name;
    }

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_cart_name = itemView.findViewById(R.id.txt_Cart_Item_Name);
        txt_cart_price = itemView.findViewById(R.id.txt_Cart_Item_Price);
        imv_cart_count = itemView.findViewById(R.id.imv_cart_item_Count);
    }

    @Override
    public void onClick(View v) {

    }

}
