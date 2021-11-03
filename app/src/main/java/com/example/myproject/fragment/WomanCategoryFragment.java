package com.example.myproject.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myproject.R;
import com.example.myproject.Adapter.WomanRecyclerViewAdapter;


public class WomanCategoryFragment extends Fragment {

    private View mView;

    RecyclerView recyclerView;

    String[] s;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_woman_category, container, false);

        recyclerView = mView.findViewById(R.id.recyclerView);
        s = getResources().getStringArray(R.array.womanshop);

        WomanRecyclerViewAdapter womanRecyclerViewAdapter = new WomanRecyclerViewAdapter(getActivity(), s);
        recyclerView.setAdapter(womanRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return mView;
    }
}