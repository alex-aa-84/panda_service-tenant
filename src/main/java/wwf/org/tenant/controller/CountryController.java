package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wwf.org.tenant.entity.Country;
import wwf.org.tenant.service.CountryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/countrys")
public class CountryController { 

    @Autowired
    private CountryService countryService;

    private FormatMessage formatMessage = new FormatMessage();

    @GetMapping
    public ResponseEntity<List<Country>> listCountry(){
        List<Country> countrys = countryService.listAllCountry();
        if(countrys.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(countrys);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable("id") Long id){
        Country country = countryService.getCountry(id);
        if(null == country){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(country);
    }

    @PostMapping()
    public ResponseEntity<Country> createCountry(@Valid @RequestBody Country country, BindingResult result){

        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        Country countryCreate =  countryService.createCountry(country);
        return ResponseEntity.status(HttpStatus.CREATED).body(countryCreate);
    }

    @PutMapping()
    public ResponseEntity<Country> updateCountry(@Valid @RequestBody Country country, BindingResult result){

        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        Country countryDB = countryService.updateCountry(country);
        if(null == countryDB){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(countryDB);
    }

    @DeleteMapping()
    public ResponseEntity<Country> deleteCountry(@RequestBody Country country){
        Country countryDB = countryService.deleteCountry(country);
        if(null == countryDB){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(countryDB);
    }

}
