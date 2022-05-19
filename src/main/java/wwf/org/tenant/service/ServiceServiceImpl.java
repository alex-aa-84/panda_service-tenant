package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import wwf.org.tenant.entity.Service;
import wwf.org.tenant.repository.ServiceRepository;

import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService{

    @Autowired
    private ServiceRepository serviceRepository;


    @Override
    public List<Service> listAllService() {
        return serviceRepository.findAll();
    }

    @Override
    public Service getService(Long id) {
        return serviceRepository.findById(id).orElse(null);
    }

    @Override
    public Service createService(Service service) {
        service.setStatus("CREATED");
        service.setCreation_date(new Date());
        service.setLast_update_date(new Date());
        return serviceRepository.save(service);
    }

    @Override
    public Service updateService(Service service) {
        Service serviceDB = getService(service.getId());
        if(null == serviceDB){
            return null;
        }

        service.setStatus("MODIFIED");
        service.setLast_update_date(new Date());

        return serviceRepository.save(service);
    }

    @Override
    public Service deleteService(Service service) {
        Service serviceDB = getService(service.getId());
        if(null == serviceDB){
            return null;
        }

        service.setStatus("DELETED");
        service.setLast_update_date(new Date());

        return serviceRepository.save(service);
    }
}
