package wwf.org.tenant.service;

import wwf.org.tenant.entity.AdministrativeUnit;
import wwf.org.tenant.entity.Country;

import java.util.List;

public interface AdministrativeUnitService {

    public List<AdministrativeUnit> listAllAdministrativeUnit();
    public AdministrativeUnit getAdministrativeUnit(Long id);

    public AdministrativeUnit createAdministrativeUnit(AdministrativeUnit administrativeUnit);
    public AdministrativeUnit updateAdministrativeUnit(AdministrativeUnit administrativeUnit);
}
