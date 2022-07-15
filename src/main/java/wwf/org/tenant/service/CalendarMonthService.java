package wwf.org.tenant.service;

import wwf.org.tenant.entity.CalendarMonth;

import java.util.List;

public interface CalendarMonthService {
    public List<CalendarMonth> listAllCalendarMonth();
    public CalendarMonth getCalendarMonth(Long id);
    public CalendarMonth createCalendarMonth(CalendarMonth country);
    public CalendarMonth updateCalendarMonth(CalendarMonth country);

    public Boolean deleteCalendarMonth(Long id);

    public CalendarMonth findByFiscalYearAndMonth(Integer fiscalyear, Integer month);
}
