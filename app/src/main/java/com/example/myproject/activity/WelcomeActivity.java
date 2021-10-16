package com.example.myproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myproject.R;

public class WelcomeActivity extends AppCompatActivity {

    Button btnShop;
    TextView textView;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.slogan);
        logo = findViewById(R.id.logo);
        btnShop = (Button) findViewById(R.id.btnShop);

        Animation animation = AnimationUtils.loadAnimation(WelcomeActivity.this, R.anim.fadein);

        logo.startAnimation(animation);
        btnShop.startAnimation(animation);
        textView.startAnimation(animation);

        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}