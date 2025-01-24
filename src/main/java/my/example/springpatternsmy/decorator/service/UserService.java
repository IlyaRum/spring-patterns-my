package my.example.springpatternsmy.decorator.service;

import my.example.springpatternsmy.decorator.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserDto> getUser(Long userId);
}
