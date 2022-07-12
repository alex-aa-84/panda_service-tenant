package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.Workflow;
import wwf.org.tenant.entity.WorkflowSignatures;
import wwf.org.tenant.repository.WorkflowRepository;
import wwf.org.tenant.repository.WorkflowSignatureRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkflowSignatureServiceImpl implements WorkflowSignatureService{
    @Autowired
    private WorkflowSignatureRepository repo;

    @Override
    public List<WorkflowSignatures> listAllWorkflowSignatures() {
        return repo.findAll();
    }

    @Override
    public WorkflowSignatures getWorkflowSignatures(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public WorkflowSignatures createWorkflowSignatures(WorkflowSignatures calendar) {
        calendar.setStatus("CREATED");
        calendar.setCreation_date(new Date());
        calendar.setLast_update_date(new Date());

        return repo.save(calendar);
    }

    @Override
    public WorkflowSignatures updateWorkflowSignatures(WorkflowSignatures calendar) {
        WorkflowSignatures db = getWorkflowSignatures(calendar.getId());
        if(null == db){
            return null;
        }

        db.setTypeSignature(calendar.getTypeSignature());

        db.setDescription(calendar.getDescription());
        db.setStatus(calendar.getStatus());
        db.setLast_update_date(new Date());
        db.setLast_update_by(calendar.getLast_update_by());

        return repo.save(db);
    }

    @Override
    public WorkflowSignatures findByTypeSignature(String typeSignature) {
        return repo.findByTypeSignature(typeSignature);
    }

    @Override
    public Boolean deleteWorkflowSignatures(Long id) {
        WorkflowSignatures db = getWorkflowSignatures(id);

        if(null == db){
            return false;
        }

        repo.deleteById(id);
        return true;
    }
}
