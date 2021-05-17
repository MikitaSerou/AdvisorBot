package com.example.advisorbot.controllers;

import com.example.advisorbot.entity.City;
import com.example.advisorbot.service.CityService;
import com.example.advisorbot.service.CountryService;
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
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private FormValidator formValidator;


    @GetMapping
    public String citiesListPage(Model model) {
        log.info("GET request /city");

        model.addAttribute("cityList", cityService.findAll());

        return "city/cities_list";
    }

    @GetMapping("/{id}")
    public String cityPage(@PathVariable Integer id, Model model) {
        log.info("GET request /city");

        model.addAttribute("city", cityService.findById(id));

        return "city/cities_list";
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> uploadProductImage(@RequestParam("id") Integer id) {
        log.info("POST request city/delete" +
                "[id: " + id + "]");

        return cityService.deleteCityById(id);
    }

    @GetMapping("/new")
    public String cityPage(Model model) {
        log.info("GET request /city/new");
        log.info("GET request /city/new " + model);
        model.addAttribute("countries", countryService.findAll());

        return "city/new_city";
    }

    @PostMapping(value = "/new")
    public String addNewCity(@RequestParam(defaultValue = "") String name,
                             @RequestParam(defaultValue = "") String description,
                             @RequestParam Integer countryId,
                             @RequestParam(defaultValue = "false") Boolean isCapital,
                             Model model) {
        log.info("POST request /city/new" +
                "[name: " + name +
                ", description: " + description +
                ", countryId: " + countryId +
                ", isCapital: " + isCapital + "]");

        Map<String, String> errors = formValidator.cityAddFormValidate(name, description);
        if (!errors.isEmpty()) {
            for (String errorCode :
                    errors.keySet()) {
                model.addAttribute(errorCode, errors.get(errorCode));
            }
            return cityPage(model);
        }

        cityService.saveCity(new City(name, description, countryService.findById(countryId), isCapital));

        return "redirect:/city";
    }
}
