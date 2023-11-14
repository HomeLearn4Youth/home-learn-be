package com.homelearn.back.user.entity;

import com.homelearn.back.notice.entity.Notice;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private Long id;
    private String password;
    private String email;
    private String name;
    private Boolean admin;
}
