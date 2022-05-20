package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wwf.org.tenant.entity.Tenant;
import wwf.org.tenant.service.TenantService;

import javax.validation.Valid;
import java.util.List;

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

    @PostMapping()
    public ResponseEntity<Tenant> createTenant(@RequestBody Tenant tenant){
        //Tenant tenantCreate = tenantService.createTenant(tenant);
        return ResponseEntity.status(HttpStatus.CREATED).body(tenant);
    }

}
