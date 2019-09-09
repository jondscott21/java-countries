package com.lambda.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("data/population")
public class PopulationController
{
    // localhost:2019/data/population/size/10000000
    // return the countries that have a population equal to or greater than the given population
    @GetMapping(value = "/size/{people}", produces = {"application/json"})
    public ResponseEntity<?> getCountriesByPopulationEntered(@PathVariable
                                                                     long people)
    {
        ArrayList<Country> countryRtn = CountriesApplication.ourCountryList.findCountries(c -> c.getPopulation() >= people);
        countryRtn.sort((c1, c2) -> ((int) (c2.getPopulation() - c1.getPopulation())));
        return new ResponseEntity<>(countryRtn, HttpStatus.OK);
    }


    // localhost:2019/data/population/min
    // return the country with the smallest population
    @GetMapping(value = "/min", produces = {"application/json"})
    public ResponseEntity<?> getCountryWithLowestPop()
    {
        ArrayList<Country> countryRtn = CountriesApplication.ourCountryList.countryList;
        countryRtn.sort((c1, c2) -> ((int) (c1.getPopulation() - c2.getPopulation())));
        return new ResponseEntity<>(countryRtn.get(0), HttpStatus.OK);
    }

    // localhost:2019/data/population/max
    // return the country with the largest population
    @GetMapping(value = "/max", produces = {"application/json"})
    public ResponseEntity<?> getCountryWithHighestPop()
    {
        ArrayList<Country> countryRtn = CountriesApplication.ourCountryList.countryList;
        countryRtn.sort((c1, c2) -> ((int) (c2.getPopulation() - c1.getPopulation())));
        return new ResponseEntity<>(countryRtn.get(0), HttpStatus.OK);
    }

    // Stretch
    // localhost:2019/data/population/median
    // return the country with the median population
    @GetMapping(value = "/median", produces = {"application/json"})
    public ResponseEntity<?> getCountryWithMedianPop()
    {
        ArrayList<Country> countryRtn = CountriesApplication.ourCountryList.countryList;
        countryRtn.sort((c1, c2) -> ((int) (c2.getPopulation() - c1.getPopulation())));
        if(countryRtn.size() % 2 == 0)
        {
            return new ResponseEntity<>(countryRtn.get((countryRtn.size()/2) + 1), HttpStatus.OK);
        }
        return new ResponseEntity<>(countryRtn.get((countryRtn.size()/2)), HttpStatus.OK);
    }
}
