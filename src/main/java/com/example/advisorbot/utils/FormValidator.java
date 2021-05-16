package com.example.advisorbot.utils;

import com.example.advisorbot.service.CityService;
import com.example.advisorbot.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
@Slf4j
public class FormValidator {

    @Autowired
    private CityService cityService;

    @Autowired
    CountryService countryService;


    public Map<String, String> cityAddFormValidate(String name, String description) {
        log.info("cityAddFormValidate(String" + name + "," + " String " + description + ")");
        Map<String, String> errors = new HashMap<>();
        if (cityService.findByName(name) != null) {
            log.error("There is a city with that name");
            errors.put("not_unique_name", "Есть город с таким названием");
        }
        if (name.equals("")) {
            log.error("City name not specified");
            errors.put("empty_name", "Введите название города");
        }
        if (description.equals("")) {
            log.error("City description not entered");
            errors.put("empty_description", "Введите описание");
        }
        return errors;
    }

    public Map<String, String> countryAddFormValidate(String name, String abbreviation) {
        Map<String, String> errors = new HashMap<>();
        if (cityService.findByName(name) != null) {
            log.error("There is a country with that name");
            errors.put("not_unique_name", "Есть страна с таким названием");
        }
        if (name.equals("")) {
            log.error("Country name not specified");
            errors.put("empty_name", "Введите название страны");
        }
        if (abbreviation.equals("")) {
            log.error("Country abbreviation not specified");
            errors.put("empty_abbreviation", "Введите сокращение");
        }
        if (!Pattern.matches("[a-zA-Z]{3}", abbreviation)) {
            log.error("Entered non-latin text");
            errors.put("invalid_abbreviation", "Сокращение должно быть записано латиницей");
        }

        return errors;
    }
}
