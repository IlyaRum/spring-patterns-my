package my.example.springpatternsmy.decorator.config;

import my.example.springpatternsmy.decorator.rest.UserController;
import my.example.springpatternsmy.decorator.service.LoggingDecorator;
import my.example.springpatternsmy.decorator.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserController userController(UserServiceImpl userServiceImpl) {
        return new LoggingDecorator(userServiceImpl);
    }
}
