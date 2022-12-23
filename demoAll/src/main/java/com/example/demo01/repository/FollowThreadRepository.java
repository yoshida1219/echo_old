package com.example.demo01.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.demo01.entity.select.FollowThread;

public interface FollowThreadRepository extends CrudRepository<FollowThreadRepository,String>{
    //SQL_TEST㉙を使用
    //フォローしているスレッドを呼び出す
    @Query("select thread.thread_id, thread.thread_name, response.response_submit, movie.url, movie.thumbnail " +
    "from follow_thread " +
    "inner join thread on thread.thread_id = follow_thread.thread_id " +
    "inner join response on response.thread_id = thread.thread_id " +
    "inner join movie on movie.movie_id = response.movie_id " +
    "where follow_thread.user_id = 'U00000002' " +
    "order by response.response_submit desc limit 0, 5;")
    Iterable<FollowThread> OrderFollowThread();
}