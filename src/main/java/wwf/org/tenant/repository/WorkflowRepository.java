package wwf.org.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.Workflow;

public interface WorkflowRepository extends JpaRepository<Workflow, Long> {
    public Workflow findByWorkflow(String workflow);
}
