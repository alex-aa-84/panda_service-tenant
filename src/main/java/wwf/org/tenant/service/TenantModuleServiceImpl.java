package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.TenantModule;
import wwf.org.tenant.repository.TenantModuleRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TenantModuleServiceImpl implements TenantModuleService{

    @Autowired
    private TenantModuleRepository tenantModuleRepository;

    @Override
    public List<TenantModule> listAllTenantModule() {
        return tenantModuleRepository.findAll();
    }

    @Override
    public TenantModule getTenantModule(Long id) {
        return tenantModuleRepository.findById(id).orElse(null);
    }

    @Override
    public TenantModule createTenantModule(TenantModule tenantModule) {
        tenantModule.setStatus("CREATED");
        tenantModule.setCreation_date(new Date());
        tenantModule.setLast_update_date(new Date());
        return tenantModuleRepository.save(tenantModule);
    }

    @Override
    public TenantModule updateTenantModule(TenantModule tenantModule) {
        TenantModule tenantModuleDB = getTenantModule(tenantModule.getId());
        if(null == tenantModuleDB){
            return null;
        }
        tenantModule.setStatus("MODIFIED");
        tenantModule.setLast_update_date(new Date());
        return tenantModuleRepository.save(tenantModule);
    }

    @Override
    public TenantModule deleteTenantModule(TenantModule tenantModule) {
        TenantModule tenantModuleDB = getTenantModule(tenantModule.getId());
        if(null == tenantModuleDB){
            return null;
        }
        tenantModule.setStatus("DELETED");
        tenantModule.setLast_update_date(new Date());
        return tenantModuleRepository.save(tenantModule);
    }
}
