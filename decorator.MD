### Логирование запросов и ответов

Предположим, вам нужно логировать все входящие HTTP-запросы и ответы вашего REST API. Для этого можно создать декоратор,
который будет оборачивать контроллеры и выполнять логирование до и после обработки запроса.

#### Шаг 1: Определение интерфейса и базовой реализации

Создайте интерфейс для вашего контроллера и базовую реализацию:

```java
public interface UserService {
    ResponseEntity<UserDto> getUser(Long userId);
}

@Service
public class DefaultUserService implements UserService {
}
```

#### Шаг 2: Создание декоратора

Создайте декоратор, который будет логировать запросы и ответы:

```java

@Slf4j
@Service
@AllArgsConstructor
@Primary
public class LoggingDecorator implements UserService {
}
```

#### Шаг 3: Конфигурируем приложение

Теперь при использовании `UserService` в других компонентах нашего приложения, Spring автоматически подставит экземпляр
`LoggingDecorator`.

```java

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserControllerImpl {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }
}
```

Теперь все вызовы метода `getUser` будут проходить через декоратор, который выполнит логирование перед и после вызова
оригинального метода.

**Аннотация `@Primary` в Spring используется для указания первичного бина, когда в контексте приложения существует
несколько реализаций одного интерфейса или класса. Она помогает Spring выбрать конкретную реализацию, если имеется
несколько кандидатов для инъекции зависимости.
В приведенном примере аннотация `@Primary` использована в классе `LoggingDecorator`, чтобы указать, что
именно эта реализация должна использоваться в качестве основного кандидата для внедрения зависимостей типа
`UserService`. Это означает, что когда другой компонент (например, контроллер) запрашивает внедрение экземпляра
`UserService`, Spring выберет именно `LoggingDecorator`, а не оригинальную реализацию `DefaultUserService`.
Без этой аннотации Spring мог бы столкнуться с проблемой неоднозначности выбора между двумя кандидатами (
`DefaultUserService` и `LoggingDecorator`), так как оба они реализуют интерфейс `UserService`. Аннотация
`@Primary` решает эту проблему, указывая явным образом предпочтительную реализацию.

