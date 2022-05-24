package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wwf.org.tenant.entity.EmailConfiguration;
import wwf.org.tenant.service.EmailConfigurationService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"${settings.cors_origin}"})
@RestController
@RequestMapping(value="/emails")
public class EmailConfigurationController {

    @Autowired
    private EmailConfigurationService emailConfigurationService;

    private FormatMessage formatMessage = new FormatMessage();

    @GetMapping
    public ResponseEntity<List<EmailConfiguration>> listEmailConfiguration(){
        List<EmailConfiguration> emails = emailConfigurationService.listAllEmailConfiguration();
        if(emails.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(emails);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmailConfiguration> getEmailConfiguration(@PathVariable("id") Long id){
        EmailConfiguration email = emailConfigurationService.getEmailConfiguration(id);
        if(null == email){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(email);
    }

    @PostMapping()
    public ResponseEntity<EmailConfiguration> createEmailConfiguration(@Valid @RequestBody EmailConfiguration emailConfiguration, BindingResult result){
        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        EmailConfiguration emailConfigurationCreate = emailConfigurationService.createEmailConfiguration(emailConfiguration);
        if(null == emailConfigurationCreate) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Configuración de email existente en la BD.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(emailConfigurationCreate);
    }

    @PutMapping()
    public ResponseEntity<EmailConfiguration> updateEmailConfiguration(@Valid @RequestBody EmailConfiguration emailConfiguration, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        EmailConfiguration emailConfigurationDB = emailConfigurationService.updateEmailConfiguration(emailConfiguration);
        if(null == emailConfigurationDB) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(emailConfigurationDB);
    }
}
