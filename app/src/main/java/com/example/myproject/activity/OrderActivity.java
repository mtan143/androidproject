package com.example.myproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myproject.R;
import com.example.myproject.adapter.OrderListAdapter;
import com.example.myproject.model.Order;
import com.example.myproject.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderActivity extends AppCompatActivity {

    TextView close;
    ListView all_order;
//    Button contactUs;
    ArrayList<Order> arraylist;
    OrderListAdapter orderListAdapter;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        close = findViewById(R.id.close);
//        contactUs = findViewById(R.id.contact);

        Intent intent = getIntent();
        userId = (String) intent.getSerializableExtra("userId");

        close.setOnClickListener(view -> finish());

        all_order = findViewById(R.id.list_order);
        arraylist = new ArrayList<>();

        orderListAdapter = new OrderListAdapter(this, R.layout.payment_item, arraylist);
        all_order.setAdapter(orderListAdapter);

        retrieveData();
    }

    public void retrieveData () {

        FirebaseFirestore.getInstance()
                .collection("orders")
                .addSnapshotListener((value, error) -> {

                    if (error != null) {
                        Log.e("Firestore Error", error.getMessage());
                        return;
                    }

                    for (DocumentChange dc : value.getDocumentChanges()) {

                        if (dc.getType() == DocumentChange.Type.ADDED) {

                            Order order = dc.getDocument().toObject(Order.class);

                            if (order.getId().equals(userId)) {
                                arraylist.add(order);
                            }
                        }
                    }
                    System.out.println(arraylist.size());
                });
    }
}