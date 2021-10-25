package com.example.myproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myproject.MyViewpager2Adapter;
import com.example.myproject.R;
import com.example.myproject.fragment.TrendFragment;
import com.example.myproject.fragment.WishListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    BottomNavigationView navigation;
    ViewPager2 viewPager2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        getSupportFragmentManager().beginTransaction().replace(R.id.my_nav, new TrendFragment()).commit();

        toolbar = findViewById(R.id.main_toolbar);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("RADAR");

        navigation = findViewById(R.id.bottom_navigation);
        viewPager2 = findViewById(R.id.view_pager2);


        MyViewpager2Adapter adapter = new MyViewpager2Adapter(this);
        viewPager2.setAdapter(adapter);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        Objects.requireNonNull(getSupportActionBar()).setTitle("RADAR");
                        navigation.getMenu().findItem(R.id.home).setChecked(true);
                        break;
                    case 1:
                        Objects.requireNonNull(getSupportActionBar()).setTitle("SHOP");
                        navigation.getMenu().findItem(R.id.search).setChecked(true);
                        break;
                    case 2:
                        Objects.requireNonNull(getSupportActionBar()).setTitle("FAVOURITE");
                        navigation.getMenu().findItem(R.id.favourite).setChecked(true);
                        break;
                    case 3:
                        Objects.requireNonNull(getSupportActionBar()).setTitle("SHOPPING BAG");
                        navigation.getMenu().findItem(R.id.shoppingbag).setChecked(true);
                        break;
                    case 4:
                        Objects.requireNonNull(getSupportActionBar()).setTitle("ACCOUNT");
                        navigation.getMenu().findItem(R.id.account).setChecked(true);
                        break;
                }
            }
        });


//        loadFragment(new TrendFragment());

        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment;

                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportActionBar().setTitle("RADAR");
                        viewPager2.setCurrentItem(0);
//                        fragment = new TrendFragment();
//                        loadFragment(fragment);
                        return true;
                    case R.id.search:
                        getSupportActionBar().setTitle("SHOP");
                        viewPager2.setCurrentItem(1);
//                        fragment = new TrendFragment();
//                        loadFragment(fragment);
                        return true;
                    case R.id.favourite:
                        getSupportActionBar().setTitle("FAVOURITE");
                        viewPager2.setCurrentItem(2);
//                        fragment = new TrendFragment();
//                        loadFragment(fragment);
                        return true;
                    case R.id.shoppingbag:
                        getSupportActionBar().setTitle("SHOPPING BAG");
                        viewPager2.setCurrentItem(3);
//                        fragment = new TrendFragment();
//                        loadFragment(fragment);
                        return true;
                    case R.id.account:
                        getSupportActionBar().setTitle("ACCOUNT");
                        viewPager2.setCurrentItem(4);
//                        fragment = new TrendFragment();
//                        loadFragment(fragment);
                        return true;
//                    case R.id.btnMen1:
//                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                        viewPager2.setCurrentItem(5);
//                        return true;
                }

                return false;
            }

        });


    }

//    private void loadFragment(Fragment fragment) {
//        // load fragment
//        FragmentTransaction transaction =
//                getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.view_pager2, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.search_icon) {

            Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(intent);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}