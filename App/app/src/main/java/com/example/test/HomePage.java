package com.example.test;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.test.Adapter.ListKiemKeAdapter;
import com.example.test.Api.ApiService;
import com.example.test.Model.Inventory;
import com.example.test.Model.InventoryResponeData;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Inventory> inventories;
    ListKiemKeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giao_dien_ho_so);

        inventories=new ArrayList<>();
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);

        RecyclerView.ItemDecoration decoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchData();




    }
    private void fetchData()
    {
        ApiService.apiService.getAllInven().enqueue(new Callback<InventoryResponeData>() {
            @Override
            public void onResponse(Call<InventoryResponeData> call, Response<InventoryResponeData> response) {
                InventoryResponeData res=response.body();
                List<Inventory> list=new ArrayList<Inventory>(res.getData());
                inventories=list;
                adapter=new ListKiemKeAdapter(inventories);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<InventoryResponeData> call, Throwable t) {
                Toast.makeText(HomePage.this,"Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}