package wwf.org.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.WorkflowStates;

public interface WorkflowStateRepository extends JpaRepository<WorkflowStates, Long> {
    public WorkflowStates findByState(String state);
}
