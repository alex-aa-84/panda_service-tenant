package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.Database;
import wwf.org.tenant.repository.DatabaseRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseServiceImpl implements DatabaseService{

    @Autowired
    private DatabaseRepository databaseRepository;

    @Override
    public List<Database> listAllDatabase() {
        return databaseRepository.findAll();
    }

    @Override
    public Database getDatabase(Long id) {
        return databaseRepository.findById(id).orElse(null);
    }

    @Override
    public Database createDatabase(Database database) {
        database.setStatus("CREATED");
        database.setCreation_date(new Date());
        database.setLast_update_date(new Date());
        return databaseRepository.save(database);
    }

    @Override
    public Database updateDatabase(Database database) {
        Database databaseDB = getDatabase(database.getId());
        if(null == databaseDB){
            return null;
        }

        databaseDB.setDb_connection(database.getDb_connection());
        databaseDB.setDb_host(databaseDB.getDb_host());
        databaseDB.setDb_port(databaseDB.getDb_port());
        databaseDB.setDb_database(database.getDb_database());
        databaseDB.setDb_username(database.getDb_username());
        databaseDB.setDb_password(database.getDb_password());

        databaseDB.setStatus(database.getStatus());
        databaseDB.setLast_update_date(new Date());
        databaseDB.setLast_update_by(database.getLast_update_by());

        return databaseRepository.save(databaseDB);
    }
}
