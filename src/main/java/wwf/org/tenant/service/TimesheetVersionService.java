package wwf.org.tenant.service;

import wwf.org.tenant.entity.TimesheetVersion;

import java.sql.Time;
import java.util.List;

public interface TimesheetVersionService {
    public List<TimesheetVersion> listAllTimesheetVersion();

    public TimesheetVersion getTimesheetVersion(Long id);

    public TimesheetVersion createTimesheetVersion(TimesheetVersion timesheetVersion);
    public TimesheetVersion updateTimesheetVersion(TimesheetVersion timesheetVersion);
    public Boolean deleteTimesheetVersion(Long id);

    public TimesheetVersion findByName(String name);


}
