package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.Tenant;
import wwf.org.tenant.repository.TenantRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService{

    @Autowired
    private TenantRepository tenantRepository;

    @Override
    public List<Tenant> listAllTenant() {
        return tenantRepository.findAll();
    }

    @Override
    public Tenant getTenant(Long id) {
        return tenantRepository.findById(id).orElse(null);
    }

    @Override
    public Tenant createTenant(Tenant tenant) {
        tenant.setStatus("CREATED");
        tenant.setCreation_date(new Date());
        tenant.setLast_update_date(new Date());
        return tenantRepository.save(tenant);
    }

    @Override
    public Tenant updateTenant(Tenant tenant) {
        Tenant tenantDB = getTenant(tenant.getId());

        if(null == tenantDB){
            return null;
        }

        tenant.setStatus("MODIFIED");
        tenant.setLast_update_date(new Date());

        return tenantRepository.save(tenant);
    }

    @Override
    public Tenant deleteTenant(Tenant tenant) {
        Tenant tenantDB = getTenant(tenant.getId());

        if(null == tenantDB){
            return null;
        }

        tenant.setStatus("MODIFIED");
        tenant.setLast_update_date(new Date());

        return tenantRepository.save(tenant);
    }

    @Override
    public List<Tenant> findByTenant(String tenant) {
        return tenantRepository.findByTenant(tenant);
    }
}
