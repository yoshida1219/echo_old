package com.example.demo01.service;

import java.lang.Iterable;

import com.example.demo01.entity.select.SelectFollowerMovie;

public interface HomeService {
    Iterable<SelectFollowerMovie> selectFollowerMovie();
}
