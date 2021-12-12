package com.example.myproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myproject.R;
import com.example.myproject.adapter.OrderAdapter;
import com.example.myproject.adapter.SearchAdapter;
import com.example.myproject.model.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SearchActivity extends AppCompatActivity {

    TextView tv;
    SearchView searchView;
    Button iconSearch;
    RecyclerView listSearch;
    SearchAdapter adapter;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        tv = findViewById(R.id.textView);
        searchView = findViewById(R.id.search_view);
        iconSearch = findViewById(R.id.iconSearch);
        listSearch = findViewById(R.id.list_search);

        tv.setOnClickListener(view -> finish());

        listSearch.setLayoutManager(new LinearLayoutManager(this));
        listSearch.setItemAnimator(null);
        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("products"), Product.class)
                .build();

        adapter = new SearchAdapter(options);
        listSearch.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processSearch(s);
                return false;
            }
        });
    }

    private void processSearch(String s) {
        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child("products")
                                .orderByChild("name")
                                .startAt(s)
                                .endAt(s + "\uf8ff"), Product.class)
                        .build();

        adapter = new SearchAdapter(options);
        adapter.startListening();
        listSearch.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setIconified(true);

        return super.onCreateOptionsMenu(menu);
    }
}

