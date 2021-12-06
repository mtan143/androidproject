package com.example.myproject.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.activity.LoginActivity;
import com.example.myproject.activity.PaymentActivity;
import com.example.myproject.adapter.ProductAdapter;
import com.example.myproject.model.Product;
import com.example.myproject.product.ProductDetailActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class BagFragment extends Fragment {

    private View mView;
    GridView gridView;
    ArrayList<Product> arrayList;
    ProductAdapter adapter;
    FirebaseFirestore dbFirestore;
    TextView tv5, tv6;
    Button removeAll, payBtn;

    public BagFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        dbFirestore = FirebaseFirestore.getInstance();
        arrayList = new ArrayList<>();

        adapter = new ProductAdapter(getContext(),R.layout.layout_product_item, arrayList);
        gridView.setAdapter(adapter);

        retrieveData();
        itemClick();
        removeAllButton();
        setPayBtn();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_bag, container, false);
        gridView = mView.findViewById(R.id.cart);
        tv5 = mView.findViewById(R.id.textView5);
        tv6 = mView.findViewById(R.id.textView6);
        removeAll = mView.findViewById(R.id.removeAll);
        payBtn = mView.findViewById(R.id.payBtn);

        return mView;
    }

    /**
     * Remove all on click
     */
    public void removeAllButton() {
        removeAll.setOnClickListener(view -> {
            for (Product prd : arrayList) {
                dbFirestore.collection("products").document(prd.getId())
                        .update("cart", false)
                        .addOnSuccessListener(unused -> {
                            Toast.makeText(getContext(), "Remove Successfully!", Toast.LENGTH_LONG).show();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(getContext(), "Failed! Try again!", Toast.LENGTH_LONG).show();
                        });
            }
            arrayList.clear();
            retrieveData();
            adapter = new ProductAdapter(getContext(),R.layout.layout_product_item, arrayList);
            gridView.setAdapter(adapter);
        });
    }

    /**
     * pay button
     */
    public void setPayBtn () {
        payBtn.setOnClickListener(view -> {
            if (!arrayList.isEmpty()) {
                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                    Toast.makeText(getActivity(), "You need to login first!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    Intent intent = new Intent(getActivity(), PaymentActivity.class);
                    intent.putExtra("order", arrayList);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * Retrieving data from Firebase
     */
    public void retrieveData () {
        dbFirestore.collection("products")
                .addSnapshotListener((value, error) -> {

                    for (DocumentChange dc : value.getDocumentChanges()) {

                        if (dc.getType() == DocumentChange.Type.ADDED) {

                            Product product = dc.getDocument().toObject(Product.class);

                            if (product.isCart()) {
                                product.setId(dc.getDocument().getId());
                                arrayList.add(product);

                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                    if (arrayList.isEmpty()) {
                        tv6.setText(R.string.bag1);
                        tv5.setText(R.string.bag2);
                    } else {
                        tv6.setText("");
                        tv5.setText("");
                    }
                });
    }


    /**
     * Handle item click
     */
    public void itemClick () {
        gridView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
            intent.putExtra("product", arrayList.get(i));
            startActivity(intent);
        });
    }
}