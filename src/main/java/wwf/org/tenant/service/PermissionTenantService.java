package wwf.org.tenant.service;

import wwf.org.tenant.entity.PermissionTenant;

import java.util.List;

public interface PermissionTenantService {

    public List<PermissionTenant> listAllPermissionTenant();
    public PermissionTenant getPermissionTenant(Long id);

    public PermissionTenant createPermissionTenant(PermissionTenant permission);
    public PermissionTenant updatePermissionTenant(PermissionTenant permission);
}
