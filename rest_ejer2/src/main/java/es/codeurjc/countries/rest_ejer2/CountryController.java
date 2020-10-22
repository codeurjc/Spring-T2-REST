package es.codeurjc.countries.rest_ejer2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    public CountryService countryService;

    @GetMapping("/all")
    // http://localhost:8080/countries/all?region=Europe
    public List<Country> allCountriesByRegion(@RequestParam(required=false) String region) {

        if (region == null){
            return countryService.getAllCountries();
        }else{
            return countryService.getByRegion(region);
        }

    }

    @GetMapping("/population")
    // http://localhost:8080/countries/population?n=40000000
    public List<Country> allCountriesByPopulation(@RequestParam final long n) {
        return countryService.getAllCountries().stream().filter( c -> c.population > n).collect(Collectors.toList());
    }

    @GetMapping("/quiz")
    // http://localhost:8080/countries/quiz?countryName=Spain&capital=Madrid
    public ResponseEntity<String> quiz(@RequestParam String countryName, @RequestParam String capital) {

        List<Country> countries = countryService.getByName(countryName);

        if(countries.size() == 1){
            if(countries.get(0).capital.equals(capital)){
                return new ResponseEntity<>("Correct", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Fail", HttpStatus.OK);
            }
        }else if (countries.size() == 0){
            return new ResponseEntity<>("Country not found", HttpStatus.NOT_FOUND); 
        }else{
            return new ResponseEntity<>("Parameter 'countryName' match more than 1 country", HttpStatus.BAD_REQUEST);
        }
    }
    
}
