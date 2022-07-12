package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wwf.org.tenant.entity.WorkflowStates;
import wwf.org.tenant.service.WorkflowSignatureService;
import wwf.org.tenant.service.WorkflowStatesService;
import wwf.org.tenant.serviceApi.FormatMessage;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"${settings.cors_origin}", "${settings.cors_origin_pro}"}, maxAge = 3600,
        allowedHeaders={"Origin", "X-Requested-With", "Content-Type", "Accept", "x-client-key", "x-client-token", "x-client-secret", "Authorization"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.DELETE, RequestMethod.PUT})
@RestController
@RequestMapping(value="/wwf/workflowstate")
public class WorkflowStateController {
    @Autowired
    private WorkflowStatesService service;

    private FormatMessage formatMessage = new FormatMessage();

    @GetMapping
    public ResponseEntity<List<WorkflowStates>> listWorkflowStates(){
        List<WorkflowStates> bd = service.listAllWorkflowStates();
        if(bd.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(bd);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkflowStates> getWorkflowStates(@PathVariable("id") Long id){
        WorkflowStates bd = service.getWorkflowStates(id);
        if(null == bd) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bd);
    }

    @PostMapping()
    public ResponseEntity<WorkflowStates> createWorkflowStates(@Valid @RequestBody WorkflowStates workflowState, BindingResult result){

        WorkflowStates bd = service.findByState(workflowState.getState());

        if (null != bd){
            FieldError err = new FieldError("Error", "registroExistente", "registroExistenteBD");
            result.addError(err);
        }

        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createWorkflowStates(workflowState));
    }

    @PutMapping()
    public ResponseEntity<WorkflowStates> updateWorkflowStates(@Valid @RequestBody WorkflowStates workflowState, BindingResult result){

        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        WorkflowStates workflowUpdate = service.updateWorkflowStates(workflowState);
        if(null == workflowUpdate){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(workflowUpdate);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteWorkflowStates(@PathVariable("id") Long id){

        Boolean action = service.deleteWorkflowStates(id);

        if ( action){
            return ResponseEntity.ok(action);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
