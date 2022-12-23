package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.test.Api.ApiService;
import com.example.test.Model.Asset;
import com.example.test.Model.Inventory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asset_detail);
        Intent intent = getIntent();
        String assetId = intent.getStringExtra("assetId");
        fetchAsset(assetId);
    }
    private void fetchAsset(String assetId)
    {
        ApiService.apiService.getAsset(assetId).enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                Asset res=response.body();
                TextView assetName=findViewById(R.id.name_ts);
                assetName.setText(res.getAssetId());
            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {

            }
        });
    }
}