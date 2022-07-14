package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.TimesheetVersion;
import wwf.org.tenant.repository.TimesheetVersionRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimesheetVersionServiceImpl implements TimesheetVersionService {

    @Autowired
    private TimesheetVersionRepository repository;


    @Override
    public List<TimesheetVersion> listAllTimesheetVersion() {
        return repository.findAll();
    }

    @Override
    public TimesheetVersion getTimesheetVersion(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public TimesheetVersion createTimesheetVersion(TimesheetVersion value) {
        value.setStatus("CREATED");
        value.setCreation_date(new Date());
        value.setLast_update_date(new Date());

        return repository.save(value);
    }

    @Override
    public TimesheetVersion updateTimesheetVersion(TimesheetVersion value) {
        TimesheetVersion bd = getTimesheetVersion(value.getId());
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
    public Boolean deleteTimesheetVersion(Long id) {
        TimesheetVersion bd = getTimesheetVersion(id);
        if(null == bd){
            return false;
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public TimesheetVersion findByName(String name) {
        return repository.findByName(name);
    }
}
