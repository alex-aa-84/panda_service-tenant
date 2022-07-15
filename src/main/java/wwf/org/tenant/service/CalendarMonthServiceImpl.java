package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.CalendarMonth;
import wwf.org.tenant.repository.CalendarMonthRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarMonthServiceImpl implements CalendarMonthService {

    @Autowired
    private CalendarMonthRepository repository;

    @Override
    public List<CalendarMonth> listAllCalendarMonth() {
        return repository.findAll();
    }

    @Override
    public CalendarMonth getCalendarMonth(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public CalendarMonth createCalendarMonth(CalendarMonth calendarMonth) {
        calendarMonth.setStatus("CREATED");
        calendarMonth.setCreation_date(new Date());
        calendarMonth.setLast_update_date(new Date());

        return repository.save(calendarMonth);
    }

    @Override
    public CalendarMonth updateCalendarMonth(CalendarMonth calendarMonth) {
        CalendarMonth calendarMonthDB = getCalendarMonth(calendarMonth.getId());
        if(null == calendarMonthDB){
            return null;
        }

        calendarMonthDB.setFiscalYear(calendarMonth.getFiscalYear());
        calendarMonthDB.setYear(calendarMonth.getYear());
        calendarMonthDB.setMonth(calendarMonth.getMonth());
        calendarMonthDB.setMonth2(calendarMonth.getMonth2());
        calendarMonthDB.setDescription(calendarMonth.getDescription());

        calendarMonthDB.setAttribute1(calendarMonth.getAttribute1());
        calendarMonthDB.setAttribute2(calendarMonth.getAttribute2());
        calendarMonthDB.setAttribute3(calendarMonth.getAttribute3());
        calendarMonthDB.setAttribute4(calendarMonth.getAttribute4());
        calendarMonthDB.setAttribute5(calendarMonth.getAttribute5());
        calendarMonthDB.setAttribute6(calendarMonth.getAttribute6());
        calendarMonthDB.setAttribute7(calendarMonth.getAttribute7());
        calendarMonthDB.setAttribute8(calendarMonth.getAttribute8());
        calendarMonthDB.setAttribute9(calendarMonth.getAttribute9());
        calendarMonthDB.setAttribute10(calendarMonth.getAttribute10());

        calendarMonthDB.setStatus(calendarMonth.getStatus());

        calendarMonthDB.setLast_update_by(calendarMonth.getLast_update_by());
        calendarMonthDB.setLast_update_date(new Date());

        return repository.save(calendarMonthDB);
    }

    @Override
    public CalendarMonth findByFiscalYearAndMonth(Integer fiscalYear, Integer month) {
        return repository.findByFiscalYearAndMonth(fiscalYear, month);
    }

    @Override
    public Boolean deleteCalendarMonth(Long id) {
        CalendarMonth calendarMonthDB = getCalendarMonth(id);

        if(null == calendarMonthDB){
            return false;
        }

        repository.deleteById(id);
        return true;
    }

}
