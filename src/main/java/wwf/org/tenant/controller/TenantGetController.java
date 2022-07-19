package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wwf.org.tenant.entity.Module;
import wwf.org.tenant.entity.SubModules;
import wwf.org.tenant.entity.Tenant;
import wwf.org.tenant.entity.TenantModule;
import wwf.org.tenant.service.ModuleService;
import wwf.org.tenant.service.SubModulesService;
import wwf.org.tenant.service.TenantModuleService;
import wwf.org.tenant.service.TenantService;

import java.util.List;

@CrossOrigin(origins = {"${settings.cors_origin}", "${settings.cors_origin_pro}"}, maxAge = 3600,
        allowedHeaders={"Origin", "X-Requested-With", "Content-Type", "Accept", "x-client-key", "x-client-token", "x-client-secret", "Authorization"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.DELETE, RequestMethod.PUT})
@RestController
@RequestMapping(value="/tenants")
public class TenantGetController {

    @Autowired
    private TenantService tenantService;
    @Autowired
    private TenantModuleService tenantModuleService;

    @GetMapping
    public ResponseEntity<List<Tenant>> listTenant(@RequestHeader HttpHeaders headers){
        List<Tenant> tenants = tenantService.listAllTenant();
        if(tenants.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(tenants);
    }

    @GetMapping(value = "/{tenant}")
    public ResponseEntity<List<TenantModule>> getTenantModuleTenant(@PathVariable("tenant") String tenant){
        Tenant tenantDB = tenantService.findByTenant(tenant);
        List<TenantModule> tenantmodules = tenantModuleService.findByTenant(tenantDB);
        if(null == tenantmodules){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tenantmodules);
    }
}
