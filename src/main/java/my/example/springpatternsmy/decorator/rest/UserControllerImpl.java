package my.example.springpatternsmy.decorator.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import my.example.springpatternsmy.decorator.dto.UserDto;
import my.example.springpatternsmy.decorator.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/decorator/users")
@Tag(name = "Decorator")
@AllArgsConstructor
public class UserControllerImpl {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }
}
