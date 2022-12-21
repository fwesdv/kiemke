package com.example.test.Api;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.example.test.Helper.ContextHelper;
import com.example.test.Model.AuthResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class TokenInterceptor implements Interceptor {
    String accessToken = "";
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextHelper.getContext());


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request newRequest = chain.request();
        Response response = chain.proceed(newRequest);
        accessToken = sharedPreferences.getString("accessToken", "");
        String refreshToken = sharedPreferences.getString("refreshToken", "");


        newRequest = chain.request().newBuilder()
//                .addHeader("Content-Type:","application/json")
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();
        return chain.proceed(newRequest);
    }
}



