package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wwf.org.tenant.entity.Module;
import wwf.org.tenant.service.ModuleService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"${settings.cors_origin}"})
@RestController
@RequestMapping(value="/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    private FormatMessage formatMessage = new FormatMessage();

    @GetMapping
    public ResponseEntity<List<Module>> listModule(){
        List<Module> modules = moduleService.listAllModule();
        if(modules.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(modules);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Module> getModule(@PathVariable("id") Long id){
        Module module = moduleService.getModule(id);
        if(null == module){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(module);
    }

    @PostMapping()
    public ResponseEntity<Module> createModule(@Valid @RequestBody Module module, BindingResult result){
        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        Module moduleCreate = moduleService.createModule(module);
        if(null == moduleCreate) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Modulo existente en la BD.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(moduleCreate);
    }

    @PutMapping()
    public ResponseEntity<Module> updateModule(@Valid @RequestBody Module module, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        Module moduleDB = moduleService.updateModule(module);
        if(null == moduleDB) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moduleDB);
    }
}
