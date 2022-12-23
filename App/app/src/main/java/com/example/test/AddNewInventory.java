package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.test.Api.ApiService;
import com.example.test.Model.BaseResponeData;
import com.example.test.Model.Bases;
import com.example.test.Model.InventoryPostModel;
import com.example.test.Model.Unit;
import com.example.test.Model.UnitResponeData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewInventory extends AppCompatActivity {
    List<Bases> basesLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_moi);

        fetchBase();

        Button btnSave=findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText invenId=findViewById(R.id.ID_kiemKe);
                Spinner base = (Spinner) findViewById(R.id.selectExist);
                String baseId = base.getSelectedItem().toString();
                Spinner unit = (Spinner) findViewById(R.id.selectUnit);
                String unitId = unit.getSelectedItem().toString();

                InventoryPostModel inventoryPostModel=new InventoryPostModel(invenId.getText().toString(),baseId,unitId);
                ApiService.apiService.addNewInven(inventoryPostModel).enqueue(new Callback<InventoryPostModel>() {
                    @Override
                    public void onResponse(Call<InventoryPostModel> call, Response<InventoryPostModel> response) {
                        Toast.makeText(AddNewInventory.this,"Thêm thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddNewInventory.this, HomePage.class));
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
    private void fetchBase(){
        ApiService.apiService.getAllBase().enqueue(new Callback<BaseResponeData>() {
            @Override
            public void onResponse(Call<BaseResponeData> call, Response<BaseResponeData> response) {
                if(response!=null && response.isSuccessful())
                {
                    basesLists=response.body().getData();
                    ArrayList<String> baseIdList=new ArrayList<>();
                    basesLists.forEach((n)->{
                        baseIdList.add(n.getId());
                    });
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddNewInventory.this,
                            android.R.layout.simple_spinner_item,
                            baseIdList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Spinner selectBase=findViewById(R.id.selectExist);
                    selectBase.setAdapter(adapter);
                }

            }

            @Override
            public void onFailure(Call<BaseResponeData> call, Throwable t) {
                Toast.makeText(AddNewInventory.this,"Fetch Base Failed", Toast.LENGTH_SHORT).show();
            }
        });
        ApiService.apiService.getAllUnit().enqueue(new Callback<UnitResponeData>() {
            @Override
            public void onResponse(Call<UnitResponeData> call, Response<UnitResponeData> response) {
                if(response!=null && response.isSuccessful()){
                    List<Unit> unitList=response.body().getData();
                    ArrayList<String> unitIdList=new ArrayList<>();
                    unitList.forEach((n)->{
                        unitIdList.add(n.getId());
                    });
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddNewInventory.this,
                            android.R.layout.simple_spinner_item,
                            unitIdList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Spinner selectUnit=findViewById(R.id.selectUnit);
                    selectUnit.setAdapter(adapter);
                }

            }
            @Override
            public void onFailure(Call<UnitResponeData> call, Throwable t) {
                Toast.makeText(AddNewInventory.this,"Fetch Unit Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void fetchUnit(){

    }
}