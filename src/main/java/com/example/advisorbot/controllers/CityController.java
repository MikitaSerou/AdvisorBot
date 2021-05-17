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

    @GetMapping("/new")
    public String newCityPage(Model model) {
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

        Map<String, String> errors = formValidator.cityFormValidate(name, description, true);
        if (!errors.isEmpty()) {
            for (String errorCode :
                    errors.keySet()) {
                model.addAttribute(errorCode, errors.get(errorCode));
            }
            return newCityPage(model);
        }

        cityService.saveCity(new City(name.toUpperCase(), description, countryService.findById(countryId), isCapital));

        return "redirect:/city";
    }

    @GetMapping("/edit/{id}")
    public String cityEditPage(@PathVariable Integer id, Model model) {
        log.info("GET request /city/edit/" + id);

        model.addAttribute("city", cityService.findById(id));
        model.addAttribute("countries", countryService.findAll());

        return "city/city_edit";
    }


    @PostMapping(value = "/edit/{id}")
    public String editCity(@PathVariable("id") Integer id,
                           @RequestParam(defaultValue = "") String newName,
                           @RequestParam(defaultValue = "") String newDescription,
                           @RequestParam(defaultValue = "") Integer newCountryId,
                           @RequestParam(defaultValue = "") Boolean newCapitalStatus,
                           Model model) {
        log.info("POST request city/edit/" + id +
                "[id: " + id +
                ", newName: " + newName +
                ", newDescription: " + newDescription +
                ", newCountryId: " + newCountryId +
                ", newCapitalStatus: " + newCapitalStatus + "]");

        Map<String, String> errors = formValidator.cityFormValidate(newName, newDescription, false);
        if (!errors.isEmpty()) {
            for (String errorCode :
                    errors.keySet()) {
                model.addAttribute(errorCode, errors.get(errorCode));
            }
            return cityEditPage(id, model);
        }

        cityService.updateCity(id, newName, newDescription, countryService.findById(newCountryId), newCapitalStatus);

        return "redirect:/city";
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCity(@RequestParam("id") Integer id) {
        log.info("POST request city/delete" +
                "[id: " + id + "]");

        return cityService.deleteCityById(id);
    }
}
