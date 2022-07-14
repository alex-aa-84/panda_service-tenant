package wwf.org.tenant.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.TimesheetType;

public interface TimesheetTypeRepository extends JpaRepository<TimesheetType, Long> {

    public TimesheetType findByName(String name);
}
