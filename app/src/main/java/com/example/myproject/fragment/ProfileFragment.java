package com.example.myproject.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.activity.HomeActivity;
import com.example.myproject.activity.EditActivity;
import com.example.myproject.activity.OrderActivity;
import com.example.myproject.model.Order;
import com.example.myproject.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    Button logout;
    TextView email;
    TextView username;
    TextView name;
    TextView phone;
    TextView address;
    User tempUser;
    CardView edit;
    CardView order;
    List<Order> orders;

    @Override
    public void onResume() {
        super.onResume();
        retrieveData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        name = view.findViewById(R.id.profile_name_value);
        phone = view.findViewById(R.id.profile_phone_value);
        address = view.findViewById(R.id.profile_address_value);
        edit = view.findViewById(R.id.edit);
        order = view.findViewById(R.id.order);
        logout = view.findViewById(R.id.logout);

        logout.setOnClickListener(view1 -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getActivity(), "Log Out Success!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), HomeActivity.class));
        });

        email.setText(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail());

        retrieveData();

        edit.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), EditActivity.class);
            intent.putExtra("user", tempUser);
            startActivity(intent);
        });

        order.setOnClickListener(view12 -> {
            Intent intent = new Intent(getActivity(), OrderActivity.class);
            intent.putExtra("userId", tempUser.getId());
            startActivity(intent);
        });

        return view;
    }

    public void retrieveData () {

        FirebaseFirestore.getInstance()
                .collection("users")
                .addSnapshotListener((value, error) -> {

                    if (error != null) {
                        Log.e("Firestore Error", error.getMessage());
                        return;
                    }

                    for (DocumentChange dc : value.getDocumentChanges()) {

                        if (dc.getType() == DocumentChange.Type.ADDED) {

                            User user = dc.getDocument().toObject(User.class);

                            if (user.getEmail().equals(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail())) {
                                username.setText(user.getUsername());
                                name.setText(user.getName());
                                phone.setText(user.getPhone());
                                address.setText(user.getAddress());
                                tempUser = user;
                                tempUser.setId(dc.getDocument().getId());
                            }
                        }
                    }
                });
    }
}