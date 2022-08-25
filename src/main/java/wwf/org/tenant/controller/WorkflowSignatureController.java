package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wwf.org.tenant.entity.WorkflowSignatures;
import wwf.org.tenant.service.WorkflowSignatureService;
import wwf.org.tenant.serviceApi.FormatMessage;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"${settings.cors_origin}", "${settings.cors_origin_pro}"}, maxAge = 3600,
        allowedHeaders={"Origin", "X-Requested-With", "Content-Type", "Accept", "x-client-key", "x-client-token", "x-client-secret", "Authorization"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.DELETE, RequestMethod.PUT})
@RestController
@RequestMapping(value="/wwf/workflowsignature")
public class WorkflowSignatureController {

    @Autowired
    private WorkflowSignatureService service;

    private FormatMessage formatMessage = new FormatMessage();

    @GetMapping
    public ResponseEntity<List<WorkflowSignatures>> listWorkflowSignatures(){
        List<WorkflowSignatures> bd = service.listAllWorkflowSignatures();
        if(bd.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(bd);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkflowSignatures> getWorkflowSignatures(@PathVariable("id") Long id){
        WorkflowSignatures bd = service.getWorkflowSignatures(id);
        if(null == bd) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bd);
    }

    @PostMapping()
    public ResponseEntity<WorkflowSignatures> createWorkflowSignatures(@Valid @RequestBody WorkflowSignatures workflowSignatures, BindingResult result){

        WorkflowSignatures bd = service.findByTypeSignature(workflowSignatures.getTypeSignature());

        if (null != bd){
            FieldError err = new FieldError("Error", "registroExistente", "registroExistenteBD");
            result.addError(err);
        }

        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createWorkflowSignatures(workflowSignatures));
    }

    @PutMapping()
    public ResponseEntity<WorkflowSignatures> updateWorkflowSignatures(@Valid @RequestBody WorkflowSignatures workflowSignatures, BindingResult result){

        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        WorkflowSignatures workflowUpdate = service.updateWorkflowSignatures(workflowSignatures);
        if(null == workflowUpdate){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(workflowUpdate);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteWorkflowSignatures(@PathVariable("id") Long id){

        Boolean action = service.deleteWorkflowSignatures(id);

        if ( action){
            return ResponseEntity.ok(action);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
