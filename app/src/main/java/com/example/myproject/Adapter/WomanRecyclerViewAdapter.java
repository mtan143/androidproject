package com.example.myproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;

public class WomanRecyclerViewAdapter extends RecyclerView.Adapter<WomanRecyclerViewAdapter.MyViewHolder> {

    String[] data;
    Context context;

    public WomanRecyclerViewAdapter(Context ct, String[] s){
        context = ct;
        data = s;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.category_item, parent , false);
        return new WomanRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mytextView.setText(data[position]);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mytextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mytextView = itemView.findViewById(R.id.TitleShop);
        }
    }
}