package com.api.user.services;

import com.api.user.dtos.UserDTO;
import com.api.user.dtos.UserLoginDTO;
import com.api.user.mappers.UserMapper;
import com.api.user.models.User;
import com.api.user.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService  implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserMapper userMapper;

    public UserDTO saveUser(User user){
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return userMapper.getUserDTO(user);
    }
    public Optional<UserDTO> getUserbyId(Long id){
        return Optional.ofNullable(userMapper.getUserDTO(userRepo.getOne(id)));
    }
    public List<UserDTO> getAll(){
        return userMapper.getAllUsersDTO(userRepo.findAll());
    }
    public void delete(Long id){
        userRepo.deleteById(id);
    }
    public String logIn(UserLoginDTO userLogInDTO){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Optional<User> userdb= Optional.ofNullable(userRepo.findByEmail(userLogInDTO.getEmail()));
        if(userdb.isPresent()){
            User user = userdb.get();
            if (bCryptPasswordEncoder.matches(userLogInDTO.getPassword(),user.getPassword())){
                return "Sesion iniciada Correctamente.";
            }else{
                return "Contrasena Incorrecta.";
            }
        }else{
            return "El usuario no se encuentra dentro de nuestros datos de email.";
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepo.findByEmail(email);
        if (user != null){
            List<GrantedAuthority> permissions = new ArrayList<>();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + user.getRole().toString());
            permissions.add(p);
            return new org.springframework.security.core.userdetails.User(email,user.getPassword(),permissions);
        }else {
            return null;
        }
    }
   /* @Override
    public UserDetails loadUserByUsername(String email){
        User user = userRepo.findByEmail(email);
        if (user != null){
            List<GrantedAuthority> permissions = new ArrayList<>();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + user.getRole().toString());
            permissions.add(p);
            return new org.springframework.security.core.userdetails.User(email,user.getPassword(),permissions);
        }else {
            return null;
        }

    }*/


}
