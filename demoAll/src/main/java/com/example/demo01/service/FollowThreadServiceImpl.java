package com.example.demo01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo01.entity.select.FollowThread;
import com.example.demo01.repository.FollowThreadRepository;

@Service
@Transactional
public class FollowThreadServiceImpl implements FollowThreadService{
    @Autowired
    FollowThreadRepository repository;

    @Override
    public Iterable<FollowThread> OrderFollowThread(){
        return repository.OrderFollowThread();
    }
    
}
