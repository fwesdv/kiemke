package com.example.test.Model;

import java.util.List;

public class BaseResponeData {
    private List<Bases> data;

    private String code;

    private String message;

    private Object pagination;

    public List<Bases> getData() {
        return data;
    }

    public void setData(List<Bases> data) {
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
