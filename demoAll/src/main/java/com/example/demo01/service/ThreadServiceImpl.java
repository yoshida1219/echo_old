package com.example.demo01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo01.entity.Thread;
import com.example.demo01.repository.ThreadRepository;

@Service
@Transactional
public class ThreadServiceImpl implements ThreadService{
    @Autowired
    ThreadRepository repository;

    @Override
    public Iterable<Thread> selectThread(){
        return repository.selectThread();
    }

    
}
