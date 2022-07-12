package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.Calendar;
import wwf.org.tenant.repository.CalendarRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private CalendarRepository repo;

    @Override
    public List<Calendar> listAllCalendar() {
        return repo.findAll();
    }

    @Override
    public Calendar getCalendar(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Calendar createCalendar(Calendar calendar) {
        calendar.setStatus("CREATED");
        calendar.setCreation_date(new Date());
        calendar.setLast_update_date(new Date());

        return repo.save(calendar);
    }

    @Override
    public Calendar updateCalendar(Calendar calendar) {
        Calendar db = getCalendar(calendar.getId());
        if(null == db){
            return null;
        }

        db.setDate(calendar.getDate());
        db.setYear(calendar.getYear());
        db.setMonth(calendar.getMonth());
        db.setDay(calendar.getDay());
        db.setWeekYear(calendar.getWeekYear());
        db.setWeekMonth(calendar.getWeekMonth());
        db.setDayWeekNumber(calendar.getDayWeekNumber());
        db.setDayText(calendar.getDayText());
        db.setMonthText(calendar.getMonthText());
        db.setFiscalYear(calendar.getFiscalYear());

        db.setDescription(calendar.getDescription());
        db.setStatus(calendar.getStatus());
        db.setLast_update_date(new Date());
        db.setLast_update_by(calendar.getLast_update_by());

        return repo.save(db);
    }

    @Override
    public Calendar findByCalendar(Date calendar) {
        return repo.findByDate(calendar);
    }

    @Override
    public Boolean deleteCalendar(Long id) {
        Calendar db = getCalendar(id);

        if(null == db){
            return false;
        }

        repo.deleteById(id);
        return true;
    }
}
