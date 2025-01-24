package my.example.springpatternsmy.facade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
