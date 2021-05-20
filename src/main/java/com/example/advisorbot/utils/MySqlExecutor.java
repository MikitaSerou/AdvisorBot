package com.example.advisorbot.utils;

import com.example.advisorbot.service.CityService;
import com.example.advisorbot.service.CountryService;
import com.example.advisorbot.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptStatementFailedException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Component
@Slf4j
public class MySqlExecutor {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CityService cityService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CountryService countryService;

   // @PostConstruct
    public void initDataBase() {
        log.info("initDataBase()");
        log.info("Initialize database...");
        DataSourceInitializer initializer = new DataSourceInitializer();
        log.info(dataSource.toString());
        initializer.setDataSource(this.dataSource);
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        try {
            if (currencyService.findAll().isEmpty()) {
                databasePopulator.addScript(
                        new ClassPathResource("CITIESDB_PUBLIC_CURRENCY.sql"));
            }
            if (countryService.findAll().isEmpty()) {
                databasePopulator.addScript(
                        new ClassPathResource("CITIESDB_PUBLIC_COUNTRY.sql"));
            }
            if (cityService.findAll().isEmpty()) {
                databasePopulator.addScript(
                        new ClassPathResource("CITIESDB_PUBLIC_CITY.sql"));
                initializer.setDatabasePopulator(databasePopulator);
            }
            initializer.afterPropertiesSet();
        } catch (ScriptStatementFailedException e) {
            log.warn("Database already initialized.");
            e.printStackTrace();
        }
    }
}
