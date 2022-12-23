package com.example.test.Model;

import java.io.Serializable;

public class AssetDetailModel implements Serializable {
    private String id;
    private String name;
    private String oldAssetId;
    private String briefId;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldAssetId() {
        return oldAssetId;
    }

    public void setOldAssetId(String oldAssetId) {
        this.oldAssetId = oldAssetId;
    }

    public String getBriefId() {
        return briefId;
    }

    public void setBriefId(String briefId) {
        this.briefId = briefId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
