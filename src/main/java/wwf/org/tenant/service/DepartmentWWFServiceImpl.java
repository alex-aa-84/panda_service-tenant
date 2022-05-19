package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.DepartmentWWF;
import wwf.org.tenant.repository.DepartmentWWFRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentWWFServiceImpl implements DepartmentWWFService{

    @Autowired
    private DepartmentWWFRepository departmentWWFRepository;

    @Override
    public List<DepartmentWWF> listAllDepartmentWWF() {
        return departmentWWFRepository.findAll();
    }

    @Override
    public DepartmentWWF getDepartmentWWF(Long id) {
        return departmentWWFRepository.findById(id).orElse(null);
    }

    @Override
    public DepartmentWWF createDepartmentWWF(DepartmentWWF department) {
        department.setStatus("CREATED");
        department.setCreation_date(new Date());
        department.setCreation_date(new Date());
        return departmentWWFRepository.save(department);
    }

    @Override
    public DepartmentWWF updateDepartmentWWF(DepartmentWWF department) {
        DepartmentWWF departmentWWFDB = getDepartmentWWF(department.getId());
        if(null == departmentWWFDB){
            return null;
        }

        department.setStatus("MODIFIED");
        department.setLast_update_date(new Date());

        return departmentWWFRepository.save(department);
    }

    @Override
    public DepartmentWWF deleteDepartmentWWF(DepartmentWWF department) {
        DepartmentWWF departmentWWFDB = getDepartmentWWF(department.getId());
        if(null == departmentWWFDB){
            return null;
        }

        department.setStatus("DELETED");
        department.setLast_update_date(new Date());

        return departmentWWFRepository.save(department);
    }
}
