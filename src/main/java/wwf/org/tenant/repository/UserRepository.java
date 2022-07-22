package wwf.org.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.Tenant;
import wwf.org.tenant.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByOid(String oid);
    public List<User> findByTenant(Tenant tenant);

}
