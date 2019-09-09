package com.lambda.countries;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;

@RestController
@RequestMapping("data/names")
public class NameController
{
    // localhost:2019/data/names/all
    // return the names of all the countries alphabetically
    @GetMapping(value = "/all", produces = {"application/json"})
    public ResponseEntity<?> getAllEmployees()
    {
        ArrayList<Country> countryRtn = CountriesApplication.ourCountryList.countryList;
        countryRtn.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(countryRtn, HttpStatus.OK);
    }

    // localhost:2019/data/names/start/s
    // return the countries alphabetically that have a name equal to or longer than the given length

    @GetMapping(value = "/start/{letter}", produces = {"application/json"})
    public ResponseEntity<?> getByFirstLetter(@PathVariable char letter)
    {
        ArrayList<Country> countryRtn = CountriesApplication.ourCountryList.findCountries(c -> c.getName().toUpperCase().charAt(0) == Character.toUpperCase(letter));
        countryRtn.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(countryRtn, HttpStatus.OK);
    }

    // localhost:2019/data/names/size/12
    // return the countries alphabetically that have a name equal to or longer than the given length

    @GetMapping(value = "/size/{number}", produces = {"application/json"})
    public ResponseEntity<?> getCountriesByNameLength(@PathVariable int number)
    {
        ArrayList<Country> countryRtn = CountriesApplication.ourCountryList.findCountries(c -> c.getName().length() >= number);
        countryRtn.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(countryRtn, HttpStatus.OK);
    }
}
