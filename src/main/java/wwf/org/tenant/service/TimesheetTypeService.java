package wwf.org.tenant.service;


import wwf.org.tenant.entity.TimesheetType;

import java.util.List;

public interface TimesheetTypeService {
    public List<TimesheetType> listAllTimesheetType();

    public TimesheetType getTimesheetType(Long id);

    public TimesheetType createTimesheetType(TimesheetType value);
    public TimesheetType updateTimesheetType(TimesheetType value);
    public Boolean deleteTimesheetType(Long id);

    public TimesheetType findByName(String name);
}
