package com.example.myproject.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myproject.R;
import com.example.myproject.category.CoatMenCategory;
import com.example.myproject.category.JacketMenCategory;
import com.example.myproject.category.JacketWomanCategory;
import com.example.myproject.category.ShirtWomanCategory;

public class TrendFragment extends Fragment {

    private View mView;
    Button btnHighLight1;
    Button btnHighLight2;
    Button btnHighLight3;
    Button btnHighLight4;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_trend, container, false);


        btnHighLight1 = mView.findViewById(R.id.btnMen1);
        btnHighLight2 = mView.findViewById(R.id.btnMen2);
        btnHighLight3 = mView.findViewById(R.id.btnWomen1);
        btnHighLight4 = mView.findViewById(R.id.btnWomen2);

        btnHighLight1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), JacketMenCategory.class));
            }
        });

        btnHighLight2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CoatMenCategory.class));
            }
        });

        btnHighLight3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), JacketWomanCategory.class));
            }
        });

        btnHighLight4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ShirtWomanCategory.class));
            }
        });

        return mView;
    }
}