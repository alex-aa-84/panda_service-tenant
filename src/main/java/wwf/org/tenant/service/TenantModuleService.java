package wwf.org.tenant.service;

import wwf.org.tenant.entity.TenantModule;

import java.util.List;

public interface TenantModuleService {

    public List<TenantModule> listAllTenantModule();
    public TenantModule getTenantModule(Long id);

    public TenantModule createTenantModule(TenantModule tenantModule);
    public TenantModule updateTenantModule(TenantModule tenantModule);
}
