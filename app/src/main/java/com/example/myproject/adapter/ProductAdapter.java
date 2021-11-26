package com.example.myproject.adapter;

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

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    Context context;
    int layout ;
    List<Product> arraylist ;

    public ProductAdapter(Context context, int layout, List<Product> arraylist) {
        this.context = context;
        this.layout = layout;
        this.arraylist = arraylist;
    }

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{

        ImageView thumbnail;
        TextView name;
        TextView price;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout , null);
            viewHolder = new ViewHolder();

            viewHolder.thumbnail = view.findViewById(R.id.thumbnail);
            viewHolder.name = view.findViewById(R.id.name);
            viewHolder.price = view.findViewById(R.id.price);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        try {
            Glide.with(context)
                    .load(arraylist.get(i).getImgLink())
                    .into(viewHolder.thumbnail);
        }
        catch (Exception exception) {
            System.out.println(exception);
        }

        viewHolder.name.setText(arraylist.get(i).getName());
        viewHolder.price.setText(arraylist.get(i).getPrice());

        return view;
    }
}
