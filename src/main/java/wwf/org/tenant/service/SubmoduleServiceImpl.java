package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.Submodule;
import wwf.org.tenant.entity.User;
import wwf.org.tenant.repository.SubmoduleRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmoduleServiceImpl implements SubmoduleService{

    @Autowired
    private SubmoduleRepository submoduleRepository;


    @Override
    public List<Submodule> listAllSubmodule() {
        return submoduleRepository.findAll();
    }

    @Override
    public Submodule getSubmodule(Long id) {
        return submoduleRepository.findById(id).orElse(null);
    }

    @Override
    public Submodule createSubmodule(Submodule submodule) {
        submodule.setStatus("CREATED");
        submodule.setCreation_date(new Date());
        submodule.setCreation_date(new Date());
        return submoduleRepository.save(submodule);
    }

    @Override
    public Submodule updateSubmodule(Submodule submodule) {
        Submodule submoduleDB = getSubmodule(submodule.getId());
        if(null == submoduleDB){
            return null;
        }
        submodule.setStatus("MODIFIED");
        submodule.setLast_update_date(new Date());

        return submoduleRepository.save(submodule);
    }

    @Override
    public Submodule deleteSubmodule(Submodule submodule) {
        Submodule submoduleDB = getSubmodule(submodule.getId());
        if(null == submoduleDB){
            return null;
        }
        submodule.setStatus("DELETED");
        submodule.setLast_update_date(new Date());

        return submoduleRepository.save(submodule);
    }
}
