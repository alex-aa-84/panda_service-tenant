package wwf.org.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.Database;

public interface DatabaseRepository extends JpaRepository<Database, Long> {

}
