package com.example.myproject.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        tabLayout = mView.findViewById(R.id.tab_layout);
        viewPager = mView.findViewById(R.id.view_pager);

        adapter = new ShopFragmentAdapter(getActivity());

        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {

                case 0:
                    tab.setText("Men");
                    break;
                case 1:
                    tab.setText("Woman");
                    break;
            }
        }).attach();

        return mView;
    }
}