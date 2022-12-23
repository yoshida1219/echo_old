package com.example.demo01.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo01.entity.select.ThreadList;

public interface ThreadListRepository extends CrudRepository<ThreadList, String>{
    @Query("with pickup_movie as ("
	    + " select thread.thread_id, thread.thread_name, thread.thread_submit, thread.thread_creater, response.response_creater, response.response_id, movie.url, row_number() over(partition by response.thread_id order by response.like desc) as 'rank'"
	    + " from thread"
	    + " left outer join response on response.thread_id = thread.thread_id"
	    + " left outer join movie on movie.movie_id = response.movie_id"
	    + " where thread.thread_creater = :user_id"
	    + " order by thread.thread_submit desc"
        + " )"
        + " select pickup_movie.thread_id, pickup_movie.thread_name, pickup_movie.url"
        + " from pickup_movie"
        + " where pickup_movie.rank = 1;")
    Iterable<ThreadList> findMyThread_OrderByRegist(
        @Param("user_id") String user_id
    );

    @Query("with pickup_movie as ("
	    + " select * from ("
		+ " select thread.thread_id, thread.thread_name, thread.thread_creater, response.response_creater, response.response_id, response.response_submit, movie.url,"
        + " row_number() over(partition by response.thread_id order by response.like desc) as 'rank'"
		+ " from thread"
		+ " left outer join response on response.thread_id = thread.thread_id"
		+ " left outer join movie on movie.movie_id = response.movie_id"
		+ " where thread.thread_creater = :user_id "
		+ " order by response.response_submit desc"
	    + " ) as temp where temp.rank = 1 ), " 
        + " new_movie as ("
	    + " select thread.thread_id, response.response_creater, response.response_id, response.response_name, response.response_submit, row_number() over(partition by response.thread_id order by response.response_submit desc) as 'new'"
	    + "from thread left outer join response on response.thread_id = thread.thread_id"
	    + " where thread.thread_creater = :user_id"
	    + " order by response.response_submit desc )"
        + " select pickup_movie.thread_id, pickup_movie.thread_name, pickup_movie.thread_creater, pickup_movie.response_creater, "
        + " pickup_movie.response_id, response.response_name, pickup_movie.response_submit, pickup_movie.url, new_movie.response_id, new_movie.response_name, new_movie.response_submit, new_movie.new  "
        + " from response inner join pickup_movie on pickup_movie.response_creater = response.response_creater and pickup_movie.response_id = response.response_id"
        + " inner join new_movie on pickup_movie.thread_id = new_movie.thread_id where new_movie.new = 1;")  
    Iterable<ThreadList> findMyThread_OrderByUpdate(
        @Param("user_id") String user_id
    );

    @Query("with pickup_movie as ("
	    + " select thread.thread_id, thread.thread_name, thread.thread_creater, thread.thread_submit, response.response_creater, response.response_id, response.response_name, response.response_submit, movie.url, "
	    + " row_number() over(partition by response.thread_id order by response.like desc) as 'rank'"
	    + " from thread"
	    + " left outer join response on response.thread_id = thread.thread_id"
	    + " left outer join movie on movie.movie_id = response.movie_id"
        + " )"
        + " select pickup_movie.thread_id, pickup_movie.thread_name, pickup_movie.url"
        + " from pickup_movie "
        + " inner join follow_thread on pickup_movie.thread_id = follow_thread.thread_id"
        + " where pickup_movie.rank = 1"
        + " and follow_thread.user_id = :user_id"
        + " order by follow_thread.follow_time desc;")
    Iterable<ThreadList> findFollowThread_OrderByRegist(
        @Param("user_id") String user_id
    );

    @Query("with pickup_movie as ("
	    + " select * from ("
		+ " select thread.thread_id, thread.thread_name, thread.thread_creater, response.response_creater, response.response_id, response.response_name, response.response_submit, movie.movie_id, movie.url,"
		+ " row_number() over(partition by response.thread_id order by response.like desc) as 'rank'"
		+ " from thread"
		+ " left outer join response on response.thread_id = thread.thread_id"
		+ " left outer join movie on movie.movie_id = response.movie_id"
		+ " order by response.response_submit desc"
	    + " ) as temp where temp.rank = 1)," 
        + " new_movie as ("
	    + " select * from ("
		+ " select thread.thread_id, response.response_creater, response.response_id, response.response_name, response.response_submit, row_number() over(partition by response.thread_id order by response.response_submit desc) as 'new'"
		+ " from thread"
		+ " left outer join response on response.thread_id = thread.thread_id"
		+ " order by response.response_submit desc"
	    + " ) as temp where temp.new = 1)"
        + " select thread.thread_id, thread.thread_name, thread.thread_creater, pickup_movie.response_creater, pickup_movie.response_id, pickup_movie.response_name, pickup_movie.movie_id, pickup_movie.url, new_movie.response_submit "
        + " from thread inner join follow_thread on follow_thread.thread_id = thread.thread_id"
        + " left outer join pickup_movie on pickup_movie.thread_id = thread.thread_id"
        + " left outer join new_movie on new_movie.thread_id = thread.thread_id"
        + " where follow_thread.user_id = :user_id"
        + " order by new_movie.response_submit desc;")
    Iterable<ThreadList> findFollowThread_OrderByUpdate(
        @Param("user_id") String user_id
    );

    //直す
    @Query("WITH thread_jenre as ("
        + " select thread.jenre_id, num_res.thread_id, thread_name, num_res.num_res, rank() over(partition by thread.jenre_id order by num_res.num_res desc) as 'rank', thread_num1.url"
        + " from thread"
        + " inner join ("
	    + " select response.thread_id, count(*) as 'num_res'"
	    + " from response group by response.thread_id) "
        + " num_res on num_res.thread_id = thread.thread_id "
        + " inner join ("
	    + " select * "
	    + " from ("
		+ " select response.thread_id, response.response_creater, response.response_id, movie.url, rank() over(partition by response.thread_id order by response.like desc) as 'rank'"
		+ " from response"
		+ " inner join movie on movie.movie_id = response.movie_id"
	    + " ) tem_rank"
	    + " where tem_rank.rank = 1)"
        + " thread_num1 on thread_num1.thread_id = thread.thread_id"
        + " where thread.jenre_id = :genre_id)"
        + " select thread_id, thread_name, url"
        + " from thread_jenre;")
    Iterable<ThreadList> findGenreThread_OrderByPopular(
        @Param("genre_id") String genre_id
    );

    @Query("with pickup_movie as ("
	    + " select * from ("
		+ " select thread.thread_id, thread.thread_name, thread.thread_creater, response.response_creater, response.response_id, response.response_name, response.response_submit, movie.movie_id, movie.url,"
		+ " row_number() over(partition by response.thread_id order by response.like desc) as 'rank'"
		+ " from thread left outer join response on response.thread_id = thread.thread_id"
		+ " left outer join movie on movie.movie_id = response.movie_id"
		+ " order by response.response_submit desc"
	    + " ) as temp where temp.rank = 1)," 
        + " new_movie as (select * from ("
		+ " select thread.thread_id, response.response_creater, response.response_id, response.response_name, response.response_submit, row_number() over(partition by response.thread_id order by response.response_submit desc) as 'new'"
		+ " from thread left outer join response on response.thread_id = thread.thread_id"
		+ " order by response.response_submit desc"
	    + " ) as temp where temp.new = 1)"
        + " select thread.thread_id, thread.thread_name, thread.thread_creater, pickup_movie.response_creater, pickup_movie.response_id, pickup_movie.response_name, pickup_movie.movie_id, pickup_movie.url, new_movie.response_submit"
        + " from thread left outer join pickup_movie on pickup_movie.thread_id = thread.thread_id"
        + " left outer join new_movie on new_movie.thread_id = thread.thread_id"
        + " where thread.jenre_id = :genre_id"
        + " order by new_movie.response_submit desc;")
    Iterable<ThreadList> findGenreThread_OrderByRegist(
        @Param("genre_id") String genre_id
    );
}
