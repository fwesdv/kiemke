package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.Api.ApiService;
import com.example.test.Model.Asset;
import com.example.test.Model.Inventory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetDetail extends AppCompatActivity {
    Asset assets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asset_detail);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            assets=(Asset) bundle.getSerializable("asset");
            TextView assetname=findViewById(R.id.name_ts);
            assetname.setText(assets.getAssets().getName());
            TextView assetId=findViewById(R.id.assetID);
            assetId.setText(assets.getAssetId());
            TextView oldId=findViewById(R.id.oldAssetId);
            oldId.setText(assets.getAssets().getOldAssetId());
            TextView briefId=findViewById(R.id.briefId);
            briefId.setText(assets.getAssets().getBriefId());
            TextView status=findViewById(R.id.status);
            status.setText(assets.getAssets().getStatus());
        }
        else{
            Toast.makeText(AssetDetail.this,"null dcm", Toast.LENGTH_SHORT).show();
        }

        Button back=findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}