package wwf.org.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.TenantModule;

public interface TenantModuleRepository extends JpaRepository<TenantModule, Long> {

}
