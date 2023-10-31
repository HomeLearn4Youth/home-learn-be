package com.homelearn.ddubeok2.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserForm {
    private String password;
    private String email;
    private String name;
}
