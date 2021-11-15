package com.example.myproject.category;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.example.myproject.adapter.DuLieuGiaAdapter;
import com.example.myproject.R;
import com.example.myproject.giulieugia;

import java.util.ArrayList;

public class JacketMenCategory extends AppCompatActivity {

    GridView gridView;
    ArrayList<giulieugia> arrayList;
    DuLieuGiaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jacket_men_category);

        gridView = (GridView) findViewById(R.id.jacket_mengrv);
        arrayList = new ArrayList<>();
        arrayList.add(new giulieugia("AoHinhRong","1Tr500", R.drawable.jacket1));
        arrayList.add(new giulieugia("AoJean","2Tr500", R.drawable.jacket2));
        arrayList.add(new giulieugia("AoTheNorthFace","3Tr500", R.drawable.jacket3));
        arrayList.add(new giulieugia("AoDoChungToYeuEm","4Tr500", R.drawable.jacket4));
        arrayList.add(new giulieugia("AoGucciXanh","5Tr500", R.drawable.jacket5));
        arrayList.add(new giulieugia("AoGucciHoa","6Tr500", R.drawable.jacket6));

        adapter = new DuLieuGiaAdapter(this,R.layout.layout_giulieugia, arrayList);
        gridView.setAdapter(adapter);

    }
}