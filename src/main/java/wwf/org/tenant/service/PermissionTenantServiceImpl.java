package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.PermissionTenant;
import wwf.org.tenant.repository.PermissionTenantRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionTenantServiceImpl implements PermissionTenantService{

    private PermissionTenantRepository permissionTenantRepository;

    @Override
    public List<PermissionTenant> listAllPermissionTenant() {
        return permissionTenantRepository.findAll();
    }

    @Override
    public PermissionTenant getPermissionTenant(Long id) {
        return permissionTenantRepository.findById(id).orElse(null);
    }

    @Override
    public PermissionTenant createPermissionTenant(PermissionTenant permission) {
        permission.setStatus("CREATED");
        permission.setCreation_date((Timestamp) new Date());
        permission.setLast_update_date((Timestamp) new Date());

        return permissionTenantRepository.save(permission);
    }

    @Override
    public PermissionTenant updatePermissionTenant(PermissionTenant permission) {
        PermissionTenant permissionTenantDB = getPermissionTenant(permission.getId());
        if(null == permissionTenantDB){
            return null;
        }

        permission.setStatus("MODIFIED");
        permission.setLast_update_date((Timestamp) new Date());

        return permissionTenantRepository.save(permission);
    }

    @Override
    public PermissionTenant deletePermissionTenant(PermissionTenant permission) {
        PermissionTenant permissionTenantDB = getPermissionTenant(permission.getId());
        if(null == permissionTenantDB){
            return null;
        }

        permission.setStatus("DELETED");
        permission.setLast_update_date((Timestamp) new Date());

        return permissionTenantRepository.save(permission);
    }
}
