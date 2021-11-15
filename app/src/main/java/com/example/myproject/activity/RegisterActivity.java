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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity{

    EditText username;
    EditText email;
    EditText password;
    Button btnRegister;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button loginSlide = findViewById(R.id.register_slide);

        //register process
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnRegister = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();

        //switch to login slide
        loginSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //register
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
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

//        mAuth.createUserWithEmailAndPassword(uEmail, uPwd)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            FirebaseDatabase.getInstance().getReference("Users")
//                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
//                                    .setValue(new User(uName, uEmail)).addOnCompleteListener(new OnCompleteListener<Void>() {
//
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(RegisterActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
//                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//                                    } else {
//                                        Toast.makeText(RegisterActivity.this, "Register Failed! Try again!", Toast.LENGTH_LONG).show();
//                                    }
//                                }
//                            });
//                        } else {
//                            Toast.makeText(RegisterActivity.this, "Register Failed!", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
        mAuth.createUserWithEmailAndPassword(uEmail, uPwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
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