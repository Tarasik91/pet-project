package com.example.petproject.dto;

import com.example.petproject.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String username;

    public static UserDto toDto(User user){
        UserDto dto = new UserDto();
        dto.setFirstName(user.getFirstName());
        dto.setUsername(user.getUsername());
        dto.setLastName(user.getLastName());
        return dto;
    }
}
