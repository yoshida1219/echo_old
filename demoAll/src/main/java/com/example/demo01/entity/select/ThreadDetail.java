package com.example.demo01.entity.select;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreadDetail {
    @Id
    public String thread_id;
    public String thread_name;
    public String response_name;
    public String like;
    public String share;
    public String movie_name;
    public String url;
    public String thumbnail;
    public String user_name;
    public String icon;
}
