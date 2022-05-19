package wwf.org.tenant.service;

import wwf.org.tenant.entity.DepartmentWWF;

import java.util.List;

public interface DepartmentWWFService {

    public List<DepartmentWWF> listAllDepartmentWWF();
    public DepartmentWWF getDepartmentWWF(Long id);

    public DepartmentWWF createDepartmentWWF(DepartmentWWF department);
    public DepartmentWWF updateDepartmentWWF(DepartmentWWF department);
    public DepartmentWWF deleteDepartmentWWF(DepartmentWWF department);
}
