package com.api.user.mappers;

import com.api.user.dtos.UserDTO;
import com.api.user.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class UserMapper {

    public UserDTO getUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRole());
        return userDTO;
    }

    public User getUser(UserDTO userDTO) {
       /* User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword();*/
        return null;
    }
    public List<UserDTO> getAllUsersDTO(List<User> users) {
        List<UserDTO> list = new ArrayList<>();
        for (User u:users) {
            list.add(this.getUserDTO(u));
        }
        return list;
    }

}
