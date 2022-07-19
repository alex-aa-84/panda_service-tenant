package wwf.org.tenant.service;


import wwf.org.tenant.entity.Module;
import wwf.org.tenant.entity.SubModules;

import java.util.List;

public interface SubModulesService {
    public List<SubModules> listAllSubModules();
    public SubModules getSubModules(Long id);

    public SubModules createSubModules(SubModules subModules);
    public SubModules updateSubModules(SubModules subModules);
    public Boolean deleteSubModules(Long id);

    public SubModules findByModuleIdAndSubmodule(Long moduleId, String submodule);
    public List<SubModules> findByModule(Module module);
}
