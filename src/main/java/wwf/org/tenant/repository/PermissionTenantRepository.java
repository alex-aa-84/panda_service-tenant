package wwf.org.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.Country;
import wwf.org.tenant.entity.PermissionTenant;
import wwf.org.tenant.entity.Submodule;

public interface PermissionTenantRepository extends JpaRepository<PermissionTenant, Long> {

    public PermissionTenant findByPermissions(String permissions);
}
