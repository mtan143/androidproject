package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myproject.fragment.AccountFragment;
import com.example.myproject.fragment.BagFragment;
import com.example.myproject.fragment.SearchFragment;
import com.example.myproject.fragment.TrendFragment;
import com.example.myproject.fragment.WishListFragment;

public class MyViewpager2Adapter extends FragmentStateAdapter {
    public MyViewpager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TrendFragment();
            case 1:
                return new SearchFragment();
            case 2:
                return new WishListFragment();
            case 3:
                return new BagFragment();
            case 4:
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