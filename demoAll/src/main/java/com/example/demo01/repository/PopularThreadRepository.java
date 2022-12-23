package com.example.demo01.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo01.entity.select.PopularThread;

public interface PopularThreadRepository extends CrudRepository<PopularThread,String>{
    
    //SQL_TEST㉘を使用
    //ジャンルごとの人気のスレッドを呼び出す
    @Query("select * from (" +
        "select thread.jenre_id, jenre.jenre_name, num_res.thread_id, thread.thread_name, num_res.num_res, row_number() over(partition by thread.jenre_id order by num_res.num_res desc) as 'rank', thread_num1.url, thread_num1.thumbnail " +
        "from thread " +
        "inner join (" +
        "select response.thread_id, count(*) as 'num_res' " +
        "from response group by response.thread_id " +
        ") num_res on num_res.thread_id = thread.thread_id " + 
        "inner join (" +
        "select * from (" +
        "select response.thread_id, response.response_creater, response.response_id, movie.url, movie.thumbnail, row_number() over(partition by response.thread_id order by response.like desc) as 'rank' " +
        "from response " +
        "inner join movie on movie.movie_id = response.movie_id " +
        ") tem_rank where tem_rank.rank = 1) " + 
        "thread_num1 on thread_num1.thread_id = thread.thread_id " + 
        "inner join jenre on jenre.jenre_id = thread.jenre_id) output " + 
        "where output.rank <= 5  and jenre_id = 'J0002' " + 
        "order by output.jenre_id;")
    Iterable<PopularThread> OrderPopularThread();
}
