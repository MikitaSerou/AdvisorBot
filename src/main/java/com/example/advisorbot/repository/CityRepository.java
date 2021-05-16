package com.example.advisorbot.repository;

import com.example.advisorbot.entity.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends PagingAndSortingRepository<City, Integer> {

    City findCityByName(String name);

    //select cities with similar names
    @Query("SELECT c FROM City c WHERE c.name lIKE %:halfOfName%")
    List<City> find10CitiesWithSimilarNames(@Param("halfOfName") String halfOfName);

}
