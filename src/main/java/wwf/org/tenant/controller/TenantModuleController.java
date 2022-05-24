package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wwf.org.tenant.entity.TenantModule;
import wwf.org.tenant.service.TenantModuleService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"${settings.cors_origin}"})
@RestController
@RequestMapping(value="/tenantmodules")
public class TenantModuleController {

    @Autowired
    private TenantModuleService tenantModuleService;

    private FormatMessage formatMessage = new FormatMessage();

    @GetMapping
    public ResponseEntity<List<TenantModule>> listTenantModules(){
        List<TenantModule> tenantmodules = tenantModuleService.listAllTenantModule();
        if(tenantmodules.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(tenantmodules);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TenantModule> getTenantModule(@PathVariable("id") Long id){
        TenantModule tenantmodule = tenantModuleService.getTenantModule(id);
        if(null == tenantmodule){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tenantmodule);
    }

    @PostMapping()
    public ResponseEntity<TenantModule> createTenantModule(@Valid @RequestBody TenantModule tenantModule, BindingResult result){
        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        TenantModule tenantModuleCreate = tenantModuleService.createTenantModule(tenantModule);
        if(null == tenantModuleCreate) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El Modulo ya existente en el inquilino.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(tenantModuleCreate);
    }

    @PutMapping()
    public ResponseEntity<TenantModule> updateTenantModule(@Valid @RequestBody TenantModule tenantModule, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        TenantModule tenantModuleDB = tenantModuleService.updateTenantModule(tenantModule);
        if(null == tenantModuleDB) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tenantModuleDB);
    }
}
