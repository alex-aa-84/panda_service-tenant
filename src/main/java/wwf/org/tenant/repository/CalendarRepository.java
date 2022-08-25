package wwf.org.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import wwf.org.tenant.entity.Calendar;

import java.util.Date;
import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    public Calendar findByDate(Date date);
    public Calendar findByDayAndMonthAndYear(Integer day, Integer month, Integer year);

    @Query("SELECT p FROM Calendar p where p.date between ?1 and ?2")
    public List<Calendar> findRangoCalendar(Date dateIni, Date dateFin);
}
