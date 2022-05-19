package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.AdministrativeUnit;
import wwf.org.tenant.repository.AdministrativeUnitRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministrativeUnitServiceImpl implements AdministrativeUnitService{

    @Autowired
    private AdministrativeUnitRepository administrativeUnitRepository;

    @Override
    public List<AdministrativeUnit> listAllAdministrativeUnit() {
        return administrativeUnitRepository.findAll();
    }

    @Override
    public AdministrativeUnit getAdministrativeUnit(Long id) {
        return administrativeUnitRepository.findById(id).orElse(null);
    }

    @Override
    public AdministrativeUnit createCountry(AdministrativeUnit administrativeUnit) {
        administrativeUnit.setStatus("CREATED");
        administrativeUnit.setCreation_date(new Date());
        administrativeUnit.setLast_update_date(new Date());

        return administrativeUnitRepository.save(administrativeUnit);
    }

    @Override
    public AdministrativeUnit updateCountry(AdministrativeUnit administrativeUnit) {
        AdministrativeUnit administrativeUnitDB = getAdministrativeUnit(administrativeUnit.getId());
        if(null == administrativeUnitDB){
            return null;
        }

        administrativeUnit.setStatus("MODIFIED");
        administrativeUnit.setLast_update_date(new Date());

        return administrativeUnitRepository.save(administrativeUnit);

    }

    @Override
    public AdministrativeUnit deleteCountry(AdministrativeUnit administrativeUnit) {
        AdministrativeUnit administrativeUnitDB = getAdministrativeUnit(administrativeUnit.getId());
        if(null == administrativeUnitDB){
            return null;
        }

        administrativeUnit.setStatus("DELETED");
        administrativeUnit.setLast_update_date(new Date());

        return administrativeUnitRepository.save(administrativeUnit);
    }
}
