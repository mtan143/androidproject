package com.example.myproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.DBHelper;
import com.example.myproject.R;
import com.example.myproject.fragment.AccountFragment;
import com.example.myproject.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity{

    EditText username;
    EditText email;
    EditText password;
    EditText name;
    EditText phone;
    EditText address;
    Button btnRegister;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button loginSlide = findViewById(R.id.register_slide);

        //register process
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        name = findViewById(R.id.nameuser);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        password = findViewById(R.id.password);
        btnRegister = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        //switch to login slide
        loginSlide.setOnClickListener(view -> onBackPressed());

        //register
        btnRegister.setOnClickListener(view -> registerUser());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    private void registerUser() {

        String uName = username.getText().toString().trim();
        String uEmail = email.getText().toString().trim();
        String uPwd = password.getText().toString().trim();
        String yourName = name.getText().toString().trim();
        String yourPhone = phone.getText().toString().trim();
        String yourAddress = address.getText().toString().trim();

        if (uName.isEmpty()) {
            username.setError("Username is required!");
            username.requestFocus();
            return;
        }

        if (uEmail.isEmpty()) {
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        if (uPwd.isEmpty()) {
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }

        if (uPwd.length() < 6) {
            password.setError("Password must be greater than 6!");
            password.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(uEmail).matches()) {
            email.setError("Email invalid!");
            email.requestFocus();
            return;
        }
        if (yourName.isEmpty()) {
            name.setError("Name invalid!");
            name.requestFocus();
            return;
        }
        if (yourPhone.isEmpty()) {
            phone.setError("Phone invalid!");
            phone.requestFocus();
            return;
        }
        if (yourPhone.length() != 10) {
            phone.setError("Phone invalid!");
            phone.requestFocus();
            return;
        }
        if (yourAddress.isEmpty()) {
            address.setError("Address invalid!");
            address.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(uEmail, uPwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Map<String, Object> user = new HashMap<>();
                    user.put("username", uName);
                    user.put("email", uEmail);
                    user.put("password", uPwd);
                    user.put("name", yourName);
                    user.put("phone", yourPhone);
                    user.put("address", yourAddress);

                    firestore.collection("users")
                            .add(user);

                    Toast.makeText(RegisterActivity.this,"You are successfully Registered", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"You are not Registered! Try again",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}