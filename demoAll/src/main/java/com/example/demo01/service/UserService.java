package com.example.demo01.service;

import java.util.Optional;

import com.example.demo01.entity.User;

public interface UserService {
    Optional<User> selectUserOne(String serch_name);

    void insertUser(User user);

    String selectUserIdMax();

    Optional<String> selectMail(String mail);

    Optional<String> selectSearchName(String search_name);
}
