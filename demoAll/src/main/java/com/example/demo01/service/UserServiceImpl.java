package com.example.demo01.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo01.entity.User;
import com.example.demo01.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository repository;

    @Override
    public Optional<User> selectUserOne(String serch_name) {
        return repository.findBySerchName(serch_name);
    }

    @Override
    public void insertUser(User user) {
        repository.insertUser(
            user.getUser_id(),
            user.getUser_name(),
            user.getPassword(),
            user.getMail(),
            user.getSearch_name()
        );
    }

    @Override
    public String selectUserIdMax() {
        return repository.findUserIdMax();
    }

    @Override
    public Optional<String> selectMail(String mail) {
        return repository.findMail(mail);
    }

    @Override
    public Optional<String> selectSearchName(String search_name) {
        return repository.findSearchName(search_name);
    }
}
