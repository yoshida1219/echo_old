package com.example.demo01.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo01.entity.Response;

public interface ResponseRepository extends CrudRepository<Response,String>{

    @Query("select user.USER_NAME,RESPONSE_NAME,response.like,response.share " + 
           "from response " + 
           "inner join user on RESPONSE_CREATER = USER_ID " +
           "where RESPONSE_ID = :response_id and RESPONSE_CREATER = :user_id;")
    Iterable<Response> SelectResponse(
        @Param("response_id") String response_id,
        @Param("user_id") String user_Id
    );

    //ジャンル不問の人気動画を呼び出す
    @Query("select user.user_id,response.response_id,response.response_name, movie.url, movie.thumbnail, user.user_name, response.like " +
    "from response " +
    "inner join user on user.user_id = response.response_creater " +
    "inner join movie on movie.movie_id = response.movie_id " +
    "order by response.like desc limit 0, 5;")
    Iterable<Response> OrderPopularMovie();

    //ジャンルごとの新着スレッドを呼び出す
    @Query("with pickup_movie as ( " +

        "select * from ( " +
		"select thread.jenre_id, thread.thread_id, thread.thread_name, thread.thread_creater, response.response_creater, response.response_id, response.response_name, response.response_submit, movie.movie_id, movie.thumbnail, " +
		"row_number() over(partition by response.thread_id order by response.like desc) as 'rank',  row_number() over(partition by response.thread_id order by response.like desc) " +
		"from thread " +
		"left outer join response on response.thread_id = thread.thread_id " +
		"left outer join movie on movie.movie_id = response.movie_id " +
		"order by response.response_submit desc " +
	    ") as temp " +
	    "where temp.rank = 1 " +
        ") " +
        ", new_movie as ( " +
	    "select * from ( " +
		"select thread.jenre_id, thread.thread_id, thread.thread_submit, response.response_creater, response.response_id, response.response_name, response.response_submit, row_number() over(partition by thread.jenre_id order by thread.thread_submit desc) as 'new1' " +
		"from thread " +
		"left outer join response on response.thread_id = thread.thread_id " +
		"order by response.response_submit desc " +
	    ") as temp " +
	    "where temp.new1 <= 5 " +

        ") " +
        "select new_movie.jenre_id, new_movie.thread_id, new_movie.thread_submit, new_movie.response_creater, new_movie.response_id, new_movie.response_name, new_movie.response_submit, new_movie.new1, pickup_movie.movie_id, pickup_movie.thumbnail " +
        "from new_movie " +
        "left outer join pickup_movie on pickup_movie.thread_id = new_movie.thread_id " +
        "order by new_movie.jenre_id, new_movie.new1;")
    Iterable<Response> OrderNewThread();
}




