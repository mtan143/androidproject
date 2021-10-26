package com.example.myproject.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myproject.R;
import com.example.myproject.ShopFragmentAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopFragment#} factory method to
 * create an instance of this fragment.
 */
public class ShopFragment extends Fragment {

    private View mView;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ShopFragmentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_shop, container, false);

        Button menCate1 = mView.findViewById(R.id.menCate1);
        menCate1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                menCate1.setBackgroundColor(R.color.white);
                menCate1.setTextColor(R.color.black);
            }
        });

        return mView;
    }
}