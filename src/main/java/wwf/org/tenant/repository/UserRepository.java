package wwf.org.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import wwf.org.tenant.entity.Tenant;
import wwf.org.tenant.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByOid(String oid);
    public List<User> findByTenant(Tenant tenant);


    @Query("SELECT u FROM User u where u.status != 'INACTIVE'")
    public List<User> findActiveUser();

}
