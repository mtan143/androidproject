package com.example.myproject.category;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.example.myproject.R;
import com.example.myproject.adapter.ProductAdapter;
import com.example.myproject.model.Product;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class TshirtWomanCategory extends AppCompatActivity {

    GridView gridView;
    ArrayList<Product> arrayList;
    ProductAdapter adapter;
    FirebaseFirestore dbFirestore;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tshirt_woman_category);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching data...");
        progressDialog.show();

        gridView = findViewById(R.id.tshirt_woman);
        dbFirestore = FirebaseFirestore.getInstance();
        arrayList = new ArrayList<>();


        adapter = new ProductAdapter(this,R.layout.layout_product_item, arrayList);
        gridView.setAdapter(adapter);

        retrieveData();
    }

    /**
     * Retrieving data from Firebase
     */
    public void retrieveData () {
        dbFirestore.collection("products")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            Log.e("Firestore Error", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()) {

                            if (dc.getType() == DocumentChange.Type.ADDED) {

                                Product product = dc.getDocument().toObject(Product.class);

                                if (product.getCategoryCode().equals("w_tshirt"))
                                    arrayList.add(product);
                            }

                            adapter.notifyDataSetChanged();
                            if (progressDialog.isShowing()) progressDialog.dismiss();
                        }
                    }
                });
    }
}