package com.example.demo01.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo01.entity.Thread;

public interface ThreadRepository extends CrudRepository<Thread,String>{
    @Query("select thread.thread_name, movie.movie_name " +
           "from thread " +
           "inner join response on thread.thread_id = response.thread_id  " +
           "inner join movie on movie.movie_id = response.movie_id " +
           "where response.movie_id = 'M00000000002' " + 
           "and thread.thread_id != 'T000001'; ")
    Iterable<Thread> selectThread();

    
}
