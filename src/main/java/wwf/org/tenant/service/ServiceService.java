package wwf.org.tenant.service;

import wwf.org.tenant.entity.Service;

import java.util.List;

public interface ServiceService {

    public List<Service> listAllService();
    public Service getService(Long id);

    public Service createService(Service service);
    public Service updateService(Service service);
    public Service deleteService(Service service);
}
