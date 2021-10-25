package com.example.myproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Users.db";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {

        myDB.execSQL("create table users(username text primary key, password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {

        myDB.execSQL("drop table if exists users");

    }

    public Boolean insertData(String username, String password) {

        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDB.insert("users", null, contentValues);

        return result != -1;

    }

    public Boolean checkUsername(String username) {

        SQLiteDatabase myDB = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = myDB
                .rawQuery("select * from users where username = ?", new String[] {username});

        return cursor.getCount() > 0;
    }

    public Boolean checkUsernamePassword(String username, String password) {

        SQLiteDatabase myDB = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = myDB
                .rawQuery("select * from users where username = ? and password = ?", new String[] {username, password});

        return cursor.getCount() > 0;
    }
}
