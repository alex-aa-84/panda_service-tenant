package wwf.org.tenant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import wwf.org.tenant.entity.User;
import wwf.org.tenant.service.UserService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = {"${settings.cors_origin}"})
@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    private UserService userService;

    private FormatMessage formatMessage = new FormatMessage();

    @GetMapping
    public ResponseEntity<List<User>> listUsers(){
        List<User> users = userService.listAllUser();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id){
        User user = userService.getUser(id);
        if(null == user){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        User userCreate = userService.createUser(user);
        if(null == userCreate) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuario existente en la BD.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userCreate);
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage.format(result));
        }

        User userDB = userService.updateUser(user);
        if(null == userDB) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDB);
    }
}
