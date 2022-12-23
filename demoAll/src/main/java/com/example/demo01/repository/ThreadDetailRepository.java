package com.example.demo01.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo01.entity.select.ThreadDetail;

public interface ThreadDetailRepository extends CrudRepository<ThreadDetail,String>{
    //投稿　いいね順で３つ
    @Query("select thread.thread_id, thread.thread_name, response.response_name, response.like, response.share, movie.movie_name, movie.url, movie.thumbnail,user.user_name, user.icon"
        + " from thread, response, movie, user"
        + " where thread.thread_id = response.thread_id"
        + " and response.movie_id = movie.movie_id"
        + " and response.response_creater = user.user_id"
        + " and thread.thread_id = :thread_id"
        + " order by response.like desc limit 3;")
    Iterable<ThreadDetail> findThreadDetail_Popular3(
        @Param("thread_id") String thread_id
    );

    //投稿　すべて　投稿の日付の降順
    @Query("select thread.thread_id, thread.thread_name, response.response_name, response.like, response.share, movie.movie_name, movie.url, movie.thumbnail, user.user_name, user.icon"
        + " from thread, response, movie, user"
        + " where thread.thread_id = response.thread_id"
        + " and response.movie_id = movie.movie_id"
        + " and response.response_creater = user.user_id"
        + " and thread.thread_id = :thread_id"
        + " order by response.response_submit desc;")
    Iterable<ThreadDetail> findThreadDetailAll(
        @Param("thread_id") String thread_id
    );
}