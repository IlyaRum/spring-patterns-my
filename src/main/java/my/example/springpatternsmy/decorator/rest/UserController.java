package my.example.springpatternsmy.decorator.rest;

import my.example.springpatternsmy.decorator.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserController {
    ResponseEntity<UserDto> getUser(Long userId);
}
