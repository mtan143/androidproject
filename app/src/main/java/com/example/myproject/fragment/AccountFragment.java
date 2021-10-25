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
import com.example.myproject.activity.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    private View mView;

    public static Boolean status = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = status ? inflater.inflate(R.layout.fragment_profile, container, false)
                : inflater.inflate(R.layout.fragment_account, container, false);

        Button btn = mView.findViewById(R.id.btnJoinRadar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), LoginActivity.class);

                startActivity(intent);

            }
        });

        return mView;
    }
}