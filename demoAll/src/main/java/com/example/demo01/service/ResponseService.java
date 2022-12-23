package com.example.demo01.service;

import com.example.demo01.entity.Response;

public interface ResponseService {
    Iterable<Response> SelectResponse(String response_id,String user_id);

    Iterable<Response> OrderPopular();

    Iterable<Response> OrderNewThread();
}
