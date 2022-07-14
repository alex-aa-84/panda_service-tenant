package wwf.org.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.TimesheetStatus;

public interface TimesheetStatusRepository extends JpaRepository<TimesheetStatus, Long> {

    public TimesheetStatus findByName(String name);
}
