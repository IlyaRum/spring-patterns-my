package my.example.springpatternsmy.facade.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUserName(long userId) {
        return "John Doe";
    }
}

