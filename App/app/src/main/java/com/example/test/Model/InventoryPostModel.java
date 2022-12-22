package com.example.test.Model;

public class InventoryPostModel {
    private String invenId;
    private String baseId;
    private String unitId;

    public InventoryPostModel(String invenId, String baseId, String unitId) {
        this.invenId = invenId;
        this.baseId = baseId;
        this.unitId = unitId;
    }
    public String getInvenId() {
        return invenId;
    }

    public void setInvenId(String invenId) {
        this.invenId = invenId;
    }

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }
}
