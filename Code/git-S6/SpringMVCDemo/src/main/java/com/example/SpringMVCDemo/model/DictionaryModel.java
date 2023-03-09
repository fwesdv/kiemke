package com.example.SpringMVCDemo.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DictionaryModel {
    private static final Map<String, String> DICTIONARY = new HashMap<>();
    static {
        DICTIONARY.put("Hello", "Xin chào");
        DICTIONARY.put("Love", "Yêu");
        DICTIONARY.put("Computer", "Máy tính");
        DICTIONARY.put("Remember", "Nhớ");
        DICTIONARY.put("Artificial", "Nhân tạo");
    }



    public Map<String, String> getDictionary() {
        return DICTIONARY;
    }


}
