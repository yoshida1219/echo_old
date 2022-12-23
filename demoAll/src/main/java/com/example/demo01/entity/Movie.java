package com.example.demo01.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    private String movie_id;

    private String movie_name;

    private String url;

    private String thumbnail;
}
