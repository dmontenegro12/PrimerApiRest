package com.api.user.controllers;

import com.api.user.dtos.UserDTO;
import com.api.user.dtos.UserLoginDTO;
import com.api.user.mappers.UserMapper;
import com.api.user.models.User;
import com.api.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/get-all")
    public List<UserDTO> getAll(){
        return userService.getAll();
    }
    @PostMapping
    public UserDTO save(@RequestBody User user){
        return userService.saveUser(user);
    }
    @PostMapping("/log-in")
    public String logIn(@RequestBody UserLoginDTO userLoginDTO){
        return userService.logIn(userLoginDTO);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        userService.delete(id);
    }

}
