package com.example.demo01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo01.entity.select.PopularThread;
import com.example.demo01.repository.PopularThreadRepository;

@Service
@Transactional
public class PopularThreadServiceImpl implements PopularThreadService{
    @Autowired
    PopularThreadRepository repository;
    
    @Override
    public Iterable<PopularThread> OrderPopularThread(){
        return repository.OrderPopularThread();
    }
}
