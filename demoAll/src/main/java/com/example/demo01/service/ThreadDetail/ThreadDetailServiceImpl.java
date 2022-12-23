package com.example.demo01.service.ThreadDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo01.entity.select.ThreadDetail;
import com.example.demo01.repository.ThreadDetailRepository;

@Service
@Transactional
public class ThreadDetailServiceImpl implements ThreadDetailService {
    @Autowired
    ThreadDetailRepository repository;

    @Override
    public Iterable<ThreadDetail> selectThreadDetail_Popular3(String thread_id) {
        return repository.findThreadDetail_Popular3(thread_id);
    }

    @Override
    public Iterable<ThreadDetail> selectThreadDetailAll(String thread_id) {
        return repository.findThreadDetailAll(thread_id);
    }

}
