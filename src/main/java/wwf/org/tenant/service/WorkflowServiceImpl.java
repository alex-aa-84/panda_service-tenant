package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.Workflow;
import wwf.org.tenant.repository.WorkflowRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkflowServiceImpl implements WorkflowService{
    @Autowired
    private WorkflowRepository repo;

    @Override
    public List<Workflow> listAllWorkflow() {
        return repo.findAll();
    }

    @Override
    public Workflow getWorkflow(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Workflow createWorkflow(Workflow calendar) {
        calendar.setStatus("CREATED");
        calendar.setCreation_date(new Date());
        calendar.setLast_update_date(new Date());

        return repo.save(calendar);
    }

    @Override
    public Workflow updateWorkflow(Workflow calendar) {
        Workflow db = getWorkflow(calendar.getId());
        if(null == db){
            return null;
        }

        db.setWorkflow(calendar.getWorkflow());

        db.setDescription(calendar.getDescription());
        db.setStatus(calendar.getStatus());
        db.setLast_update_date(new Date());
        db.setLast_update_by(calendar.getLast_update_by());

        return repo.save(db);
    }

    @Override
    public Workflow findByWorkflow(String workflow) {
        return repo.findByWorkflow(workflow);
    }

    @Override
    public Boolean deleteWorkflow(Long id) {
        Workflow db = getWorkflow(id);

        if(null == db){
            return false;
        }

        repo.deleteById(id);
        return true;
    }
}
