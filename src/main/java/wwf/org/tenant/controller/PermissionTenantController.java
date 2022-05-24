package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wwf.org.tenant.entity.PermissionTenant;
import wwf.org.tenant.service.PermissionTenantService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"${settings.cors_origin}"})
@RestController
@RequestMapping(value="/permissions")
public class PermissionTenantController {

    @Autowired
    private PermissionTenantService permissionTenantService;

    private FormatMessage formatMessage = new FormatMessage();

    @GetMapping
    public ResponseEntity<List<PermissionTenant>> listPermissionTenant(){
        List<PermissionTenant> permissions = permissionTenantService.listAllPermissionTenant();
        if(permissions.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(permissions);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PermissionTenant> getPermissionTenant(@PathVariable("id") Long id){
        PermissionTenant permission = permissionTenantService.getPermissionTenant(id);
        if(null == permission){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(permission);
    }

    @PostMapping()
    public ResponseEntity<PermissionTenant> createPermissionTenant(@Valid @RequestBody PermissionTenant permissionTenant, BindingResult result){
        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        PermissionTenant permissionTenantCreate = permissionTenantService.createPermissionTenant(permissionTenant);
        if(null == permissionTenantCreate) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Permiso existente en la BD.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(permissionTenantCreate);
    }

    @PutMapping()
    public ResponseEntity<PermissionTenant> updatePermissionTenant(@Valid @RequestBody PermissionTenant permissionTenant, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        PermissionTenant permissionTenantDB = permissionTenantService.updatePermissionTenant(permissionTenant);
        if(null == permissionTenantDB) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(permissionTenantDB);
    }
}
