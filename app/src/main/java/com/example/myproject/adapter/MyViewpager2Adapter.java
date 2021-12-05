package com.example.myproject.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myproject.fragment.AccountFragment;
import com.example.myproject.fragment.BagFragment;
import com.example.myproject.fragment.FavFragment;
import com.example.myproject.fragment.ProfileFragment;
import com.example.myproject.fragment.ShopFragment;
import com.example.myproject.fragment.TrendFragment;
import com.example.myproject.model.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MyViewpager2Adapter extends FragmentStateAdapter {

    FirebaseAuth mAuth;
    FirebaseFirestore dbFirestore;
    ArrayList<Product> arrayList;

    public MyViewpager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        mAuth = FirebaseAuth.getInstance();
        dbFirestore = FirebaseFirestore.getInstance();
        arrayList = new ArrayList<>();
        retrieveData();
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TrendFragment();
            case 1:
                return new ShopFragment();
            case 2:
//                if (arrayList.isEmpty()) {
//                    return new WishListFragment();
//                }
                return new FavFragment();
            case 3:
                return new BagFragment();
            case 4:
                if (mAuth.getCurrentUser() != null) {
                    return new ProfileFragment();
                }
                return new AccountFragment();
            default:
                return new TrendFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 5;
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public boolean containsItem(long itemId) {
        return super.containsItem(itemId);
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
                                product.setId(dc.getDocument().getId());
                                arrayList.add(product);
                            }
                        }
                    }
                });
    }
}
