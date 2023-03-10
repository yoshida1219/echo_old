package com.example.demo01.entity.select;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PopularThread {
    @Id
    private String jenre_id;

    private String jenre_name;

    private String thread_id;

    private String thread_name;

    private String num_res;

    private String rank;

    private String url;

    private String thumbnail;
}
