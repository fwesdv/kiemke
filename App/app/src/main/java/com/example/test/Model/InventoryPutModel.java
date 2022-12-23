package com.example.test.Model;

public class InventoryPutModel {
    private String isFinish;
    private Asset asset;

    public InventoryPutModel(Asset asset) {
        this.asset = asset;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
