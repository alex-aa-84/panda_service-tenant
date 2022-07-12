package wwf.org.tenant.service;

import wwf.org.tenant.entity.WorkflowSignatures;

import java.util.List;

public interface WorkflowSignatureService {
    public List<WorkflowSignatures> listAllWorkflowSignatures();
    public WorkflowSignatures getWorkflowSignatures(Long id);

    public WorkflowSignatures findByTypeSignature(String typeSignature);
    public WorkflowSignatures createWorkflowSignatures(WorkflowSignatures workflowSignatures);
    public WorkflowSignatures updateWorkflowSignatures(WorkflowSignatures workflowSignatures);

    public Boolean deleteWorkflowSignatures(Long id);
}
