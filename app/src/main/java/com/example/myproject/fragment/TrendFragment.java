package com.example.myproject.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrendFragment#} factory method to
 * create an instance of this fragment.
 */
public class TrendFragment extends Fragment {

    private View mView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_trend, container, false);

        Button btnHighLight1 = mView.findViewById(R.id.btnMen1);

        btnHighLight1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

//                Navigation.findNavController(mView).navigate(R.id.action_trendFragment_to_menFragment);

//                Fragment fm2 = new MenFragment();
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.my_nav, fm2).commit();


            }
        });

        return mView;
    }
}