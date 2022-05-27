package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wwf.org.tenant.entity.AdministrativeUnit;
import wwf.org.tenant.entity.AdministrativeUnit;
import wwf.org.tenant.service.AdministrativeUnitService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"${settings.cors_origin}"})
@RestController
@RequestMapping(value="/adminunits")
public class AdministrativeUnitController {

    @Autowired
    private AdministrativeUnitService administrativeUnitService;

    private FormatMessage formatMessage = new FormatMessage();

    @GetMapping
    public ResponseEntity<List<AdministrativeUnit>> listAdministrativeUnit(){
        List<AdministrativeUnit> adminunits = administrativeUnitService.listAllAdministrativeUnit();
        if(adminunits.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(adminunits);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AdministrativeUnit> getAdministrativeUnit(@PathVariable("id") Long id){
        AdministrativeUnit adminunit = administrativeUnitService.getAdministrativeUnit(id);
        if(null == adminunit){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(adminunit);
    }

    @PostMapping()
    public ResponseEntity<AdministrativeUnit> createAdministrativeUnit(@Valid @RequestBody AdministrativeUnit administrativeUnit, BindingResult result){

        AdministrativeUnit administrativeUnitBD = administrativeUnitService.findByAdministrative_unit(administrativeUnit.getAdministrative_unit());

        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        if (null != administrativeUnitBD){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Unidad Administrativa existentes en la BD.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(administrativeUnitService.createAdministrativeUnit(administrativeUnit));
    }

    @PutMapping()
    public ResponseEntity<AdministrativeUnit> updateAdministrativeUnit(@Valid @RequestBody AdministrativeUnit administrativeUnit, BindingResult result){

        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        AdministrativeUnit administrativeUnitDB = administrativeUnitService.updateAdministrativeUnit(administrativeUnit);
        if(null == administrativeUnitDB){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(administrativeUnitDB);
    }


}
