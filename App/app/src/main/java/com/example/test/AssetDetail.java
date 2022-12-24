package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.Api.ApiService;
import com.example.test.Model.Asset;
import com.example.test.Model.Inventory;
import com.example.test.Model.InventoryDetailResData;
import com.example.test.Model.InventoryPostModel;
import com.example.test.Model.InventoryPutModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetDetail extends AppCompatActivity {
    Inventory inventory;
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
            TextView description=findViewById(R.id.asssetDescription);
            String des=assets.getDescription();
            boolean check= assets.getDescription()!="";
            if(check)
            {
                description.setText(assets.getDescription());
            }
            else{
                description.setText("");
            }
            ArrayList<String> isExist=new ArrayList<>();
            isExist.add("Có");
            isExist.add("Không");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(AssetDetail.this,
                    android.R.layout.simple_spinner_item,
                    isExist);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Spinner isExistList=findViewById(R.id.selectExist);
            isExistList.setAdapter(adapter);
            if(assets.isExist()!=null)
            {
                if(assets.isExist()==true)
                {
                    isExistList.setSelection(0);
                }
                else if(assets.isExist()==false)
                {
                    isExistList.setSelection(1);
                }
            }
            isExistList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(isExistList.getSelectedItem().toString()=="Có")
                    {
                        ArrayList<String> assetStatus=new ArrayList<>();
                        assetStatus.add("Tốt");
                        assetStatus.add("Hư Hỏng");
                        assetStatus.add("Không sử dụng");
                        ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>(AssetDetail.this,
                                android.R.layout.simple_spinner_item,
                                assetStatus);
                        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Spinner statusList=findViewById(R.id.selectStatus);
                        statusList.setAdapter(adapterStatus);
                        if(assets.getStatus()!=null)
                        {
                            switch (assets.getStatus()){
                                case "Tốt":statusList.setSelection(0); break;
                                case "Hư Hỏng":statusList.setSelection(1); break;
                                case "Không sử dụng":statusList.setSelection(2); break;
                            }
                        }
                    }
                    else {
                        ArrayList<String> assetStatus=new ArrayList<>();
                        assetStatus.add("Đã thanh lý");
                        assetStatus.add("Đã điều chuyển");
                        assetStatus.add("Bị thất lạc");
                        ArrayAdapter<String> adapterStatus = new ArrayAdapter<String>(AssetDetail.this,
                                android.R.layout.simple_spinner_item,
                                assetStatus);
                        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Spinner statusList=findViewById(R.id.selectStatus);
                        statusList.setAdapter(adapterStatus);
                        if(assets.getStatus()!=null)
                        {
                            switch (assets.getStatus()){
                                case "Đã thanh lý":statusList.setSelection(0); break;
                                case "Đã điều chuyển":statusList.setSelection(1); break;
                                case "Bị thất lạc":statusList.setSelection(2); break;
                            }
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


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

        Button confirm =findViewById(R.id.btnXacNhan);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String invenId=assets.getInvenId().toString();
                ApiService.apiService.getInven(invenId).enqueue(new Callback<InventoryDetailResData>() {
                    @Override
                    public void onResponse(Call<InventoryDetailResData> call, Response<InventoryDetailResData> response) {
                        inventory=response.body().getData();
                        String assetId=assets.getAssetId().toString();
                        boolean isExist=false;
                        Spinner exist=findViewById(R.id.selectExist);
                        if(exist.getSelectedItem().toString()=="Có")
                        {
                            isExist=true;
                        }
                        Spinner status=findViewById(R.id.selectStatus);
                        EditText description=findViewById(R.id.asssetDescription);
                        Asset asset=new Asset(invenId,assetId,isExist,status.getSelectedItem().toString(),description.getText().toString());

                        List<Asset> assetList=new ArrayList<>();
                        inventory.getInventoryAssetsList().forEach((i)->{
                            boolean check=i.getAssetId().equals(assetId);
                            if(check){
                                assetList.add(asset);
                            }
                            else{
                                assetList.add(i);
                            }
                        });
                        InventoryPutModel inventoryPostModel=new InventoryPutModel(inventory.getFinish(),assetList);
                        ApiService.apiService.editInven(invenId,inventoryPostModel).enqueue(new Callback<InventoryDetailResData>() {
                            @Override
                            public void onResponse(Call<InventoryDetailResData> call, Response<InventoryDetailResData> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(AssetDetail.this,"Sửa thành công", Toast.LENGTH_SHORT).show();
                                    finish();
                                    Intent finishintent=new Intent(AssetDetail.this, InventoryDetail.class);
                                    finishintent.putExtra("invenId",inventory.getId());
                                    startActivity(finishintent);
                                }
                            }

                            @Override
                            public void onFailure(Call<InventoryDetailResData> call, Throwable t) {
                                Toast.makeText(AssetDetail.this,"Sửa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<InventoryDetailResData> call, Throwable t) {
                        Toast.makeText(AssetDetail.this,"Fetch Inventory Failed", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });
    }
}