package com.example.demo01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo01.entity.Movie;
import com.example.demo01.repository.MovieRepository;

@Service
@Transactional
public class MovieServiceImpl implements MovieService{
    @Autowired
    MovieRepository repository;

    @Override
    public Iterable<Movie> SelectMovie(){
        return repository.selectMovie();
    }
}
