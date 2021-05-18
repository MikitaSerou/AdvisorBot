package com.example.advisorbot.service;

import com.example.advisorbot.entity.City;
import com.example.advisorbot.entity.Country;
import com.example.advisorbot.repository.CityRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CityService.class);
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
        log.info("saveCity(City " + city + ")");
        if (city.getCountry() != null && city.getIsCapital() != null &&
                city.getName() != null && city.getDescription() != null) {
            log.info("Creating new Entity(" + city + ")...");
            cityRepository.save(city);
        } else {
            log.error("Not all necessary fields defined");
        }
    }

    public List<City> findByName(String name) {
        log.info("findByName(String " + name + ")");
        City city = cityRepository.findCityByName(name.toUpperCase());

        if (city != null) {
            log.info("Cities with this name: \"" + name + "\" has been found");
            return Collections.singletonList(city);
        }

        List<City> cities = null;
        if (name.length() >= 3) {
            log.info("Search for cities with similar names");
            cities = cityRepository.findCitiesWithSimilarNames(name.substring(0, 3).toUpperCase());
            log.info("Cities with similar names: " + cities);
        }

        if (cities != null && cities.size() == 0) {
            log.warn("No one cities found");
            return null;
        }
        return cities;
    }

    @Transactional
    public void updateCity(Integer id, String newName, String newDescription, Country newCountry,
                           Boolean newCapitalStatus) {
        log.info("updateCity(Integer " + id +
                ", String " + newName +
                ", String " + newDescription +
                ", Country " + newCountry +
                ", Boolean " + newCapitalStatus + ")");

        City cityForUpdate = findById(id);
        if (cityForUpdate != null) {
            log.info("Edit city: " + cityForUpdate.toString());
            if (!newName.equals("") && !cityForUpdate.getName().equals(newName)) {
                cityForUpdate.setName(newName.toUpperCase());
            }
            if (!newDescription.equals("") && !cityForUpdate.getDescription().equals(newDescription)) {
                cityForUpdate.setDescription(newDescription);
            }
            if (!newCountry.equals(cityForUpdate.getCountry())) {
                cityForUpdate.setCountry(newCountry);
            }
            if (!newCapitalStatus.equals(cityForUpdate.getIsCapital())) {
                cityForUpdate.setIsCapital(newCapitalStatus);
            }
            log.info("Edit and save city: " + cityForUpdate.toString());
            cityRepository.save(cityForUpdate);
        } else {
            log.error("City with this id (" + id + ") is not exist");
        }
    }

    public List<City> findAll() {
        return (List<City>) cityRepository.findAll();
    }

    public ResponseEntity<String> deleteCityById(Integer id) {
        log.info("deleteCityById(Integer " + id + ")");
        if (id == null) {
            log.error("City id (" + id + ") is not defined");
            return new ResponseEntity<>("Invalid Id: " + id, HttpStatus.BAD_REQUEST);
        } else {
            if (!cityRepository.existsById(id)) {
                log.error("City with this id (" + id + ") is not exist");
                return new ResponseEntity<>("No city with this id:" + id, HttpStatus.BAD_REQUEST);
            }
        }
        log.info("Delete city with id: " + id);
        cityRepository.deleteById(id);

        return new ResponseEntity<>("City deleted successfully.", HttpStatus.OK);
    }
}
