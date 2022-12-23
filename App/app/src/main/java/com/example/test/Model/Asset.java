package com.example.test.Model;

import java.io.Serializable;
import java.util.List;

public class Asset implements Serializable {
    private String invenId;
    private String assetId;
    private Boolean isExist;
    private String status;
    private String description;
    private AssetDetailModel assets;

    public String getInvenId() {
        return invenId;
    }

    public void setInvenId(String invenId) {
        this.invenId = invenId;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public Boolean isExist() {
        return isExist;
    }

    public void setExist(Boolean exist) {
        isExist = exist;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AssetDetailModel getAssets() {
        return assets;
    }

    public void setAssets(AssetDetailModel assets) {
        this.assets = assets;
    }
}
