package com.example.myproject.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproject.R;
import com.example.myproject.model.Product;
import com.example.myproject.product.ProductDetailActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class SearchAdapter extends FirebaseRecyclerAdapter<Product, SearchAdapter.SearchViewHolder> {

    public SearchAdapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SearchViewHolder holder, int position, @NonNull Product model) {

        holder.name.setText(model.getName());
        holder.price.setText(String.valueOf(model.getPrice()));

        try {
            Glide.with(holder.thumbnail.getContext())
                    .load(model.getImgLink())
                    .into(holder.thumbnail);
        }
        catch (Exception exception) {
            System.out.println(exception);
        }

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ProductDetailActivity.class);
            intent.putExtra("product", model);
            view.getContext().startActivity(intent);
        });
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new SearchViewHolder(view);
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView name, price;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.order_image);
            name = itemView.findViewById(R.id.order_name);
            price = itemView.findViewById(R.id.order_price);
        }
    }
}
