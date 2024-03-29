package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.Module;
import wwf.org.tenant.repository.ModuleRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public List<Module> listAllModule() {
        return moduleRepository.findAll();
    }

    @Override
    public Module getModule(Long id) {
        return moduleRepository.findById(id).orElse(null);
    }

    @Override
    public Module createModule(Module module) {
        module.setStatus("CREATED");
        module.setCreation_date(new Date());
        module.setLast_update_date(new Date());

        return moduleRepository.save(module);
    }

    @Override
    public Module findByModule(String module) {
        return moduleRepository.findByModule(module);
    }

    @Override
    public Module updateModule(Module module) {
        Module moduleDB = getModule(module.getId());
        if(null == moduleDB){
            return null;
        }

        moduleDB.setModule(module.getModule());
        moduleDB.setDescription(module.getDescription());
        moduleDB.setImageConfig(module.getImageConfig());
        moduleDB.setOrderRow(module.getOrderRow());

        moduleDB.setStatus(module.getStatus());
        moduleDB.setLast_update_date(new Date());
        moduleDB.setLast_update_by(module.getLast_update_by());

        return moduleRepository.save(moduleDB);
    }


    @Override
    public Boolean deleteModule(Long id) {
        Module moduleDB = getModule(id);

        if(null == moduleDB){
            return false;
        }

        moduleRepository.deleteById(id);
        return true;
    }
}
