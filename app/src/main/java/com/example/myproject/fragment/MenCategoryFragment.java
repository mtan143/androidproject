package com.example.myproject.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myproject.adapter.MenRecyclerViewAdapter;
import com.example.myproject.R;


public class MenCategoryFragment extends Fragment {

    private View mView;

    RecyclerView recyclerView;

    String[] s;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_men_category, container, false);
        recyclerView = mView.findViewById(R.id.recyclerView);
        s = getResources().getStringArray(R.array.menshop);

        MenRecyclerViewAdapter menRecyclerViewAdapter = new MenRecyclerViewAdapter(getActivity(), s);
        recyclerView.setAdapter(menRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return mView;
    }
}