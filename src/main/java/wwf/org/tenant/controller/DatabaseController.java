package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wwf.org.tenant.entity.Database;
import wwf.org.tenant.service.DatabaseService;

import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.util.List;

@CrossOrigin(origins = {"${settings.cors_origin}"})
@RestController
@RequestMapping(value="/dbs")
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;

    private FormatMessage formatMessage = new FormatMessage();

    @GetMapping
    public ResponseEntity<List<Database>> listDatabase(){
        List<Database> dbs = databaseService.listAllDatabase();
        if(dbs.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(dbs);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Database> getDatabase(@PathVariable("id") Long id){
        Database db = databaseService.getDatabase(id);
        if(null == db){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(db);
    }

    @PostMapping()
    public ResponseEntity<Database> createDatabase(@Valid @RequestBody Database database, BindingResult result){
        Data dataBaseBD = databaseService.findByDbDatabase(database.getDbDatabase());

        if (null != dataBaseBD){
            FieldError err = new FieldError("Error", "dataBase", "base_datos_existente");
            result.addError(err);
        }

        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(databaseService.createDatabase(database));
    }

    @PutMapping()
    public ResponseEntity<Database> updateDatabase(@Valid @RequestBody Database database, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        Database databaseDB = databaseService.updateDatabase(database);
        if(null == databaseDB) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(databaseDB);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteDatabase(@PathVariable("id") Long id){

        Boolean action = databaseService.deleteDatabase(id);

        if ( action){
            return ResponseEntity.ok(action);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
