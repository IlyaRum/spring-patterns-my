package my.example.springpatternsmy.facade.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    public List<String> getOrdersForUser(long userId) {
        return Arrays.asList("Order 1", "Order 2");
    }
}
