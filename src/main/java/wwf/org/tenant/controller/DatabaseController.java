package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wwf.org.tenant.entity.Database;
import wwf.org.tenant.service.DatabaseService;

import javax.validation.Valid;
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
        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        Database databaseCreate = databaseService.createDatabase(database);
        if(null == databaseCreate) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Base de datos existente en la BD.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(databaseCreate);
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
}
