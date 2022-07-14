package wwf.org.tenant.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.TimesheetVersion;

public interface TimesheetVersionRepository extends JpaRepository<TimesheetVersion, Long> {

    public TimesheetVersion findByName(String name);

}
