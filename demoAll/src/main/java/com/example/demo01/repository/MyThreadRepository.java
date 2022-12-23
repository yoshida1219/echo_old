package com.example.demo01.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo01.entity.select.MyThread;

public interface MyThreadRepository extends CrudRepository<MyThread,String>{
    //SQL_TEST㉜を使用
    //自分作成したスレッドを呼び出す
    @Query("select thread.thread_id, thread.thread_name, response.response_submit, movie.url, movie.thumbnail " +
    " from thread" +
    " inner join response on response.thread_id = thread.thread_id" +
    " inner join movie on movie.movie_id = response.movie_id" +
    " where thread.thread_creater = 'U00000002'" +
    " order by response.response_submit desc limit 0, 5;")
    Iterable<MyThread> OrderMyThread();
}