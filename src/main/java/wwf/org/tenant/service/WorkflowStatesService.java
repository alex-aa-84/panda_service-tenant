package wwf.org.tenant.service;

import wwf.org.tenant.entity.WorkflowStates;

import java.util.List;

public interface WorkflowStatesService {
    public List<WorkflowStates> listAllWorkflowStates();
    public WorkflowStates getWorkflowStates(Long id);

    public WorkflowStates findByState(String state);
    public WorkflowStates createWorkflowStates(WorkflowStates workflowStates);
    public WorkflowStates updateWorkflowStates(WorkflowStates workflowStates);

    public Boolean deleteWorkflowStates(Long id);
}
