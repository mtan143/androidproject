package com.example.myproject.activity;

import static com.example.myproject.MyApplication.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.DBHelper;
import com.example.myproject.R;
import com.example.myproject.fragment.AccountFragment;
import com.example.myproject.fragment.ProfileFragment;
import com.example.myproject.fragment.TrendFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button btnLogin;
    TextView forgotPassword;
    DBHelper dbHelper;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //move between login and register

        TextView btnClose = findViewById(R.id.btnClose);

        Button registerSlide = findViewById(R.id.register_slide);

        //login process

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login_button);
        forgotPassword = findViewById(R.id.forgotPassword);
        dbHelper = new DBHelper(this);
        mAuth = FirebaseAuth.getInstance();


        //close button will back to account tab
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //intent to register
        registerSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);

                startActivity(intent);

                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
                }
        });
    }

    private void userLogin() {
        String uEmail = email.getText().toString();
        String pwd = password.getText().toString();

        if (uEmail.isEmpty()) {

            email.setError("Username is required!");
            email.requestFocus();
            return;

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(uEmail).matches()) {

            email.setError("Email invalid!");
            email.requestFocus();
            return;

        }

        if (pwd.isEmpty()) {

            password.setError("Password is required!");
            password.requestFocus();
            return;

        }

        if (pwd.length() < 6) {

            password.setError("Password must be greater than 6!");
            password.requestFocus();
            return;

        }

        mAuth.signInWithEmailAndPassword(uEmail, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login successful!",
                                    Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}