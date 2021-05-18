package com.example.advisorbot.service;

import com.example.advisorbot.entity.Country;
import com.example.advisorbot.entity.Currency;
import com.example.advisorbot.repository.CountryRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CountryService.class);
    @Autowired
    private CountryRepository countryRepository;


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

    public Country findByName(String name) {
        log.info("findByName(String " + name + ")");
        Country country = countryRepository.findByName(name.toUpperCase());

        if (country != null) {
            log.info("Country with this name: \"" + name + "\" has been found: " + country.toString());
            return country;
        } else {
            log.error("Country with name (" + name + ") is not exist");
            return null;
        }
    }

    public void saveCountry(Country country) {
        log.info("saveCountry(Country " + country + ")");
        if (country.getCurrency() != null && country.getAbbreviation() != null &&
                country.getName() != null) {
            log.info("Creating new Entity(" + country + ")...");
            countryRepository.save(country);
        } else {
            log.error("Not all necessary fields defined");
        }
    }

    @Transactional
    public void updateCountry(Integer id, String newName, String newAbbreviation, Currency newCurrency) {
        log.info("updateCountry(Integer " + id +
                ", String " + newName +
                ", String " + newAbbreviation +
                ", Currency " + newCurrency + ")");
        Country countryForUpdate = findById(id);
        if (countryForUpdate != null) {
            log.info("Edit country: " + countryForUpdate.toString());
            if (!newName.equals("") && !countryForUpdate.getName().equals(newName)) {
                countryForUpdate.setName(newName.toUpperCase());
            }
            if (!newAbbreviation.equals("") &&
                    !countryForUpdate.getAbbreviation().equals(newAbbreviation)
            ) {
                countryForUpdate.setAbbreviation(newAbbreviation);
            }
            if (!newCurrency.equals(countryForUpdate.getCurrency())) {
                countryForUpdate.setCurrency(newCurrency);
            }
            log.info("Edit and save country: " + countryForUpdate.toString());
            countryRepository.save(countryForUpdate);
        } else {
            log.error("Country with this id (" + id + ") is not exist");
        }
    }

    public ResponseEntity<String> deleteCountryById(Integer id) {
        log.info("deleteCountryById(Integer " + id + ")");
        if (id == null) {
            log.error("Country id (" + id + ") is not defined");
            return new ResponseEntity<>("Invalid Id: " + id, HttpStatus.BAD_REQUEST);
        } else {
            if (!countryRepository.existsById(id)) {
                log.error("Country with this id (" + id + ") is not exist");
                return new ResponseEntity<>("No country with this id:" + id, HttpStatus.BAD_REQUEST);
            }
        }
        log.info("Delete country with id: " + id);
        countryRepository.deleteById(id);

        return new ResponseEntity<>("Country deleted successfully.", HttpStatus.OK);
    }
}
