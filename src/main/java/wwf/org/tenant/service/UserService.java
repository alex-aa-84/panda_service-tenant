package wwf.org.tenant.service;

import wwf.org.tenant.entity.User;

import java.util.List;

public interface UserService {

    public List<User> listAllUser();
    public User getUser(Long id);

    public User findByOid_(String oid_);

    public User createUser(User user);
    public User updateUser(User user);
}
