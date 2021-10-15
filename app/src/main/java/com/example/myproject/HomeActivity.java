package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myproject.fragment.TrendFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.main_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("RADAR");

        navigation = findViewById(R.id.bottom_navigation);

        loadFragment(new TrendFragment());

        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment;

                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportActionBar().setTitle("RADAR");
                        fragment = new TrendFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.search:
                        getSupportActionBar().setTitle("SEARCH");
                        fragment = new TrendFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.favourite:
                        getSupportActionBar().setTitle("FAVOURITE");
                        fragment = new TrendFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.shoppingbag:
                        getSupportActionBar().setTitle("SHOPPING BAG");
                        fragment = new TrendFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.account:
                        getSupportActionBar().setTitle("ACCOUNT");
                        fragment = new TrendFragment();
                        loadFragment(fragment);
                        return true;
                }

                return false;
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}