package com.example.demo01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo01.entity.select.MyThread;
import com.example.demo01.repository.MyThreadRepository;

@Service
@Transactional
public class MyThreadServiceImpl implements MyThreadService{
    @Autowired
    MyThreadRepository repository;

    @Override
    public Iterable<MyThread> OrderMyThread(){
        return repository.OrderMyThread();
    }
}
