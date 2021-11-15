package com.example.myproject.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.myproject.R;
import com.example.myproject.adapter.ShopFragmentAdapter;
import com.example.myproject.category.BagWomanCategory;
import com.example.myproject.category.CoatMenCategory;
import com.example.myproject.category.DenimMenCategory;
import com.example.myproject.category.JacketMenCategory;
import com.example.myproject.category.JacketWomanCategory;
import com.example.myproject.category.PantsMenCategory;
import com.example.myproject.category.PantsWomanCategory;
import com.example.myproject.category.ShirtWomanCategory;
import com.example.myproject.category.ShoesWomanCategory;
import com.example.myproject.category.SneakersMenCategory;
import com.example.myproject.category.SwimwearWomanCategory;
import com.example.myproject.category.TshirtMenCategory;
import com.example.myproject.category.TshirtWomanCategory;
import com.google.android.material.tabs.TabLayout;

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

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.lefttoright);
        Animation animation1 = AnimationUtils.loadAnimation(getContext(), R.anim.righttoleft);
        Animation animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.lefttoright);
        Animation animation3 = AnimationUtils.loadAnimation(getContext(), R.anim.righttoleft);
        Button menBtn = mView.findViewById(R.id.menBtn);
        Button JacketmenCate = mView.findViewById(R.id.menCate1);
        Button PantmenCate = mView.findViewById(R.id.menCate2);
        Button TshirtmenCate = mView.findViewById(R.id.menCate3);
        Button DenimmenCate = mView.findViewById(R.id.menCate4);
        Button CoatmenCate = mView.findViewById(R.id.menCate5);
        Button SneakermenCate = mView.findViewById(R.id.menCate6);
        Button wmanBtn = mView.findViewById(R.id.womanBtn);
        Button ShirtwmanCate = mView.findViewById(R.id.wmanCate1);
        Button TshirtwmanCate = mView.findViewById(R.id.wmanCate2);
        Button BagwmanCate = mView.findViewById(R.id.wmanCate3);
        Button ShoewmanCate = mView.findViewById(R.id.wmanCate4);
        Button PantwmanCate = mView.findViewById(R.id.wmanCate5);
        Button JacketwmanCate = mView.findViewById(R.id.wmanCate6);
        Button SwimwearwmanCate = mView.findViewById(R.id.wmanCate7);

        menBtn.startAnimation(animation);
        JacketmenCate.startAnimation(animation1);
        PantmenCate.startAnimation(animation2);
        TshirtmenCate.startAnimation(animation3);
        DenimmenCate.startAnimation(animation1);
        CoatmenCate.startAnimation(animation);
        SneakermenCate.startAnimation(animation2);
        wmanBtn.startAnimation(animation3);
        ShirtwmanCate.startAnimation(animation);
        TshirtwmanCate.startAnimation(animation1);
        BagwmanCate.startAnimation(animation3);
        ShoewmanCate.startAnimation(animation2);
        PantwmanCate.startAnimation(animation2);
        JacketwmanCate.startAnimation(animation1);
        SwimwearwmanCate.startAnimation(animation);


        //Men Category button
        JacketmenCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), JacketMenCategory.class);

                startActivity(intent);

            }
        });

        PantmenCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), PantsMenCategory.class);

                startActivity(intent);

            }
        });

        TshirtmenCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), TshirtMenCategory.class);

                startActivity(intent);

            }
        });

        DenimmenCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DenimMenCategory.class);

                startActivity(intent);

            }
        });

        CoatmenCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), CoatMenCategory.class);

                startActivity(intent);

            }
        });

        SneakermenCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), SneakersMenCategory.class);

                startActivity(intent);

            }
        });

        //Woman category button
        ShirtwmanCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ShirtWomanCategory.class);

                startActivity(intent);

            }
        });

        TshirtwmanCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), TshirtWomanCategory.class);

                startActivity(intent);

            }
        });

        BagwmanCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), BagWomanCategory.class);

                startActivity(intent);

            }
        });

        ShoewmanCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ShoesWomanCategory.class);

                startActivity(intent);

            }
        });

        PantwmanCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), PantsWomanCategory.class);

                startActivity(intent);

            }
        });

        JacketwmanCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), JacketWomanCategory.class);

                startActivity(intent);

            }
        });

        SwimwearwmanCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), SwimwearWomanCategory.class);

                startActivity(intent);

            }
        });

        return mView;
    }
}