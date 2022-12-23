package com.example.test.Api;

import com.example.test.Model.Asset;
import com.example.test.Model.AuthResponse;
import com.example.test.Model.BaseResponeData;
import com.example.test.Model.Bases;
import com.example.test.Model.InventoryDetailResData;
import com.example.test.Model.InventoryPostModel;
import com.example.test.Model.InventoryPutModel;
import com.example.test.Model.InventoryResponeData;
import com.example.test.Model.LoginModel;
import com.example.test.Model.Unit;
import com.example.test.Model.UnitResponeData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    TokenInterceptor tokenInterceptor = new TokenInterceptor();
    CustomDispatcher dispatcher = new CustomDispatcher();


    OkHttpClient client  = new OkHttpClient().newBuilder().addInterceptor(tokenInterceptor)
            .dispatcher(dispatcher.getDispatcher()).build();
    Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    ApiService apiService= new Retrofit.Builder()
            .baseUrl("http://192.168.1.8:8000/api/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

            .create(ApiService.class);

    @GET("Inventory")
    Call<InventoryResponeData> getAllInven();

    @GET("Bases")
    Call<BaseResponeData> getAllBase();

    @GET("Unit")
    Call<UnitResponeData> getAllUnit();
    @GET("Asset/{id}")
    Call<Asset> getAsset(@Path("id") String id);
    @GET("Inventory/{id}")
    Call<InventoryDetailResData> getInven(@Path("id") String id);
    @POST("auth")
    Call<AuthResponse> login(@Body LoginModel loginModel);
    @POST("auth/refresh_token")
    Call<AuthResponse> refreshToken(@Body AuthResponse authResponse);
    @POST("Inventory")
    Call<InventoryPostModel> addNewInven(@Body InventoryPostModel inventoryPostModel);
    @PUT("Inventory/{id}")
    Call<InventoryPutModel> editInven(@Path("id") String id, @Body InventoryPutModel inventoryPutModel);
}
