Паттерн **Фасад** (Facade) позволяет упростить взаимодействие между клиентом и сложной системой, предоставляя единый
интерфейс для работы с несколькими подсистемами. В контексте Spring Boot этот паттерн может использоваться для упрощения
доступа к различным сервисам приложения через единую точку входа.

Рассмотрим пример реализации паттерна Фасад на основе Spring Boot:

### 1. Сервисы

Предположим, у нас есть два сервиса: `UserService` и `OrderService`, каждый из которых предоставляет свой набор методов.

```java

@Service
public class UserService {

    public String getUserName(long userId) {
        return "John Doe";
    }
}
```

```java

@Service
public class OrderService {

    public List<String> getOrdersForUser(long userId) {
        return Arrays.asList("Order 1", "Order 2");
    }
}
```

### 2. Фасад

Теперь создадим класс `UserFacade`, который будет служить единой точкой входа для взаимодействия с этими сервисами.

```java

@Service
public class UserFacade {

    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public UserFacade(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    public String getUserDetails(long userId) {
        String userName = userService.getUserName(userId);
        List<String> orders = orderService.getOrdersForUser(userId);

        return String.format("User %s has the following orders: %s", userName, orders);
    }
}
```

### 3. Контроллер

Создадим контроллер, который будет использовать фасады для обработки запросов.

```java
@RestController
@RequestMapping("/users-facade")
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
```

### Объяснение

1. **Сервисы**: `UserService` и `OrderService` представляют собой отдельные компоненты системы, которые выполняют свои
   функции.

2. **Фасад**: Класс `UserFacade` объединяет функциональность обоих сервисов и предоставляет метод `getUserDetails()`,
   который возвращает подробную информацию о пользователе, включая его имя и список заказов.

3. **Контроллер**: `UserController` использует фасады для получения информации о пользователе и возврата её клиенту.

Таким образом, клиент взаимодействует только с одним методом контроллера, а вся логика работы с различными сервисами
скрыта за фасадом.