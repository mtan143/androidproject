package com.example.myproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myproject.R;
import com.example.myproject.model.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter {


    Context context;
    int layout;
    ArrayList<Product> arrayList;

    public OrderAdapter(Context context, int layout, ArrayList<Product> arrayList) {
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

        OrderAdapter.ViewHolder viewHolder;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout , null);
            viewHolder = new OrderAdapter.ViewHolder();

            viewHolder.thumbnail = view.findViewById(R.id.order_image);
            viewHolder.name = view.findViewById(R.id.order_name);
            viewHolder.price = view.findViewById(R.id.order_price);

            view.setTag(viewHolder);
        } else {
            viewHolder = (OrderAdapter.ViewHolder) view.getTag();
        }

        try {
            Glide.with(context)
                    .load(arrayList.get(i).getImgLink())
                    .into(viewHolder.thumbnail);
        }
        catch (Exception exception) {
            System.out.println(exception);
        }

        viewHolder.name.setText(arrayList.get(i).getName());
        viewHolder.price.setText("AU$ " + new DecimalFormat("#,###").format(arrayList.get(i).getPrice()));

        return view;
    }

    private class ViewHolder{

        ImageView thumbnail;
        TextView name;
        TextView price;

    }
}
