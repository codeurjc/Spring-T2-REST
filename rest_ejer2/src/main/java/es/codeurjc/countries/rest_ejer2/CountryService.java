package es.codeurjc.countries.rest_ejer2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryService {

    Logger logger = LoggerFactory.getLogger(CountryService.class);
    
    private static final String API = "https://restcountries.eu/rest/v2/";
    private RestTemplate restTemplate = new RestTemplate();

    public List<Country> getAllCountries() {
        
        Country[] res = restTemplate.getForObject(API + "all", Country[].class);

        return new ArrayList<Country>(Arrays.asList(res));
    }

    public List<Country> getByName(String name) {

        List<Country> countries = new ArrayList<>(); 

        try{
            Country[] res = restTemplate.getForObject(API + "name/" + name, Country[].class);
            countries = Arrays.asList(res);
        }catch(HttpClientErrorException ex){          
            logger.error(ex.getMessage());
        }

        return countries;
    }

    public List<Country> getByRegion(String region) {

        Country[] res = restTemplate.getForObject(API + "region/"+region, Country[].class);

        return new ArrayList<Country>(Arrays.asList(res));
    }


}
