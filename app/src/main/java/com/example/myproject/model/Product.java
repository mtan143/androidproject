package com.example.myproject.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;


public class Product implements Serializable {

    private Integer id;

    private String name;

    private String description;

    private String price;

    private String ImgLink;

    private String categoryCode;

    public Product(Integer id, String name, String description, String price, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.ImgLink = image;
    }

    public Product() {
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgLink() {
        return ImgLink;
    }

    public void setImgLink(String imgLink) {
        this.ImgLink = imgLink;
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
