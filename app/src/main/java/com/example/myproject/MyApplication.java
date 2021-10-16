package com.example.myproject;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyApplication extends Application {

    public static final String TAG = MyApplication.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }


    public RequestQueue getRequestQueue() {

        mRequestQueue = mRequestQueue == null ? Volley.newRequestQueue(getApplicationContext()) : mRequestQueue;

        return mRequestQueue;
    }


    public <T> void addToRequestQueue (Request<T> request, String tag) {

        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(request);
    }


    public <T> void addToRequestQueue (Request<T> request) {

        request.setTag(TAG);
        getRequestQueue().add(request);
    }
}
