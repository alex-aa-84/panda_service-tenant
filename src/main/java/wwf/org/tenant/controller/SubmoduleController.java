package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wwf.org.tenant.entity.Submodule;
import wwf.org.tenant.service.SubmoduleService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"${settings.cors_origin}"})
@RestController
@RequestMapping(value="/submodules")
public class SubmoduleController {

    @Autowired
    private SubmoduleService submoduleService;

    private FormatMessage formatMessage = new FormatMessage();

    @GetMapping
    public ResponseEntity<List<Submodule>> listSubmodule(){
        List<Submodule> submodules = submoduleService.listAllSubmodule();
        if(submodules.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(submodules);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Submodule> getSubmodule(@PathVariable("id") Long id){
        Submodule submodule = submoduleService.getSubmodule(id);
        if(null == submodule){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(submodule);
    }

    @PostMapping()
    public ResponseEntity<Submodule> createSubmodule(@Valid @RequestBody Submodule submodule, BindingResult result){
        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        Submodule submoduleCreate = submoduleService.createSubmodule(submodule);
        if(null == submoduleCreate) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Submodulo existente en la BD.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(submoduleCreate);
    }

    @PutMapping()
    public ResponseEntity<Submodule> updateSubmodule(@Valid @RequestBody Submodule submodule, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        Submodule submoduleDB = submoduleService.updateSubmodule(submodule);
        if(null == submoduleDB) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(submoduleDB);
    }
}
