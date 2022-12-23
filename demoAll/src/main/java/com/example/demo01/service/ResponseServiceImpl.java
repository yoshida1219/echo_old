package com.example.demo01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo01.entity.Response;
import com.example.demo01.repository.ResponseRepository;

@Service
@Transactional
public class ResponseServiceImpl implements ResponseService{
    @Autowired
    ResponseRepository repository;

    @Override
    public Iterable<Response> SelectResponse(String resposne_id,String user_id){
        return repository.SelectResponse(resposne_id,user_id);
    }

    @Override
    public Iterable<Response> OrderPopular(){
        return repository.OrderPopularMovie();
    }

    @Override
    public Iterable<Response> OrderNewThread(){
        return repository.OrderNewThread();
    }
}
