package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myproject.fragment.MenCategoryFragment;
import com.example.myproject.fragment.WomanCategoryFragment;

public class ShopFragmentAdapter extends FragmentStateAdapter {


    public ShopFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {

            case 0:
                return new MenCategoryFragment();
            case 1:
                return new WomanCategoryFragment();
            default:
                return new MenCategoryFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
