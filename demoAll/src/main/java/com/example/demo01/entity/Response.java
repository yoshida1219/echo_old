package com.example.demo01.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    @Id
    private String response_id;

    private String user_id;

    private String response_name;

    private String response_creater;

    private String user_name;

    private String thread_id;

    private String thread_name;

    private String movie_id;

    private String thumbnail;

    private String like;

    private String share;

    private String url;

    private Integer count;

    private String jenre_name;

}
