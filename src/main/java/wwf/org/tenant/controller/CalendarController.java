package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wwf.org.tenant.entity.Calendar;
import wwf.org.tenant.service.CalendarService;
import wwf.org.tenant.serviceApi.FormatMessage;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"${settings.cors_origin}", "${settings.cors_origin_pro}"}, maxAge = 3600,
        allowedHeaders={"Origin", "X-Requested-With", "Content-Type", "Accept", "x-client-key", "x-client-token", "x-client-secret", "Authorization"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.DELETE, RequestMethod.PUT})
@RestController
@RequestMapping(value="/wwf/calendar")
public class CalendarController {
    @Autowired
    private CalendarService service;

    private FormatMessage formatMessage = new FormatMessage();

    @GetMapping
    public ResponseEntity<List<Calendar>> listCalendar(){
        List<Calendar> bd = service.listAllCalendar();
        if(bd.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(bd);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Calendar> getCalendar(@PathVariable("id") Long id){
        Calendar bd = service.getCalendar(id);
        if(null == bd) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bd);
    }

    @PostMapping()
    public ResponseEntity<Calendar> createCalendar(@Valid @RequestBody Calendar calendar, BindingResult result){

        Calendar calendarBD = service.findByCalendar(calendar.getDate());

        if (null != calendarBD){
            FieldError err = new FieldError("Error", "registroExistente", "registroExistenteBD");
            result.addError(err);
        }

        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCalendar(calendar));
    }

    @PutMapping()
    public ResponseEntity<Calendar> updateCalendar(@Valid @RequestBody Calendar calendar, BindingResult result){

        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        Calendar calendarUpdate = service.updateCalendar(calendar);
        if(null == calendarUpdate){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(calendarUpdate);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteCalendar(@PathVariable("id") Long id){

        Boolean action = service.deleteCalendar(id);

        if ( action){
            return ResponseEntity.ok(action);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
}