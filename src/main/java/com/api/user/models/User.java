package com.api.user.models;

import com.api.user.enums.Roles;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles role = Roles.ADMIN;




}
