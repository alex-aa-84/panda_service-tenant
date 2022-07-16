package wwf.org.tenant.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public SubModules createSubModules(SubModules staffModule) {
        staffModule.setStatus("CREATED");
        staffModule.setCreation_date(new Date());
        staffModule.setLast_update_date(new Date());

        return subModuleRepository.save(staffModule);
    }

    @Override
    public SubModules updateSubModules(SubModules staffModule) {
        SubModules staffModuleDB = getSubModules(staffModule.getId());
        if(null == staffModuleDB){
            return null;
        }

        staffModuleDB.setModule(staffModule.getModule());
        staffModuleDB.setSubmodule(staffModule.getSubmodule());
        staffModuleDB.setDescription(staffModule.getDescription());

        staffModuleDB.setAttribute1(staffModule.getAttribute1());
        staffModuleDB.setAttribute2(staffModule.getAttribute2());
        staffModuleDB.setAttribute3(staffModule.getAttribute3());
        staffModuleDB.setAttribute4(staffModule.getAttribute4());
        staffModuleDB.setAttribute5(staffModule.getAttribute5());
        staffModuleDB.setAttribute6(staffModule.getAttribute6());
        staffModuleDB.setAttribute7(staffModule.getAttribute7());
        staffModuleDB.setAttribute8(staffModule.getAttribute8());
        staffModuleDB.setAttribute9(staffModule.getAttribute9());
        staffModuleDB.setAttribute10(staffModule.getAttribute10());

        staffModuleDB.setStatus(staffModule.getStatus());

        staffModuleDB.setLast_update_by(staffModule.getLast_update_by());
        staffModuleDB.setLast_update_date(new Date());

        return subModuleRepository.save(staffModuleDB);
    }

    @Override
    public Boolean deleteSubModules(Long id) {
        SubModules staffModuleDB = getSubModules(id);
        if(null == staffModuleDB){
            return false;
        }

        subModuleRepository.deleteById(id);
        return true;
    }

    @Override
    public SubModules findByModuleIdAndSubmodule(Long moduleId, String submodule) {
        return subModuleRepository.findByModuleIdAndSubmodule(moduleId, submodule);
    }

}
