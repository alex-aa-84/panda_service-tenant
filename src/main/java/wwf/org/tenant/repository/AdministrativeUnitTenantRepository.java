package wwf.org.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.AdministrativeUnitTenant;

public interface AdministrativeUnitTenantRepository extends JpaRepository<AdministrativeUnitTenant, Long> {
}
