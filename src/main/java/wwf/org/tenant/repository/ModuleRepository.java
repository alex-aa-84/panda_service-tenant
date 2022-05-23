package wwf.org.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.Module;
import wwf.org.tenant.entity.Submodule;

public interface ModuleRepository extends JpaRepository<Module, Long> {

}
