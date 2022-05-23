package wwf.org.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.Tenant;
import wwf.org.tenant.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByOid_(String oid_);

}
