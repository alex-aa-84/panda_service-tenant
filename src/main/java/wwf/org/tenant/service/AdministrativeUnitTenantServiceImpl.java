package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.AdministrativeUnitTenant;
import wwf.org.tenant.repository.AdministrativeUnitTenantRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministrativeUnitTenantServiceImpl implements AdministrativeUnitTenantService{

    @Autowired
    private AdministrativeUnitTenantRepository administrativeUnitTenantRepository;

    @Override
    public List<AdministrativeUnitTenant> listAllAdministrativeUnitTenant() {
        return administrativeUnitTenantRepository.findAll();
    }

    @Override
    public AdministrativeUnitTenant getAdministrativeUnitTenant(Long id) {
        return administrativeUnitTenantRepository.findById(id).orElse(null);
    }

    @Override
    public AdministrativeUnitTenant createAdministrativeUnitTenant(AdministrativeUnitTenant administrativeUnitTenant) {
        administrativeUnitTenant.setStatus("CREATED");
        administrativeUnitTenant.setCreation_date(new Date());
        administrativeUnitTenant.setLast_update_date(new Date());

        return administrativeUnitTenantRepository.save(administrativeUnitTenant);
    }

    @Override
    public AdministrativeUnitTenant updateAdministrativeUnitTenant(AdministrativeUnitTenant administrativeUnitTenant) {
        AdministrativeUnitTenant administrativeUnitTenantDB = getAdministrativeUnitTenant(administrativeUnitTenant.getId());

        if(null == administrativeUnitTenantDB){
            return null;
        }

        administrativeUnitTenant.setStatus("MODIFIED");
        administrativeUnitTenant.setLast_update_date(new Date());

        return administrativeUnitTenantRepository.save(administrativeUnitTenant);
    }

    @Override
    public AdministrativeUnitTenant deleteAdministrativeUnitTenant(AdministrativeUnitTenant administrativeUnitTenant) {
        AdministrativeUnitTenant administrativeUnitTenantDB = getAdministrativeUnitTenant(administrativeUnitTenant.getId());

        if(null == administrativeUnitTenantDB){
            return null;
        }

        administrativeUnitTenant.setStatus("DELETED");
        administrativeUnitTenant.setLast_update_date(new Date());

        return administrativeUnitTenantRepository.save(administrativeUnitTenant);
    }
}
