package com.example.demo01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo01.entity.Comment;
import com.example.demo01.repository.CommentRepository;

@Service
@Transactional
public class CommentServicelmpl implements CommentService{
    @Autowired
    CommentRepository repository;
    
    @Override
    public Iterable<Comment>  SelectComment(String user_id,String response_id){
        return repository.SelectComment(user_id,response_id);
    }

}
