package wwf.org.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.Calendar;

import java.util.Date;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    public Calendar findByDate(Date date);
}
