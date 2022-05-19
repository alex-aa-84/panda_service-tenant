package wwf.org.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {

}
