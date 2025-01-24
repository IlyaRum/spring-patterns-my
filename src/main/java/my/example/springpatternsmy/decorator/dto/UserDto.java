package my.example.springpatternsmy.decorator.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String username;

    public UserDto(Long id, String name) {
        this.id = id;
        this.username = name;
    }
}
