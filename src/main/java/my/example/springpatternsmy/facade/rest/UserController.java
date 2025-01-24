package my.example.springpatternsmy.facade.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import my.example.springpatternsmy.facade.service.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/facade/users")
@Tag(name = "Facade")
public class UserController {

    private final UserFacade userFacade;

    @Autowired
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getUserDetails(@PathVariable long id) {
        String details = userFacade.getUserDetails(id);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }
}
