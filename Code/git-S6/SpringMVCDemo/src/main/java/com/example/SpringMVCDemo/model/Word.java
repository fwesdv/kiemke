package com.example.SpringMVCDemo.model;

import org.springframework.web.multipart.MultipartFile;

public class Word {
    private String word;
    private String description;
    private String note;
    public String getNode() {
        return note;
    }
    private MultipartFile img;
    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public void setNode(String word) {
        this.note = note;
    }
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}






