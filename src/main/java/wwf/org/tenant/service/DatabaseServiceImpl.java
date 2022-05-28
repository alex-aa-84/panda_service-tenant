package wwf.org.tenant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wwf.org.tenant.entity.Database;
import wwf.org.tenant.repository.DatabaseRepository;

import javax.xml.crypto.Data;
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

        databaseDB.setDbConnection(database.getDbConnection());
        databaseDB.setDbHost(databaseDB.getDbHost());
        databaseDB.setDbPort(databaseDB.getDbPort());
        databaseDB.setDbDatabase(database.getDbDatabase());
        databaseDB.setDbUsername(database.getDbUsername());
        databaseDB.setDbPassword(database.getDbPassword());

        databaseDB.setStatus(database.getStatus());
        databaseDB.setLast_update_date(new Date());
        databaseDB.setLast_update_by(database.getLast_update_by());

        return databaseRepository.save(databaseDB);
    }

    @Override
    public Data findByDbDatabase(String dbDatabase) {
        return databaseRepository.findByDbDatabase(dbDatabase);
    }

    @Override
    public Boolean deleteDatabase(Long id) {
        Database dataBaseDB = getDatabase(id);

        if(null == dataBaseDB){
            return false;
        }

        databaseRepository.deleteById(id);
        return true;
    }
}
