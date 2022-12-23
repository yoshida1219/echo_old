package com.example.demo01.entity.select;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreadList {
    @Id
    private String thread_id;

    private String thread_name;

    private String url;
    
}
