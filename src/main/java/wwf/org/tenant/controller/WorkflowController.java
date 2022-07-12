package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wwf.org.tenant.entity.Workflow;
import wwf.org.tenant.service.WorkflowService;
import wwf.org.tenant.serviceApi.FormatMessage;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"${settings.cors_origin}", "${settings.cors_origin_pro}"}, maxAge = 3600,
        allowedHeaders={"Origin", "X-Requested-With", "Content-Type", "Accept", "x-client-key", "x-client-token", "x-client-secret", "Authorization"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.DELETE, RequestMethod.PUT})
@RestController
@RequestMapping(value="/wwf/workflow")
public class WorkflowController {

    @Autowired
    private WorkflowService service;

    private FormatMessage formatMessage = new FormatMessage();

    @GetMapping
    public ResponseEntity<List<Workflow>> listWorkflow(){
        List<Workflow> bd = service.listAllWorkflow();
        if(bd.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(bd);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Workflow> getWorkflow(@PathVariable("id") Long id){
        Workflow bd = service.getWorkflow(id);
        if(null == bd) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bd);
    }

    @PostMapping()
    public ResponseEntity<Workflow> createWorkflow(@Valid @RequestBody Workflow workflow, BindingResult result){

        Workflow workflowBD = service.findByWorkflow(workflow.getWorkflow());

        if (null != workflowBD){
            FieldError err = new FieldError("Error", "registroExistente", "registroExistenteBD");
            result.addError(err);
        }

        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createWorkflow(workflow));
    }

    @PutMapping()
    public ResponseEntity<Workflow> updateWorkflow(@Valid @RequestBody Workflow workflow, BindingResult result){

        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        Workflow workflowUpdate = service.updateWorkflow(workflow);
        if(null == workflowUpdate){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(workflowUpdate);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteWorkflow(@PathVariable("id") Long id){

        Boolean action = service.deleteWorkflow(id);

        if ( action){
            return ResponseEntity.ok(action);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}
