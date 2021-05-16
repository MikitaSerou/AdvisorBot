package com.example.advisorbot.service;

import com.example.advisorbot.entity.Country;
import com.example.advisorbot.repository.CityRepository;
import com.example.advisorbot.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    public List<Country> findAll() {
        return (List<Country>) countryRepository.findAll();
    }


    public Country findById(Integer id) {
        Optional<Country> country = countryRepository.findById(id);
        log.info("findById(Integer " + id + ")");
        country.ifPresentOrElse(c -> {
                    log.info("Country with this id: \"" + id + "\" has been found");
                },
                () -> log.error("Country with this id: " + id + " is not exist."));

        return country.orElse(null);
    }

    public void saveCountry(Country country) {
        log.info("Creating new Entity(" + country + ")...");
        countryRepository.save(country);
    }
}
