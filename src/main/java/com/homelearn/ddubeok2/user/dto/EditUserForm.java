package com.homelearn.ddubeok2.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditUserForm {
    private Long id;
    private String password;
    private String name;
}
