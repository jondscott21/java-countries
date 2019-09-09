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
@RequestMapping("data/age")
public class AgeController
{
    // localhost:2019/data/age/age/{age}
    // return the countries that have a median age equal to or greater than the given age
    @GetMapping(value = "/age/{age}", produces = {"application/json"})
    public ResponseEntity<?> getCountryGreaterThanAgeEntered(@PathVariable
                                                                     int age)
    {
        ArrayList<Country> countryRtn = CountriesApplication.ourCountryList.findCountries(c -> c.getMedianAge() >= age);
        countryRtn.sort((c1, c2) -> c2.getMedianAge() - c1.getMedianAge());
        return new ResponseEntity<>(countryRtn, HttpStatus.OK);
    }

    // localhost:2019/data/age/min
    // return the country with the smallest median age
    @GetMapping(value = "/min", produces = {"application/json"})
    public ResponseEntity<?> getCountryWithLowestAge()
    {
        ArrayList<Country> countryRtn = CountriesApplication.ourCountryList.countryList;
        countryRtn.sort(Comparator.comparingInt(Country::getMedianAge));
        return new ResponseEntity<>(countryRtn.get(0), HttpStatus.OK);
    }

    // localhost:2019/data/age/max
    // return the country the the greatest median age
    @GetMapping(value = "/max", produces = {"application/json"})
    public ResponseEntity<?> getCountryWithHighestAge()
    {
        ArrayList<Country> countryRtn = CountriesApplication.ourCountryList.countryList;
        countryRtn.sort((c1, c2) -> c2.getMedianAge() - c1.getMedianAge());
        return new ResponseEntity<>(countryRtn.get(0), HttpStatus.OK);
    }

    //Stretch 2
    // localhost:2019/data/age/median
    //return the country with the median median age
    @GetMapping(value = "/median", produces = {"application/json"})
    public ResponseEntity<?> geMedianAge()
    {
        ArrayList<Country> countryRtn = CountriesApplication.ourCountryList.countryList;
        countryRtn.sort((c1, c2) -> ((int) (c2.getMedianAge() - c1.getMedianAge())));
        if(countryRtn.size() % 2 == 0)
        {
            return new ResponseEntity<>(countryRtn.get((countryRtn.size()/2) + 1), HttpStatus.OK);
        }
        return new ResponseEntity<>(countryRtn.get((countryRtn.size()/2)), HttpStatus.OK);
    }
}
