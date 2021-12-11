package com.example.myproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myproject.R;
import com.example.myproject.adapter.OrderListAdapter;
import com.example.myproject.model.Order;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class OrderActivity extends AppCompatActivity {

    TextView close;
    ListView all_order;
    ArrayList<Order> arraylist;
    OrderListAdapter orderListAdapter;
    String username;
    TextView empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        close = findViewById(R.id.close);
        empty = findViewById(R.id.empty_order);

        Intent intent = getIntent();
        username = (String) intent.getSerializableExtra("username");

        close.setOnClickListener(view -> finish());

        all_order = findViewById(R.id.list_order);
        arraylist = new ArrayList<>();
        retrieveData();

        orderListAdapter = new OrderListAdapter(this, R.layout.payment_item, arraylist);
        all_order.setAdapter(orderListAdapter);

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

                            if (order.getUsername() != null) {
                                if (order.getUsername().equals(username)) {
                                    order.setId(dc.getDocument().getId());
                                    arraylist.add(order);
                                }
                            }
                        }
                        orderListAdapter.notifyDataSetChanged();
                    }

                    if (!arraylist.isEmpty()) {
                        empty.setText("");
                    }
                    System.out.println(arraylist.size());
                });
    }
}