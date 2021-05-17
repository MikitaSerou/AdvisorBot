package com.example.advisorbot.controllers;


import com.example.advisorbot.entity.Country;
import com.example.advisorbot.service.CountryService;
import com.example.advisorbot.service.CurrencyService;
import com.example.advisorbot.utils.FormValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private FormValidator formValidator;


    @GetMapping
    public String countriesListPage(Model model) {
        log.info("GET request /country");
        model.addAttribute("countriesList", countryService.findAll());
        System.out.println(countryService.findAll());

        return "country/countries_list";
    }

    @GetMapping("/{id}")
    public String citiesOfCountry(@PathVariable Integer id, Model model) {
        log.info("GET request /country/" + id);
        Country country = countryService.findById(id);
        model.addAttribute("country", country);
        model.addAttribute("citiesOfCountry", country.getCities());

        return "country/country_cities";
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCountry(@RequestParam("id") Integer id) {
        log.info("POST request country/delete" +
                "[id: " + id + "]");

        return countryService.deleteCountryById(id);
    }

    @GetMapping("/new")
    public String countriesPage(Model model) {
        log.info("GET request /country/new");
        log.info("GET request /country/new " + model);
        model.addAttribute("currencies", currencyService.findAll());

        return "country/new_country";
    }

    @PostMapping(value = "/new")
    public String addNewCountry(@RequestParam(defaultValue = "") String name,
                                @RequestParam(defaultValue = "") String abbreviation,
                                @RequestParam Integer currencyId,
                                Model model) {
        log.info("POST request /country/new" +
                "[name: " + name +
                ", abbreviation: " + abbreviation +
                ", currencyId: " + currencyId + "]");

        Map<String, String> errors = formValidator.countryAddFormValidate(name, abbreviation);
        if (!errors.isEmpty()) {
            for (String errorCode :
                    errors.keySet()) {
                model.addAttribute(errorCode, errors.get(errorCode));
            }
            return countriesPage(model);
        }

        countryService.saveCountry(new Country(name, abbreviation, currencyService.findById(currencyId)));

        return "redirect:/country";
    }
}
