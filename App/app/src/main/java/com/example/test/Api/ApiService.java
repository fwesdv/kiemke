package com.example.test.Api;

import com.example.test.Model.InventoryDetailResData;
import com.example.test.Model.InventoryResponeData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    Gson gson=new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
    ApiService apiService= new Retrofit.Builder()
            .baseUrl("http://192.168.1.8:8000/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("Inventory")
    Call<InventoryResponeData> getAllInven();
    @GET("Inventory/{id}")
    Call<InventoryDetailResData> getInven(@Path("id") String id);
}
