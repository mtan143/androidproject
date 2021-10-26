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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fadeinlogo);
        Button menBtn = mView.findViewById(R.id.menBtn);
        Button menCate1 = mView.findViewById(R.id.menCate1);
        Button menCate2 = mView.findViewById(R.id.menCate2);
        Button menCate3 = mView.findViewById(R.id.menCate3);
        Button menCate4 = mView.findViewById(R.id.menCate4);
        Button menCate5 = mView.findViewById(R.id.menCate5);
        Button menCate6 = mView.findViewById(R.id.menCate6);
        Button wmanBtn = mView.findViewById(R.id.womanBtn);
        Button wmanCate1 = mView.findViewById(R.id.wmanCate1);
        Button wmanCate2 = mView.findViewById(R.id.wmanCate2);
        Button wmanCate3 = mView.findViewById(R.id.wmanCate3);
        Button wmanCate4 = mView.findViewById(R.id.wmanCate4);
        Button wmanCate5 = mView.findViewById(R.id.wmanCate5);
        Button wmanCate6 = mView.findViewById(R.id.wmanCate6);
        Button wmanCate7 = mView.findViewById(R.id.wmanCate7);

        menBtn.startAnimation(animation);
        menCate1.startAnimation(animation);
        menCate2.startAnimation(animation);
        menCate3.startAnimation(animation);
        menCate4.startAnimation(animation);
        menCate5.startAnimation(animation);
        menCate6.startAnimation(animation);
        wmanBtn.startAnimation(animation);
        wmanCate1.startAnimation(animation);
        wmanCate2.startAnimation(animation);
        wmanCate3.startAnimation(animation);
        wmanCate4.startAnimation(animation);
        wmanCate5.startAnimation(animation);
        wmanCate6.startAnimation(animation);
        wmanCate7.startAnimation(animation);
        return mView;
    }
}