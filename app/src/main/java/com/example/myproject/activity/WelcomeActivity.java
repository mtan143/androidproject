package com.example.myproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myproject.R;

public class WelcomeActivity extends AppCompatActivity {

    Button btnShop;
    TextView textView;
    ImageView logo;
    TextView slogan1;
    TextView slogan2;
    TextView slogan3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.slogan);
        logo = findViewById(R.id.logo);
        btnShop = findViewById(R.id.btnShop);
        slogan1 = findViewById(R.id.slogan1);
        slogan2 = findViewById(R.id.slogan2);
        slogan3 = findViewById(R.id.slogan3);

        Animation aSlogan1 = AnimationUtils.loadAnimation(WelcomeActivity.this, R.anim.fadein);
        Animation aSlogan2 = AnimationUtils.loadAnimation(WelcomeActivity.this, R.anim.fadein);
        Animation aSlogan3 = AnimationUtils.loadAnimation(WelcomeActivity.this, R.anim.fadein);

        Animation animation = AnimationUtils.loadAnimation(WelcomeActivity.this, R.anim.fadeinlogo);
        Animation animation1 = AnimationUtils.loadAnimation(WelcomeActivity.this, R.anim.fadeinlogo);
        Animation animation2= AnimationUtils.loadAnimation(WelcomeActivity.this, R.anim.fadeinlogo);

        aSlogan1.setDuration(4000);
        aSlogan2.setStartOffset(4000);
        aSlogan3.setStartOffset(8000);


        animation.setStartOffset(13000);
        animation1.setStartOffset(13000);
        animation2.setStartOffset(13000);

        slogan1.startAnimation(aSlogan1);
        slogan2.startAnimation(aSlogan2);
        slogan3.startAnimation(aSlogan3);

        logo.startAnimation(animation);
        btnShop.startAnimation(animation1);
        textView.startAnimation(animation2);

        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}