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
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    private final CountryService countryService;

    @Autowired
    public CityController(CityService cityService, CountryService countryService) {
        this.cityService = cityService;
        this.countryService = countryService;
    }


    @GetMapping
    public String citiesListPage(Model model) {
        log.info("GET request /city");

        model.addAttribute("cityList", cityService.findAll());

        return "city/cities_list";
    }
}
