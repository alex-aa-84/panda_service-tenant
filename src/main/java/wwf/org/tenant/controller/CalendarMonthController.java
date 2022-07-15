package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wwf.org.tenant.entity.CalendarMonth;
import wwf.org.tenant.service.CalendarMonthService;
import wwf.org.tenant.serviceApi.FormatMessage;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"${settings.cors_origin}", "${settings.cors_origin_pro}"}, maxAge = 3600,
        allowedHeaders={"Origin", "X-Requested-With", "Content-Type", "Accept", "x-client-key", "x-client-token", "x-client-secret", "Authorization"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.DELETE, RequestMethod.PUT})
@RestController
@RequestMapping(value="/wwf/calendarmonth")
public class CalendarMonthController {

    @Autowired
    private CalendarMonthService service;

    private FormatMessage formatMessage = new FormatMessage();

    @GetMapping
    public ResponseEntity<List<CalendarMonth>> listCalendarMonth(){
        List<CalendarMonth> value = service.listAllCalendarMonth();
        if(value.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(value);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CalendarMonth> getCalendarMonth(@PathVariable("id") Long id){
        CalendarMonth value = service.getCalendarMonth(id);
        if(null == value){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(value);
    }

    @PostMapping()
    public ResponseEntity<CalendarMonth> createCalendarMonth(@Valid @RequestBody CalendarMonth value, BindingResult result){

        CalendarMonth valueBD = service.findByFiscalYearAndMonth(value.getFiscalYear(), value.getMonth());

        if (null != valueBD){
            FieldError err = new FieldError("Error", "registroExistente", "registroExistenteBD");
            result.addError(err);
        }

        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCalendarMonth(value));
    }

    @PutMapping()
    public ResponseEntity<CalendarMonth> updateCalendarMonth(@Valid @RequestBody CalendarMonth value, BindingResult result){

        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        CalendarMonth valueUpdate = service.updateCalendarMonth(value);
        if(null == valueUpdate){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(valueUpdate);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteCalendarMonth(@PathVariable("id") Long id){

        Boolean action = service.deleteCalendarMonth(id);

        if ( action){
            return ResponseEntity.ok(action);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

}
