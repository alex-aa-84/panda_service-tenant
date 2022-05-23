package wwf.org.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.TenantModule;
import wwf.org.tenant.entity.User;

public interface TenantModuleRepository extends JpaRepository<TenantModule, Long> {

}
