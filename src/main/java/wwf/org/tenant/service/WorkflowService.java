package wwf.org.tenant.service;

import wwf.org.tenant.entity.Workflow;

import java.util.List;

public interface WorkflowService {
    public List<Workflow> listAllWorkflow();
    public Workflow getWorkflow(Long id);

    public Workflow findByWorkflow(String workflow);
    public Workflow createWorkflow(Workflow workflow);
    public Workflow updateWorkflow(Workflow workflow);

    public Boolean deleteWorkflow(Long id);

}
