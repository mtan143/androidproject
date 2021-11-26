package com.example.myproject.category;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.example.myproject.R;
import com.example.myproject.adapter.ProductAdapter;
import com.example.myproject.model.Product;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class BagWomanCategory extends AppCompatActivity {

    GridView gridView;
    ArrayList<Product> arrayList;
    ProductAdapter adapter;
    FirebaseFirestore dbFirestore;
    ProgressDialog progressDialog;
    TextView title;
    TextView btnDone;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bag_woman_category);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching data...");
        progressDialog.show();

        title = findViewById(R.id.titleCategory);
        title.setText("BAG");

        btnDone = findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        gridView = findViewById(R.id.bag_woman);
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

                                if (product.getCategoryCode().equals("w_bag"))
                                    arrayList.add(product);
                            }

                            adapter.notifyDataSetChanged();
                            if (progressDialog.isShowing()) progressDialog.dismiss();
                        }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.done_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.done:
                finish();
                break;
        }
        return true;
    }
}