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

import com.example.myproject.DBHelper;
import com.example.myproject.R;
import com.example.myproject.fragment.AccountFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
//                if (user.equals("") || pwd.equals("")) {
//                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
//                } else {
//
//                    if (user.equals("admin") || pwd.equals("admin")) {
//                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
//                        startActivity(intent);
//                    }
//                    if (dbHelper.checkUsernamePassword(user, pwd)) {
//                        Toast.makeText(LoginActivity.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
//                        AccountFragment.status = true;
//                        Intent intent = new Intent(getApplicationContext(), AccountFragment.class);
//                        startActivity(intent);
//                    } else {
//                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
//                    }
                }
        });
    }

    private void userLogin() {
        String uEmail = email.getText().toString().trim();
        String pwd = password.getText().toString().trim();

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
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
//                            startActivity(new Intent(this, AccountFragment.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Login Failed! Try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}