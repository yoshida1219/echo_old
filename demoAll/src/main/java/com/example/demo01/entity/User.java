package com.example.demo01.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String user_id;

    private String user_name;

    private String search_name;

    private String password;

    private String mail;
}
