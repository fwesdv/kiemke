package com.example.test.Model;

import java.util.List;

public class InventoryResponeData {

    private List<Inventory> data;

    private String code;

    private String message;

    private Object pagination;

    public List<Inventory> getData() {
        return data;
    }

    public void setData(List<Inventory> data) {
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

    public Object getPagination() {
        return pagination;
    }

    public void setPagination(Object pagination) {
        this.pagination = pagination;
    }
}
