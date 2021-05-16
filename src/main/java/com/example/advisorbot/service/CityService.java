package com.example.advisorbot.service;

import com.example.advisorbot.entity.City;
import com.example.advisorbot.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


//    public void saveCity(String name, String reference) {
//        log.info("Creating new Entity...");
//        cityRepository.save(new City(name, reference));
//    }

    public List<City> findByName(String name){
        log.info("findByName(String " + name + ")");
        City city = cityRepository.findCityByName(name);

        if (city != null) {
            log.info("Cities with this name: \"" + name + "\" has been found");
            return Collections.singletonList(city);
        }

        log.info("Search for cities with similar names");
        List<City> cities = cityRepository.find10CitiesWithSimilarNames(name.substring(0, 3));
        log.info("Cities with similar names: " + cities);
        if (cities.size() == 0) {
            log.warn("No one cities found");
            return null;
        }
        return cities;
    }

    public List<City> findAll() {
        return (List<City>) cityRepository.findAll();
    }
}
