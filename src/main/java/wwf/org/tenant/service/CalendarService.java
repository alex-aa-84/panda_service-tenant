package wwf.org.tenant.service;

import wwf.org.tenant.entity.Calendar;

import java.util.Date;
import java.util.List;

public interface CalendarService {
    public List<Calendar> listAllCalendar();
    public Calendar getCalendar(Long id);

    public Calendar findByCalendar(Date date);
    public Calendar createCalendar(Calendar calendar);
    public Calendar updateCalendar(Calendar calendar);

    public Boolean deleteCalendar(Long id);
}
