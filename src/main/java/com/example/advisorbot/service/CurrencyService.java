package com.example.advisorbot.service;

import com.example.advisorbot.entity.Currency;
import com.example.advisorbot.repository.CurrencyRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CurrencyService.class);
    @Autowired
    private CurrencyRepository currencyRepository;

    public Currency findById(Integer id) {
        log.info("findById(Integer " + id + ")");
        Optional<Currency> currency = currencyRepository.findById(id);
        currency.ifPresentOrElse(c -> {
                    log.info("Currency with this id: \"" + id + "\" has been found");
                },
                () -> log.error("Currency with this id: " + id + " is not exist."));

        return currency.orElse(null);
    }

    public List<Currency> findAll(){
        return (List<Currency>) currencyRepository.findAll();
    }
}
