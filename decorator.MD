### Логирование запросов и ответов

Предположим, вам нужно логировать все входящие HTTP-запросы и ответы вашего REST API. Для этого можно создать декоратор,
который будет оборачивать контроллеры и выполнять логирование до и после обработки запроса.

#### Шаг 1: Определение интерфейса и базовой реализации

Создайте интерфейс для вашего контроллера и базовую реализацию:

```java
public interface UserController {
    ResponseEntity<UserDto> getUser(Long userId);
}

@Service
public class UserServiceImpl implements UserController {
}
```

#### Шаг 2: Создание декоратора

Создайте декоратор, который будет логировать запросы и ответы:

```java
public class LoggingDecorator implements UserController {
}
```

#### Шаг 3: Конфигурация Spring

Обновите конфигурацию Spring, чтобы использовать декоратор вместо оригинальной службы:

```java

@Configuration
public class AppConfig {

    @Bean
    public UserController userController(UserServiceImpl userServiceImpl) {
        return new LoggingDecorator(userServiceImpl);
    }
}
```

Теперь все вызовы метода `getUser` будут проходить через декоратор, который выполнит логирование перед и после вызова
оригинального метода.

