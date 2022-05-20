package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wwf.org.tenant.entity.Country;
import wwf.org.tenant.entity.Tenant;
import wwf.org.tenant.service.TenantService;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value="/tenants")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    private FormatMessage formatMessage = new FormatMessage();

    @GetMapping
    public ResponseEntity<List<Tenant>> listTenant(){
        List<Tenant> tenants = tenantService.listAllTenant();
        if(tenants.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(tenants);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Tenant> getTenant(@PathVariable("id") Long id){
        Tenant tenant = tenantService.getTenant(id);
        if(null == tenant){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tenant);
    }

    @GetMapping(value = "/tenant/{tenant}")
    public ResponseEntity<Tenant> getByTenant(@PathVariable("tenant") String tenant){
        Tenant tenantR = tenantService.findByTenant(tenant);
        if(null == tenantR){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tenantR);
    }

    @GetMapping(value = "/domain/{domain}")
    public ResponseEntity<Tenant> getByDomain(@PathVariable("domain") String domain){
        Tenant tenantR = tenantService.findByDomain(domain);
        if(null == tenantR){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tenantR);
    }

    @PostMapping()
    public ResponseEntity<Tenant> createTenant(@Valid @RequestBody Tenant tenant, BindingResult result){
        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        Tenant tenantCreate = tenantService.createTenant(tenant);

        if(null == tenantCreate){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tenant y/o Dominio existentes en la BD.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(tenantCreate);
    }

    @PutMapping()
    public ResponseEntity<Tenant> updateTenant(@Valid @RequestBody Tenant tenant, BindingResult result){

        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        Tenant tenantDB = tenantService.updateTenant(tenant);
        if(null == tenantDB){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tenantDB);
    }

    @DeleteMapping()
    public ResponseEntity<Tenant> deleteTenant(@RequestBody Tenant tenant){
        Tenant tenantDB = tenantService.deleteTenant(tenant);
        if(null == tenantDB){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tenantDB);
    }

}
