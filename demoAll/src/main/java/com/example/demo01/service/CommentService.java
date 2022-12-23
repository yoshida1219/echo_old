package com.example.demo01.service;

import com.example.demo01.entity.Comment;

public interface CommentService {

    Iterable<Comment> SelectComment(String user_id,String response_id);     

}
