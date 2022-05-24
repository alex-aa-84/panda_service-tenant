package wwf.org.tenant.service;

import wwf.org.tenant.entity.Database;

import java.util.List;

public interface DatabaseService {

    public List<Database> listAllDatabase();
    public Database getDatabase(Long id);

    public Database createDatabase(Database database);
    public Database updateDatabase(Database database);

}
