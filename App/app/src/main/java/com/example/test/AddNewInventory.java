package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.Api.ApiService;
import com.example.test.Model.InventoryPostModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewInventory extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_moi);
        Button btnSave=findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText invenId=findViewById(R.id.ID_kiemKe);
                InventoryPostModel inventoryPostModel=new InventoryPostModel(invenId.getText().toString(),"ROYAL-PHULAM","ROYAL-CTY.HG");
                ApiService.apiService.addNewInven(inventoryPostModel).enqueue(new Callback<InventoryPostModel>() {
                    @Override
                    public void onResponse(Call<InventoryPostModel> call, Response<InventoryPostModel> response) {
                        Toast.makeText(AddNewInventory.this,"Thêm thành công", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<InventoryPostModel> call, Throwable t) {
                        Toast.makeText(AddNewInventory.this,"Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        Button btnBack=findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}