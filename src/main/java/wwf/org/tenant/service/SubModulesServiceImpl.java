package wwf.org.tenant.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.Module;
import wwf.org.tenant.entity.SubModules;
import wwf.org.tenant.repository.SubModulesRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubModulesServiceImpl implements SubModulesService{

    @Autowired
    private SubModulesRepository subModuleRepository;

    @Override
    public List<SubModules> listAllSubModules() {
        return subModuleRepository.findAll();
    }

    @Override
    public SubModules getSubModules(Long id) {
        return subModuleRepository.findById(id).orElse(null);
    }

    @Override
    public SubModules createSubModules(SubModules submodules) {
        submodules.setStatus("CREATED");
        submodules.setCreation_date(new Date());
        submodules.setLast_update_date(new Date());

        return subModuleRepository.save(submodules);
    }

    @Override
    public SubModules updateSubModules(SubModules submodules) {
        SubModules submodulesDB = getSubModules(submodules.getId());
        if(null == submodulesDB){
            return null;
        }

        submodulesDB.setModule(submodules.getModule());
        submodulesDB.setSubmodule(submodules.getSubmodule());
        submodulesDB.setRouterLink(submodules.getRouterLink());
        submodulesDB.setDescription(submodules.getDescription());
        submodulesDB.setOrder(submodules.getOrder());

        submodulesDB.setAttribute1(submodules.getAttribute1());
        submodulesDB.setAttribute2(submodules.getAttribute2());
        submodulesDB.setAttribute3(submodules.getAttribute3());
        submodulesDB.setAttribute4(submodules.getAttribute4());
        submodulesDB.setAttribute5(submodules.getAttribute5());
        submodulesDB.setAttribute6(submodules.getAttribute6());
        submodulesDB.setAttribute7(submodules.getAttribute7());
        submodulesDB.setAttribute8(submodules.getAttribute8());
        submodulesDB.setAttribute9(submodules.getAttribute9());
        submodulesDB.setAttribute10(submodules.getAttribute10());

        submodulesDB.setStatus(submodules.getStatus());

        submodulesDB.setLast_update_by(submodules.getLast_update_by());
        submodulesDB.setLast_update_date(new Date());

        return subModuleRepository.save(submodulesDB);
    }

    @Override
    public Boolean deleteSubModules(Long id) {
        SubModules submodulesDB = getSubModules(id);
        if(null == submodulesDB){
            return false;
        }

        subModuleRepository.deleteById(id);
        return true;
    }

    @Override
    public SubModules findByModuleIdAndSubmodule(Long moduleId, String submodule) {
        return subModuleRepository.findByModuleIdAndSubmodule(moduleId, submodule);
    }

    @Override
    public List<SubModules> findByModule(Module module) {
        return subModuleRepository.findByModule(module);
    }
}
