package com.example.advisorbot.service;

import com.example.advisorbot.entity.Country;
import com.example.advisorbot.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public List<Country> findAll() {
        return (List<Country>) countryRepository.findAll();
    }
}
