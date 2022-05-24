package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.User;
import wwf.org.tenant.repository.UserRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> listAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByOid_(String oid_) {
        return userRepository.findByOid_(oid_);
    }

    @Override
    public User createUser(User user) {
        user.setStatus("CREATED");
        user.setCreation_date(new Date());
        user.setCreation_date(new Date());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        User userDB = getUser(user.getId());
        if(null == userDB){
            return null;
        }

        userDB.setTenant_id(user.getTenant_id());
        userDB.setOid_(user.getOid_());
        userDB.setUser_principal_name(user.getUser_principal_name());
        userDB.setDisplay_name(user.getDisplay_name());
        userDB.setSurname(user.getSurname());
        userDB.setGiven_name(user.getGiven_name());
        userDB.setPermissions_tenant_id(user.getPermissions_tenant_id());

        userDB.setStatus(user.getStatus());
        userDB.setLast_update_date(new Date());
        userDB.setLast_update_by(user.getLast_update_by());

        return userRepository.save(userDB);
    }
}
