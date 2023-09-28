package com.api.user.dtos;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String email;
    private String password;
}
