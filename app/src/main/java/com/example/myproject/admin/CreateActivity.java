package com.example.myproject.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateActivity extends AppCompatActivity {

    Button btnCancel;
    Button btnAdd;
    EditText txtName;
    EditText txtPrice;
    EditText txtDescription;
    EditText txtCategoryCode;
    EditText txtImage;
    EditText txtDockey;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        btnCancel = findViewById(R.id.cancel);
        btnAdd = findViewById(R.id.btnAdd);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtDescription = findViewById(R.id.txtDescription);
        txtCategoryCode = findViewById(R.id.txtCategoryCode);
        txtImage = findViewById(R.id.txtImage);
        txtDockey = findViewById(R.id.txtDockey);

        firestore = FirebaseFirestore.getInstance();


        btnCancel.setOnClickListener(view -> finish());

        btnAdd.setOnClickListener(view -> addProduct());

    }

    private void checkData() {
        String name = txtName.getText().toString();
        String price = txtPrice.getText().toString();
        String des = txtDescription.getText().toString();
        String cateCode = txtCategoryCode.getText().toString();
        String img = txtImage.getText().toString();
        String dockey = txtDockey.getText().toString();

        if (name.isEmpty()) {
            txtName.setError("Name Invalid");
            txtName.requestFocus();
            return;
        }

        if (price.isEmpty()) {
            txtPrice.setError("Price Invalid");
            txtPrice.requestFocus();
            return;
        }

        if (des.isEmpty()) {
            txtDescription.setError("Description Invalid");
            txtDescription.requestFocus();
            return;
        }

        if (cateCode.isEmpty()) {
            txtCategoryCode.setError("CategoryCode Invalid");
            txtCategoryCode.requestFocus();
            return;
        }

        if (img.isEmpty()) {
            txtImage.setError("Image link Invalid");
            txtImage.requestFocus();
            return;
        }

        if (dockey.isEmpty()) {
            txtDockey.setError("Dockey Invalid");
            txtDockey.requestFocus();
            return;
        }
    }

    public void addProduct() {
        checkData();
        Map<String, Object> data = new HashMap<>();
        data.put("name", txtName.getText().toString());
        data.put("price", Integer.parseInt(txtPrice.getText().toString()));
        data.put("description", txtDescription.getText().toString());
        data.put("categoryCode", txtCategoryCode.getText().toString());
        data.put("ImgLink", txtImage.getText().toString());
        data.put("dockey", txtDockey.getText().toString());

        firestore.collection("products")
                .add(data)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(getApplicationContext(), "Already Created New Product", Toast.LENGTH_LONG).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Try again!", Toast.LENGTH_LONG).show());
    }
}