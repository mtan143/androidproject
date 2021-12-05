package com.example.myproject.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.myproject.R;
import com.example.myproject.adapter.MyViewpager2Adapter;
import com.example.myproject.adapter.ProductAdapter;
import com.example.myproject.model.Product;
import com.example.myproject.product.ProductDetailActivity;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FavFragment extends Fragment {

    private View mView;
    GridView gridView;
    ArrayList<Product> arrayList;
    ProductAdapter adapter;
    FirebaseFirestore dbFirestore;
    TextView tv3, tv4;

    public FavFragment() {
        // Required empty public constructor
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

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_fav, container, false);

        gridView = mView.findViewById(R.id.wishlist);
        tv3 = mView.findViewById(R.id.textView3);
        tv4 = mView.findViewById(R.id.textView4);

        return mView;
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

                            if (product.isLike()) {
                                if (!arrayList.contains(product)) {
                                    product.setId(dc.getDocument().getId());
                                    arrayList.add(product);
                                }
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                    if (arrayList.isEmpty()) {
                        tv4.setText(R.string.wishlistemty1);
                        tv3.setText(R.string.wishlistemty2);
                    } else {
                        tv4.setText("");
                        tv3.setText("");
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