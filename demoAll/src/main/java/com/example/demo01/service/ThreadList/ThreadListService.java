package com.example.demo01.service.ThreadList;

import com.example.demo01.entity.select.ThreadList;

public interface ThreadListService {
    
    Iterable<ThreadList> selectMyThread_OrderByRegist(String user_id);

    Iterable<ThreadList> selectMyThread_OrderByUpdate(String user_id);

    Iterable<ThreadList> selectFollowThread_OrderByRegist(String user_id);

    Iterable<ThreadList> selectFollowThread_OrderByUpdate(String user_id);

    Iterable<ThreadList> selectGenreThread_OrderByPopular(String genre_id);

    Iterable<ThreadList> selectGenreThread_OrderByRegist(String genre_id);

}
