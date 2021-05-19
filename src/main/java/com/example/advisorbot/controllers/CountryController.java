package com.example.advisorbot.controllers;


import com.example.advisorbot.entity.Country;
import com.example.advisorbot.service.CountryService;
import com.example.advisorbot.service.CurrencyService;
import com.example.advisorbot.utils.FormValidator;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/country")
public class CountryController {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CountryController.class);

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

        Map<String, String> errors = formValidator.countryFormValidate(name, abbreviation, true);
        if (!errors.isEmpty()) {
            for (String errorCode :
                    errors.keySet()) {
                model.addAttribute(errorCode, errors.get(errorCode));
            }
            return countriesPage(model);
        }

        countryService.saveCountry(new Country(name.toUpperCase(), abbreviation.toUpperCase(), currencyService.findById(currencyId)));

        return "redirect:/country";
    }

    @GetMapping("/edit/{id}")
    public String countryEditPage(@PathVariable Integer id, Model model) {
        log.info("GET request /country/edit/" + id);

        model.addAttribute("country", countryService.findById(id));
        model.addAttribute("currencies", currencyService.findAll());

        return "country/country_edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String editCountry(@PathVariable("id") Integer id,
                              @RequestParam(defaultValue = "") String newName,
                              @RequestParam(defaultValue = "") String newAbbreviation,
                              @RequestParam(defaultValue = "") Integer newCurrencyId,
                              Model model) {
        log.info("POST request country/edit/" + id +
                "[id: " + id +
                ", newName: " + newName +
                ", newAbbreviation: " + newAbbreviation +
                ", newCurrencyId: " + newCurrencyId + "]");

        Map<String, String> errors = formValidator.countryFormValidate(newName, newAbbreviation, false);
        if (!errors.isEmpty()) {
            for (String errorCode :
                    errors.keySet()) {
                model.addAttribute(errorCode, errors.get(errorCode));
            }
            return countryEditPage(id, model);
        }

        countryService.updateCountry(id, newName, newAbbreviation, currencyService.findById(newCurrencyId));

        return "redirect:/country";
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCountry(@RequestParam("id") Integer id) {
        log.info("POST request country/delete" +
                "[id: " + id + "]");

        return countryService.deleteCountryById(id);
    }
}
