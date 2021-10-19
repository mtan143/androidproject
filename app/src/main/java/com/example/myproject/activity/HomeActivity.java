package com.example.myproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myproject.MyViewpager2Adapter;
import com.example.myproject.R;
import com.example.myproject.fragment.TrendFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    BottomNavigationView navigation;
    ViewPager2 viewPager2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.main_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("RADAR");

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
                        getSupportActionBar().setTitle("RADAR");
                        navigation.getMenu().findItem(R.id.home).setChecked(true);
                        break;
                    case 1:
                        getSupportActionBar().setTitle("SEARCH");
                        navigation.getMenu().findItem(R.id.search).setChecked(true);
                        break;
                    case 2:
                        getSupportActionBar().setTitle("FAVOURITE");
                        navigation.getMenu().findItem(R.id.favourite).setChecked(true);
                        break;
                    case 3:
                        getSupportActionBar().setTitle("SHOPPING BAG");
                        navigation.getMenu().findItem(R.id.shoppingbag).setChecked(true);
                        break;
                    case 4:
                        getSupportActionBar().setTitle("ACCOUNT");
                        navigation.getMenu().findItem(R.id.account).setChecked(true);
                        break;
                }
            }
        });










//        loadFragment(new TrendFragment());

        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment;

                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportActionBar().setTitle("RADAR");
                        viewPager2.setCurrentItem(0);
                        fragment = new TrendFragment();
//                        loadFragment(fragment);
                        return true;
                    case R.id.search:
                        getSupportActionBar().setTitle("SEARCH");
                        viewPager2.setCurrentItem(1);
                        fragment = new TrendFragment();
//                        loadFragment(fragment);
                        return true;
                    case R.id.favourite:
                        getSupportActionBar().setTitle("FAVOURITE");
                        viewPager2.setCurrentItem(2);
                        fragment = new TrendFragment();
//                        loadFragment(fragment);
                        return true;
                    case R.id.shoppingbag:
                        getSupportActionBar().setTitle("SHOPPING BAG");
                        viewPager2.setCurrentItem(3);
                        fragment = new TrendFragment();
//                        loadFragment(fragment);
                        return true;
                    case R.id.account:
                        getSupportActionBar().setTitle("ACCOUNT");
                        viewPager2.setCurrentItem(4);
                        fragment = new TrendFragment();
//                        loadFragment(fragment);
                        return true;
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
}