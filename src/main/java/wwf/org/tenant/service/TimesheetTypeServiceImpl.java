package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.TimesheetType;
import wwf.org.tenant.repository.TimesheetTypeRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimesheetTypeServiceImpl implements TimesheetTypeService{


    @Autowired
    private TimesheetTypeRepository repository;


    @Override
    public List<TimesheetType> listAllTimesheetType() {
        return repository.findAll();
    }

    @Override
    public TimesheetType getTimesheetType(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public TimesheetType createTimesheetType(TimesheetType value) {
        value.setStatus("CREATED");
        value.setCreation_date(new Date());
        value.setLast_update_date(new Date());

        return repository.save(value);
    }

    @Override
    public TimesheetType updateTimesheetType(TimesheetType value) {
        TimesheetType bd = getTimesheetType(value.getId());
        if(null == bd){
            return null;
        }

        bd.setName(value.getName());

        bd.setDescription(value.getDescription());

        bd.setAttribute1(value.getAttribute1());
        bd.setAttribute2(value.getAttribute2());
        bd.setAttribute3(value.getAttribute3());
        bd.setAttribute4(value.getAttribute4());
        bd.setAttribute5(value.getAttribute5());
        bd.setAttribute6(value.getAttribute6());
        bd.setAttribute7(value.getAttribute7());
        bd.setAttribute8(value.getAttribute8());
        bd.setAttribute9(value.getAttribute9());
        bd.setAttribute10(value.getAttribute10());

        bd.setStatus(value.getStatus());

        bd.setLast_update_by(value.getLast_update_by());
        bd.setLast_update_date(new Date());

        return repository.save(bd);
    }

    @Override
    public Boolean deleteTimesheetType(Long id) {
        TimesheetType bd = getTimesheetType(id);
        if(null == bd){
            return false;
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public TimesheetType findByName(String name) {
        return repository.findByName(name);
    }
}
