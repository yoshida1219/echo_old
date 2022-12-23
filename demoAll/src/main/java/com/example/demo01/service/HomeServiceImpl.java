package com.example.demo01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo01.entity.select.SelectFollowerMovie;
import com.example.demo01.repository.HomeSelectRepository;

@Service
@Transactional
public class HomeServiceImpl implements HomeService {
    @Autowired
    HomeSelectRepository repository;

    @Override
    public Iterable<SelectFollowerMovie> selectFollowerMovie() {
        return repository.findFollowerMovies();
    }
}
