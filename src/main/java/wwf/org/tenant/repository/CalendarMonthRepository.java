package wwf.org.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.CalendarMonth;
import wwf.org.tenant.entity.Country;

public interface CalendarMonthRepository extends JpaRepository<CalendarMonth, Long> {
    public CalendarMonth findByFiscalYearAndMonth(Integer fiscalyear, Integer month);
}
