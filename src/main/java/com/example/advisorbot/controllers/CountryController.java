package com.example.advisorbot.controllers;

import com.example.advisorbot.service.CityService;
import com.example.advisorbot.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/country")
public class CountryController {

    private final CityService cityService;

    private final CountryService countryService;

    @Autowired
    public CountryController(CityService cityService, CountryService countryService) {
        this.cityService = cityService;
        this.countryService = countryService;
    }

    @GetMapping
    public String coountriesListPage(Model model) {
        log.info("GET request /country");
        model.addAttribute("countriesList", countryService.findAll());

        return "country/countries_list";
    }


}
