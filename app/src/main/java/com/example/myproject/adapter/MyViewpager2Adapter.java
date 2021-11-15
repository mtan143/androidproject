package com.example.myproject.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myproject.fragment.AccountFragment;
import com.example.myproject.fragment.BagFragment;
import com.example.myproject.fragment.ProfileFragment;
import com.example.myproject.fragment.ShopFragment;
import com.example.myproject.fragment.TrendFragment;
import com.example.myproject.fragment.WishListFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MyViewpager2Adapter extends FragmentStateAdapter {

    FirebaseAuth mAuth;

    public MyViewpager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        mAuth = FirebaseAuth.getInstance();

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
                return new WishListFragment();
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
}
