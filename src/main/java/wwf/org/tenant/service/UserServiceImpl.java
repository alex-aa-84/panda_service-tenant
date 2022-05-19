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
        user.setStatus("MODIFIED");
        user.setLast_update_date(new Date());

        return userRepository.save(user);
    }

    @Override
    public User deleteUser(User user) {
        User userDB = getUser(user.getId());
        if(null == userDB){
            return null;
        }
        user.setStatus("DELETED");
        user.setLast_update_date(new Date());

        return userRepository.save(user);
    }
}
