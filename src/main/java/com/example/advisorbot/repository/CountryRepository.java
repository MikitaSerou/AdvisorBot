package com.example.advisorbot.repository;

import com.example.advisorbot.entity.Country;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends PagingAndSortingRepository<Country, Integer> {

Country findByName(String name);
}
