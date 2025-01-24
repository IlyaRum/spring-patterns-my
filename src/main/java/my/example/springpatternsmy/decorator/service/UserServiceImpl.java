package my.example.springpatternsmy.decorator.service;

import lombok.AllArgsConstructor;
import my.example.springpatternsmy.decorator.dto.UserDto;
import my.example.springpatternsmy.decorator.repository.UserRepository;
import my.example.springpatternsmy.decorator.repository.entity.User;
import my.example.springpatternsmy.decorator.rest.UserController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserController {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<UserDto> getUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDto userDto = new UserDto(user.getId(), user.getName());
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
