package com.example.demo01.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo01.entity.Movie;

public interface MovieRepository extends CrudRepository<Movie,String>{
    @Query("SELECT URL FROM MOVIE where MOVIE_ID = 'M00000000001';")
    Iterable<Movie> selectMovie();
}
