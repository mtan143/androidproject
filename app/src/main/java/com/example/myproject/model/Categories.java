package com.example.myproject.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Categories {

    private Integer id;

    private String name;


    private String code;

    public Categories(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Bitmap convertStringToBitmapFromAccess(Context context, String filename) {

        AssetManager assetManager = context.getAssets();

        try {

            InputStream inputStream = assetManager.open(filename);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
