package com.example.myproject.product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myproject.R;
import com.example.myproject.fragment.FavFragment;
import com.example.myproject.model.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class ProductDetailActivity extends AppCompatActivity {

    TextView btnBack;
    ImageView image;
    TextView nameDetail;
    TextView priceDetail;
    TextView description;
    Product product;
    ImageView favBtn;
    Button btnAddToCart;
    FirebaseFirestore firestore;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        btnBack = findViewById(R.id.back);
        image = findViewById(R.id.picture);
        nameDetail = findViewById(R.id.nameDetail);
        priceDetail = findViewById(R.id.priceDetail);
        description = findViewById(R.id.description);
        favBtn = findViewById(R.id.heartLove);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        firestore = FirebaseFirestore.getInstance();

        //receive from grid view
        Intent intent = getIntent();
        product = (Product) intent.getSerializableExtra("product");

        nameDetail.setText(product.getName());
        priceDetail.setText("AU$ " + new DecimalFormat("#,###").format(product.getPrice()));
        description.setText(product.getDescription());

        Glide.with(getApplicationContext())
                .load(product.getImgLink())
                .into(image);

        if (product.isLike())
            favBtn.setImageResource(R.drawable.fav);
        else
            favBtn.setImageResource(R.drawable.not_fav);

        btnBack.setOnClickListener(view -> finish());


        //set up heart icon
        favBtn.setOnClickListener(view -> {

            if (!product.isLike()) {

                product.setLike(true);

                favBtn.setImageResource(R.drawable.fav);

                firestore.collection("products")
                        .document(product.getId())
                        .update("like", product.isLike())
                        .addOnSuccessListener(aVoid ->
                                Toast.makeText(ProductDetailActivity.this,
                                        "Added to wishlist!", Toast.LENGTH_LONG).show())
                        .addOnFailureListener(e ->
                                Toast.makeText(ProductDetailActivity.this,
                                        "Failed! Try again", Toast.LENGTH_LONG).show());

            } else {

                favBtn.setImageResource(R.drawable.not_fav);

                product.setLike(false);

                firestore.collection("products")
                        .document(product.getId())
                        .update("like", product.isLike())
                        .addOnSuccessListener(
                                aVoid -> Toast.makeText(getApplicationContext(),
                                        "Removed!", Toast.LENGTH_LONG).show())
                        .addOnFailureListener(e ->
                                Toast.makeText(getApplicationContext(),
                                        "Failed! Try again", Toast.LENGTH_LONG).show());
            }
        });

        //set up add to cart button
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (product.isCart()) {

                    Toast.makeText(getApplicationContext(), "You've already added it to cart!", Toast.LENGTH_LONG).show();

                } else {
                    product.setCart(true);
                    firestore.collection("products")
                            .document(product.getId())
                            .update("cart", true)
                            .addOnSuccessListener(
                                    aVoid -> Toast.makeText(getApplicationContext(),
                                            "Check your cart!", Toast.LENGTH_LONG).show())
                            .addOnFailureListener(e ->
                                    Toast.makeText(getApplicationContext(),
                                            "Failed! Try again", Toast.LENGTH_LONG).show());
                }
            }
        });
    }
}