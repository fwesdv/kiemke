package com.example.test.Helper;

import android.app.Application;
import android.content.Context;

public class ContextHelper extends Application {
    private static ContextHelper instance;

    public static ContextHelper getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}

