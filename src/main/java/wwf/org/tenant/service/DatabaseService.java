package wwf.org.tenant.service;

import wwf.org.tenant.entity.Database;

import javax.xml.crypto.Data;
import java.util.List;

public interface DatabaseService {

    public List<Database> listAllDatabase();
    public Database getDatabase(Long id);

    public Database createDatabase(Database database);
    public Database updateDatabase(Database database);

    public Data findByDbDatabase(String dbDatabase);
    public Boolean deleteDatabase(Long id);

}
