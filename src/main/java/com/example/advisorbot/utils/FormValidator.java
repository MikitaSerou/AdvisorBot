package com.example.advisorbot.utils;

import com.example.advisorbot.service.CityService;
import com.example.advisorbot.service.CountryService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class FormValidator {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(FormValidator.class);

    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;


    public Map<String, String> cityFormValidate(String name, String description, Boolean addForm) {
        log.info("cityAddFormValidate(String" + name + "," + " String " + description + ")");
        Map<String, String> errors = new HashMap<>();
        if (addForm && cityService.findByName(name) != null) {
            log.error("There is a city with that name");
            errors.put("not_unique_name", "Есть город с таким названием");
        }
        if (name.equals("")) {
            log.error("City name not specified");
            errors.put("empty_name", "Введите название города");
        }
        if (!Pattern.matches("^[а-яА-ЯёЁa]+$", name)) {
            log.error("City name not Cyrillic");
            errors.put("not_cyrillic_name", "Только кириллица");
        }
        if (description.equals("")) {
            log.error("City description not entered");
            errors.put("empty_description", "Введите описание");
        }
        if (!Pattern.matches("^[а-яА-ЯёЁa]+$", description)) {
            log.error("City description not Cyrillic");
            errors.put("empty_description", "Только кириллица");
        }

        return errors;
    }

    public Map<String, String> countryFormValidate(String name, String abbreviation, Boolean addForm) {
        Map<String, String> errors = new HashMap<>();
        if (addForm && countryService.findByName(name) != null) {
            log.error("There is a country with that name");
            errors.put("not_unique_name", "Есть страна с таким названием");
        }
        if (name.equals("")) {
            log.error("Country name not specified");
            errors.put("empty_name", "Введите название страны");
        }
        if (!Pattern.matches("^[а-яА-ЯёЁa]+$", name)) {
            log.error("Country name not Cyrillic");
            errors.put("not_cyrillic_name", "Только кириллица");
        }
        if (abbreviation.equals("")) {
            log.error("Country abbreviation not specified");
            errors.put("empty_abbreviation", "Введите сокращение");
        }
        if (!Pattern.matches("[a-zA-Z]{2,3}", abbreviation)) {
            log.error("Entered non-latin text");
            errors.put("invalid_abbreviation", "Сокращение должно быть записано латиницей");
        }

        return errors;
    }
}
