package com.example.test.Model;

public class Asset {
    private String invenId;
    private String assetId;
    private boolean isExist;
    private String status;
    private String description;

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

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
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
}
