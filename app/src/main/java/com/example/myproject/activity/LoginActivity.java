package com.example.myproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.DBHelper;
import com.example.myproject.R;
import com.example.myproject.fragment.AccountFragment;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button btnLogin;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //move between login and register

        TextView btnClose = findViewById(R.id.btnClose);

        Button registerSlide = findViewById(R.id.register_slide);

        //login process

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login_button);
        dbHelper = new DBHelper(this);


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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pwd = password.getText().toString();

                if (user.equals("") || pwd.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {

                    if (dbHelper.checkUsernamePassword(user, pwd)) {
                        Toast.makeText(LoginActivity.this, "Sign In Successfully", Toast.LENGTH_SHORT).show();
                        AccountFragment.status = true;
                        Intent intent = new Intent(getApplicationContext(), AccountFragment.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}