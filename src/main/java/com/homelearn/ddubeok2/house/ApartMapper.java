package com.homelearn.ddubeok2.house;

import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;

@Mapper
public class ApartMapper {
    private Long id;
    private String title;
    private String content;
    private int view;
    private Timestamp createdAt;
    private Long userId; //사용자 fk
}
