package com.example.myproject.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WishListFragment#} factory method to
 * create an instance of this fragment.
 */
public class WishListFragment extends Fragment {

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_wish_list, container, false);
        return mView;
    }
}