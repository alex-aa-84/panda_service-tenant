package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.WorkflowStates;
import wwf.org.tenant.repository.WorkflowStateRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkflowStatesServiceImpl implements WorkflowStatesService{
    @Autowired
    private WorkflowStateRepository repo;

    @Override
    public List<WorkflowStates> listAllWorkflowStates() {
        return repo.findAll();
    }

    @Override
    public WorkflowStates getWorkflowStates(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public WorkflowStates createWorkflowStates(WorkflowStates state) {
        state.setStatus("CREATED");
        state.setCreation_date(new Date());
        state.setLast_update_date(new Date());

        return repo.save(state);
    }

    @Override
    public WorkflowStates updateWorkflowStates(WorkflowStates state) {
        WorkflowStates db = getWorkflowStates(state.getId());
        if(null == db){
            return null;
        }

        db.setState(state.getState());
        db.setWorkflowSignatures(state.getWorkflowSignatures());

        db.setDescription(state.getDescription());
        db.setStatus(state.getStatus());
        db.setLast_update_date(new Date());
        db.setLast_update_by(state.getLast_update_by());

        return repo.save(db);
    }

    @Override
    public WorkflowStates findByState(String state) {
        return repo.findByState(state);
    }

    @Override
    public Boolean deleteWorkflowStates(Long id) {
        WorkflowStates db = getWorkflowStates(id);

        if(null == db){
            return false;
        }

        repo.deleteById(id);
        return true;
    }
}
