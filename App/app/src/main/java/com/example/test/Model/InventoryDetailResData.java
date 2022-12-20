package com.example.test.Model;

public class InventoryDetailResData {
    private Inventory data;

    private String code;

    private String message;

    public Inventory getData() {
        return data;
    }

    public void setData(Inventory data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
