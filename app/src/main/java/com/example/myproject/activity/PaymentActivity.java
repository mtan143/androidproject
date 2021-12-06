package com.example.myproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myproject.R;
import com.example.myproject.adapter.OrderAdapter;
import com.example.myproject.model.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    ListView total_item;
    TextView total_price;
    Button btnConfirm;
    TextView btnCancel;
    ArrayList<Product> arrayList;
    int totalPrice = 0;
    OrderAdapter adapter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        total_item = findViewById(R.id.total_item);
        total_price = findViewById(R.id.total_price);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnCancel = findViewById(R.id.btnCancel);

        //btn cancel
        btnCancel.setOnClickListener(view -> {
            finish();
        });

        //btnConfirm
        btnConfirm.setOnClickListener(view -> {
            
        });

        //receive data from bag fragment
        Intent intent = getIntent();
        arrayList = (ArrayList<Product>) intent.getSerializableExtra("order");

        //setting adapter
        adapter = new OrderAdapter(this, R.layout.order_item, arrayList);
        total_item.setAdapter(adapter);

        //counting total price
        for (Product product : arrayList) {
            totalPrice += product.getPrice();
        }
        total_price.setText("AU$ " + new DecimalFormat("#,###").format(totalPrice));

    }
}