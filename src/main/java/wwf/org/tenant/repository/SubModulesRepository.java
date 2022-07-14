package wwf.org.tenant.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.SubModules;

public interface SubModulesRepository extends JpaRepository<SubModules, Long> {
    public SubModules findByModuleIdAndSubmodule(Long moduleId, String submodule);
}
