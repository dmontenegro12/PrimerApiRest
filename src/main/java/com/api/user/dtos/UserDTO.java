package com.api.user.dtos;


import com.api.user.enums.Roles;
import com.api.user.models.User;
import com.api.user.services.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Roles roles = Roles.ADMIN;

}
