package wwf.org.tenant.service;

import wwf.org.tenant.entity.Tenant;

import java.util.List;

public interface TenantService {

    public List<Tenant> listAllTenant();
    public Tenant getTenant(Long id);

    public Tenant createTenant(Tenant tenant);
    public Tenant updateTenant(Tenant tenant);
    public Tenant deleteTenant(Long id, Long user_id);

    public Tenant findByTenant(String tenant);
    public Tenant findByDomain(String domain);
}
