package wwf.org.tenant.service;

import wwf.org.tenant.entity.AdministrativeUnitTenant;

import java.util.List;

public interface AdministrativeUnitTenantService {

    public List<AdministrativeUnitTenant> listAllAdministrativeUnitTenant();
    public AdministrativeUnitTenant getAdministrativeUnitTenant(Long id);

    public AdministrativeUnitTenant createAdministrativeUnitTenant(AdministrativeUnitTenant administrativeUnitTenant);
    public AdministrativeUnitTenant updateAdministrativeUnitTenant(AdministrativeUnitTenant administrativeUnitTenant);
    public AdministrativeUnitTenant deleteAdministrativeUnitTenant(AdministrativeUnitTenant administrativeUnitTenant);


}
