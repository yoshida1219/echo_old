package com.example.demo01.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    private String comment_id;

    private String user_name;

    private String comment;

    private Data submit_time;

}
