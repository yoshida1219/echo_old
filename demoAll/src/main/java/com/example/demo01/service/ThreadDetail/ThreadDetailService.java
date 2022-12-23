package com.example.demo01.service.ThreadDetail;

import com.example.demo01.entity.select.ThreadDetail;

public interface ThreadDetailService {
    
    Iterable<ThreadDetail> selectThreadDetail_Popular3(String thread_id);

    Iterable<ThreadDetail> selectThreadDetailAll(String thread_id);

}