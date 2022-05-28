package wwf.org.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wwf.org.tenant.entity.Country;
import wwf.org.tenant.entity.Database;

import javax.xml.crypto.Data;

public interface DatabaseRepository extends JpaRepository<Database, Long> {

    public Data findByDbDatabase(String dbDatabase);

}
