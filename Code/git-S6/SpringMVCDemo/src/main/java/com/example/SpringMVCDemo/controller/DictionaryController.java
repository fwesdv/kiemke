package com.example.SpringMVCDemo.controller;
import java.io.File;
import java.io.IOException;
import java.util.HashMap; import java.util.Map;

import com.example.SpringMVCDemo.model.DictionaryModel;
import com.example.SpringMVCDemo.model.Word;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/dicts")
public class DictionaryController {
    private DictionaryModel dictionaryModel = new DictionaryModel();

    @RequestMapping
    public String index(ModelMap model) {

        model.addAttribute("message", "Welcome to Our Dictionary!!!");
        return "dicts";
    }

    @RequestMapping(value = "/list")
    public String list( ModelMap model) {
        Map<String, String> dictionary = dictionaryModel.getDictionary();
        model.addAttribute("words", dictionary);
        return "dicts-list";
    }
    @RequestMapping(value = "/list/{word}")
    public String details(ModelMap model, @PathVariable(value = "word") String word) {
        String message = dictionaryModel.getDictionary().get(word);
        if (message == null)
        message = "Không có từ này!!!";
        model.addAttribute("message", message);
        return "dicts-detail";
    }
    @RequestMapping(value = "/search")
    public String list(ModelMap model, @RequestParam(value = "word") String word) {
        Map<String, String> res = new HashMap<>();
        String des = dictionaryModel.getDictionary().get(word);
        if (des != null)
            res.put(word, des);
        model.addAttribute("words", res);
        return "dicts-list";
    }
    @RequestMapping(value = "/add")
    public String addWordView(ModelMap model) {
        Word w = new Word();
        model.addAttribute("word", w);
        return "dicts-add-word";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addWordProcess(ModelMap model, @ModelAttribute(value = "word") Word newWord, HttpServletRequest request) {
        if (dictionaryModel.getDictionary().get(newWord.getWord()) == null) {
            MultipartFile img = newWord.getImg();
             String rootDir = request.getSession()
                .getServletContext().getRealPath("/");
            if (img != null && !img.isEmpty()) {
                try {
                img.transferTo(new File(rootDir + "resources/images/" + newWord.getWord() + ".png"));
                } catch (IOException | IllegalStateException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        dictionaryModel.getDictionary().put(newWord.getWord(), newWord.getDescription());
        return "redirect:/dicts/list";
        } else {
            model.addAttribute("message", "Từ đã tồn tại!!!");
            return "dicts-add-word";
        }
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields("word", "description");
    }


}

