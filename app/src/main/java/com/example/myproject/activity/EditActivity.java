package com.example.myproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.R;
import com.example.myproject.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EditActivity extends AppCompatActivity {

    EditText name, username, email, phone, address;
    Button btnUpdate;
    String name_value;
    String username_value;
    String email_value;
    String phone_value;
    String address_value;
    TextView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        name = findViewById(R.id.edit_name);
        username = findViewById(R.id.edit_username);
        email = findViewById(R.id.edit_email);
        phone = findViewById(R.id.edit_phone);
        address = findViewById(R.id.edit_address);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnBack = findViewById(R.id.edit_back);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");

        name.setText(user.getName());
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        phone.setText(user.getPhone());
        address.setText(user.getAddress());

        name_value = name.getText().toString();
        username_value = username.getText().toString();
        email_value = email.getText().toString();
        phone_value = phone.getText().toString();
        address_value = address.getText().toString();

        btnBack.setOnClickListener(view -> finish());

        btnUpdate.setOnClickListener(view -> {

            name_value = name.getText().toString();
            username_value = username.getText().toString();
            email_value = email.getText().toString();
            phone_value = phone.getText().toString();
            address_value = address.getText().toString();

            if (checkData()) {
                Map<String, Object> data = new HashMap<>();

                data.put("name", name_value);
                data.put("username", username_value);
                data.put("email", email_value);
                data.put("phone", phone_value);
                data.put("address", address_value);


                FirebaseFirestore.getInstance()
                        .collection("users")
                        .document(user.getId())
                        .update(data)
                        .addOnSuccessListener(unused -> {
                            Toast.makeText(getApplicationContext(), "Update Successfully!", Toast.LENGTH_SHORT).show();
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(getApplicationContext(), "Update Failed!", Toast.LENGTH_SHORT).show();
                        });
            } else {
                Toast.makeText(getApplicationContext(), "Check data again!", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public boolean checkData () {

        if (username_value.isEmpty()) {
            username.setError("Username is required!");
            username.requestFocus();
            return false;
        }

        if (email_value.isEmpty()) {
            email.setError("Email is required!");
            email.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email_value).matches()) {
            email.setError("Email invalid!");
            email.requestFocus();
            return false;
        }
        if (name_value.isEmpty()) {
            name.setError("Name invalid!");
            name.requestFocus();
            return false;
        }
        if (phone_value.isEmpty()) {
            phone.setError("Phone invalid!");
            phone.requestFocus();
            return false;
        }
        if (phone_value.length() != 10) {
            phone.setError("Phone invalid!");
            phone.requestFocus();
            return false;
        }
        if (address_value.isEmpty()) {
            address.setError("Address invalid!");
            address.requestFocus();
            return false;
        }
        return true;
    }
}