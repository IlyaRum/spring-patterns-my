package my.example.springpatternsmy.decorator.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.example.springpatternsmy.decorator.dto.UserDto;
import my.example.springpatternsmy.decorator.rest.UserController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class LoggingDecorator implements UserController {

    private final UserController userController;

    @Override
    public ResponseEntity<UserDto> getUser(Long userId) {
        logRequest(userId);
        ResponseEntity<UserDto> response = userController.getUser(userId);
        logResponse(response);
        return response;
    }

    private void logRequest(Long userId) {
        log.info("Получен запрос на получение пользователя с id {}", userId);
    }

    private void logResponse(ResponseEntity<UserDto> response) {
        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("Запрос успешно обработан");
        } else {
            log.warn("Ошибка при обработке запроса: {}", response.getBody());
        }
    }
}

