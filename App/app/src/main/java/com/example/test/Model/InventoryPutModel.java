package com.example.test.Model;

import java.util.List;

public class InventoryPutModel {
    private Boolean isFinish;
    private List<Asset> inventoryAsset;

    public InventoryPutModel(Boolean isFinish, List<Asset> inventoryAsset) {
        this.isFinish = isFinish;
        this.inventoryAsset = inventoryAsset;
    }
    public InventoryPutModel(Inventory inventory) {
        this.isFinish = inventory.getFinish();
        this.inventoryAsset = inventory.getInventoryAssetsList();
    }
    public InventoryPutModel(Inventory inventory, Boolean isFinish) {
        this.isFinish = isFinish;
        this.inventoryAsset = inventory.getInventoryAssetsList();
    }
    public Boolean getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Boolean isFinish) {
        this.isFinish = isFinish;
    }

    public List<Asset> getInventoryAsset() {
        return inventoryAsset;
    }

    public void setInventoryAsset(List<Asset> inventoryAsset) {
        this.inventoryAsset = inventoryAsset;
    }
}
