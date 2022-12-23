package com.example.test.Model;


import java.util.ArrayList;
import java.util.Date;

public class Inventory {
    public ArrayList<Asset> inventoryAssetsList ;
    public String id;
    public Date date;
    public String invenUser;
    public String invenBase;
    public String invenUnit;
    public Boolean isFinish;

    public Inventory(String id, Date date, String invenUser, String invenBase, String invenUnit) {
        this.id = id;
        this.date = date;
        this.invenUser = invenUser;
        this.invenBase = invenBase;
        this.invenUnit = invenUnit;
    }

    public Inventory(ArrayList<Asset> inventoryAssetsList, String id, Date date, String invenUser, String invenBase, String invenUnit, Boolean isFinish) {
        this.inventoryAssetsList = inventoryAssetsList;
        this.id = id;
        this.date = date;
        this.invenUser = invenUser;
        this.invenBase = invenBase;
        this.invenUnit = invenUnit;
        this.isFinish = isFinish;
    }

    public Boolean getFinish() {
        return isFinish;
    }

    public void setFinish(Boolean finish) {
        isFinish = finish;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInvenUser() {
        return invenUser;
    }

    public void setInvenUser(String invenUser) {
        this.invenUser = invenUser;
    }

    public String getInvenBase() {
        return invenBase;
    }

    public void setInvenBase(String invenBase) {
        this.invenBase = invenBase;
    }

    public String getInvenUnit() {
        return invenUnit;
    }

    public void setInvenUnit(String invenUnit) {
        this.invenUnit = invenUnit;
    }

    public ArrayList<Asset> getInventoryAssetsList() {
        return inventoryAssetsList;
    }

    public void setInventoryAssetsList(ArrayList<Asset> inventoryAssetsList) {
        this.inventoryAssetsList = inventoryAssetsList;
    }
}
