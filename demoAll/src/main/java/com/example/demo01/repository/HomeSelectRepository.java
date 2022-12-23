package com.example.demo01.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo01.entity.select.SelectFollowerMovie;

public interface HomeSelectRepository extends CrudRepository<SelectFollowerMovie, String>{
    @Query("select user1.user_name as my_name, user2.user_name as follower_name, response.response_name, movie.movie_name, movie.url, movie.thumbnail, thread.thread_name, response.like, response.share from follow"
        + " inner join user as user1 on user1.user_id = follow.user_id "
        + " inner join user as user2 on user2.user_id = follow.followuser_id "
        + " inner join response on response.response_creater = user2.user_id "
        + " inner join thread on response.thread_id = thread.thread_id "
        + " inner join movie on movie.movie_id = response.movie_id "
        + " where user1.user_id = 'U00000002';")
    Iterable<SelectFollowerMovie> findFollowerMovies();
}
