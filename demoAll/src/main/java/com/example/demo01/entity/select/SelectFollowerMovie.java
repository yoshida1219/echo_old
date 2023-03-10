package com.example.demo01.entity.select;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectFollowerMovie {
    @Id
    private String my_name;

    private String follower_name;

    private String response_name;

    private String movie_name;

    private String url;

    private String thread_name;

    private String thumbnail;

    private Integer like;

    private Integer share;
    
}
