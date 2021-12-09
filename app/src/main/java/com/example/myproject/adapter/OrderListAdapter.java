package com.example.myproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myproject.R;
import com.example.myproject.model.Order;
import com.example.myproject.model.Product;
import com.example.myproject.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class OrderListAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<Order> arrayList;

    public OrderListAdapter(Context context, int layout, ArrayList<Order> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        OrderListAdapter.ViewHolder viewHolder;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout , null);
            viewHolder = new OrderListAdapter.ViewHolder();

            viewHolder.id = view.findViewById(R.id.order_id_value);
            viewHolder.totalProduct = view.findViewById(R.id.total_product_value);
            viewHolder.totalPrice = view.findViewById(R.id.total_price_value);
            viewHolder.status = view.findViewById(R.id.status);

            view.setTag(viewHolder);
        } else {
            viewHolder = (OrderListAdapter.ViewHolder) view.getTag();
        }
        viewHolder.id.setText(arrayList.get(i).getId());
        viewHolder.totalProduct.setText(String.valueOf(arrayList.get(i).getProducts().size()));
        viewHolder.totalPrice.setText("AU$ " + new DecimalFormat("#,###").format(arrayList.get(i).getTotalPrice()));
        viewHolder.status.setText(arrayList.get(i).getStatus());
        return view;
    }

    private class ViewHolder{

        TextView id;
        TextView totalProduct;
        TextView totalPrice;
        TextView status;

    }

}
