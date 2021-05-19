package com.example.advisorbot.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
@Slf4j
public class MySqlExecutor {

    @Autowired
    DataSource dataSource;

    public void initDataBase() {
        DataSourceInitializer initializer = new DataSourceInitializer();
        System.err.println(dataSource.toString());
        log.info(dataSource.toString());
        initializer.setDataSource(this.dataSource);
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(
                new ClassPathResource("CITIESDB_PUBLIC_CURRENCY.sql"));
        databasePopulator.addScript(
                new ClassPathResource("CITIESDB_PUBLIC_COUNTRY.sql"));
        databasePopulator.addScript(
                new ClassPathResource("CITIESDB_PUBLIC_CITY.sql"));
        initializer.setDatabasePopulator(databasePopulator);

        initializer.afterPropertiesSet();
    }
}
