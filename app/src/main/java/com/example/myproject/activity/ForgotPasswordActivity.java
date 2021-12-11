package com.example.myproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText email;
    private FirebaseAuth mAuth;
    TextView btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.email);
        Button forgotPassword = findViewById(R.id.forgotPassword);
        mAuth = FirebaseAuth.getInstance();
        btnClose = findViewById(R.id.btnClose);

        btnClose.setOnClickListener(view -> finish());

        forgotPassword.setOnClickListener(view -> resetPassword());

    }

    private void resetPassword() {
        String uEmail = email.getText().toString().trim();

        if (uEmail.isEmpty()) {
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(uEmail).matches()) {
            email.setError("Email invalid!");
            email.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(uEmail).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(ForgotPasswordActivity.this, "Check your email to set new password!", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(ForgotPasswordActivity.this, "Something wrong! Try again!", Toast.LENGTH_LONG).show();
            }
        });
    }
}