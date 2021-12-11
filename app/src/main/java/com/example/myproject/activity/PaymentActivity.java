package com.example.myproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.adapter.OrderAdapter;
import com.example.myproject.fragment.ProfileFragment;
import com.example.myproject.model.Order;
import com.example.myproject.model.Product;
import com.example.myproject.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PaymentActivity extends AppCompatActivity {

    ListView total_item;
    TextView total_price;
    Button btnConfirm;
    TextView btnCancel;
    TextView name;
    TextView address;
    TextView phone;
    ArrayList<Product> arrayList;
    int totalPrice = 0;
    OrderAdapter adapter;
    User user;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        total_item = findViewById(R.id.total_item);
        total_price = findViewById(R.id.total_price);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnCancel = findViewById(R.id.btnCancel);
        name = findViewById(R.id.name_value);
        address = findViewById(R.id.address_value);
        phone = findViewById(R.id.phone_value);

        //btn cancel
        btnCancel.setOnClickListener(view -> {
            finish();
        });

        //receive data from bag fragment
        Intent intent = getIntent();
        arrayList = (ArrayList<Product>) intent.getSerializableExtra("order");

        //btnConfirm
        btnConfirm.setOnClickListener(view -> {
            retrieveData();
            addOrder();
        });

        //setting adapter
        adapter = new OrderAdapter(this, R.layout.order_item, arrayList);
        total_item.setAdapter(adapter);

        //counting total price
        for (Product product : arrayList) {
            totalPrice += product.getPrice();
        }
        total_price.setText("AU$ " + new DecimalFormat("#,###").format(totalPrice));


        retrieveData();

    }

    public void retrieveData() {

        FirebaseFirestore.getInstance()
                .collection("users")
                .addSnapshotListener((value, error) -> {

                    if (error != null) {
                        Log.e("Firestore Error", error.getMessage());
                        return;
                    }

                    for (DocumentChange dc : value.getDocumentChanges()) {

                        if (dc.getType() == DocumentChange.Type.ADDED) {

                            user = dc.getDocument().toObject(User.class);

                            if (user.getEmail().equals(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail())) {
                                user.setId(dc.getDocument().getId());
                                name.setText(user.getName());
                                phone.setText(user.getPhone());
                                address.setText(user.getAddress());
                            }
                        }
                    }
                });
    }

    public void addOrder() {

        List<String> products = new ArrayList<>();

        for (Product product : arrayList) {
            products.add(product.getName());
        }

        Order order = new Order();
        order.setId(user.getId());
        order.setStatus("Processing");
        order.setTotalPrice(totalPrice);
        order.setUsername(user.getUsername());
        order.setProducts(products);


        FirebaseFirestore.getInstance()
                .collection("orders")
                .add(order)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(getApplicationContext(), "Check your order!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getApplicationContext(), "Failed! try again!", Toast.LENGTH_SHORT).show();
                });
    }
}