package com.example.advisorbot.utils;

import com.example.advisorbot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FormValidator {

    @Autowired
    private CityService cityService;

    public Map<String, String> cityAddFormValidate(String name, String description) {
        Map<String, String> errors = new HashMap<>();
        if (cityService.findByName(name) != null) {
            errors.put("not_unique_name", "Есть город с таким именем");
        }
        if (name.equals("")) {
            errors.put("empty_name", "Введите имя");
        }
        if (name.length() < 3) {
            errors.put("empty_name", "Введите имя");
        }
        if (description.equals("")) {
            errors.put("empty_description", "Введите описание");
        }
        return errors;
    }
}
