package com.example.springsecuritytest.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //unique한 이름으로 만든다.
    @Column(unique = true)
    private String username;
    private String password;

    private String role;
}