package com.example.myproject.category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.myproject.adapter.DuLieuGiaAdapter;
import com.example.myproject.R;
import com.example.myproject.dulieugia;
import com.example.myproject.model.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class JacketMenCategory extends AppCompatActivity {

    GridView gridView;
    ArrayList<Product> arrayList;
    DuLieuGiaAdapter adapter;
    FirebaseFirestore dbFirestore;
    Map<String, Object> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jacket_men_category);

        gridView = findViewById(R.id.jacket_mengrv);
        dbFirestore = FirebaseFirestore.getInstance();
        arrayList = new ArrayList<>();
//        arrayList.add(new dulieugia("AoHinhRong","1Tr500", R.drawable.jacket1));
//        arrayList.add(new dulieugia("AoJean","2Tr500", R.drawable.jacket2));
//        arrayList.add(new dulieugia("AoTheNorthFace","3Tr500", R.drawable.jacket3));
//        arrayList.add(new dulieugia("AoDoChungToYeuEm","4Tr500", R.drawable.jacket4));
//        arrayList.add(new dulieugia("AoGucciXanh","5Tr500", R.drawable.jacket5));
//        arrayList.add(new dulieugia("AoGucciHoa","6Tr500", R.drawable.jacket6));

        adapter = new DuLieuGiaAdapter(this,R.layout.layout_dulieugia, arrayList);
        gridView.setAdapter(adapter);

    }
    public void retrieveData () {
        dbFirestore.collection("products").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                    }
                }
            }
        });
    }
}