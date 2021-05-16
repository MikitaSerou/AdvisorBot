package com.example.advisorbot.service;

import com.example.advisorbot.entity.City;
import com.example.advisorbot.repository.CityRepository;
import com.example.advisorbot.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CityService {

    @Autowired
    private CityRepository cityRepository;


    public City findById(Integer id) {
        log.info("findById(Integer " + id + ")");
        Optional<City> city = cityRepository.findById(id);
        city.ifPresentOrElse(c -> {
                    log.info("City with this id: \"" + id + "\" has been found");
                },
                () -> log.error("City with this id: " + id + " is not exist."));

        return city.orElse(null);
    }

    public void saveCity(City city) {
        log.info("Creating new Entity...");
        cityRepository.save(city);
    }

    public void deleteCityById(Integer id) {
        log.info("deleteCityById(Integer " + id + ")");
        cityRepository.deleteById(id);
    }

    public List<City> findByName(String name) {
        log.info("findByName(String " + name + ")");
        City city = cityRepository.findCityByName(name);

        if (city != null) {
            log.info("Cities with this name: \"" + name + "\" has been found");
            return Collections.singletonList(city);
        }


        List<City> cities = null;
        if (name.length() >= 3) {
            log.info("Search for cities with similar names");
            cities = cityRepository.find10CitiesWithSimilarNames(name.substring(0, 3));
            log.info("Cities with similar names: " + cities);
        }

        if (cities!= null && cities.size() == 0) {
            log.warn("No one cities found");
            return null;
        }
        return cities;
    }

    public List<City> findAll() {
        return (List<City>) cityRepository.findAll();
    }
}
