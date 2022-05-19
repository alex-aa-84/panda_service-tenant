package wwf.org.tenant.service;

import wwf.org.tenant.entity.Submodule;

import java.util.List;

public interface SubmoduleService {
    public List<Submodule> listAllSubmodule();
    public Submodule getSubmodule(Long id);

    public Submodule createSubmodule(Submodule submodule);
    public Submodule updateSubmodule(Submodule submodule);
    public Submodule deleteSubmodule(Submodule submodule);
}
