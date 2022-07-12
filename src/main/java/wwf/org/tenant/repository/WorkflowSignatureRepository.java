package wwf.org.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.WorkflowSignatures;

public interface WorkflowSignatureRepository extends JpaRepository<WorkflowSignatures, Long> {
    public WorkflowSignatures findByTypeSignature(String typeSignature);
}
