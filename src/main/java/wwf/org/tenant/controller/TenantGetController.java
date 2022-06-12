package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wwf.org.tenant.entity.Tenant;
import wwf.org.tenant.service.TenantService;

import java.util.List;

@CrossOrigin(origins = {"${settings.cors_origin}"}, maxAge = 3600,
        allowedHeaders={"Authorization"})

@RestController
@RequestMapping(value="/tenants")
public class TenantGetController {

    @Autowired
    private TenantService tenantService;

    @GetMapping
    public ResponseEntity<List<Tenant>> listTenant(@RequestHeader HttpHeaders headers){
        List<Tenant> tenants = tenantService.listAllTenant();
        if(tenants.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(tenants);
    }
}
