package com.homelearn.back.user;


import com.homelearn.back.oauth.dto.OAuthDto;
import com.homelearn.back.user.dto.AddUserForm;
import com.homelearn.back.user.dto.EditUserForm;
import com.homelearn.back.user.dto.LoginForm;
import com.homelearn.back.user.entity.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;

public interface UserService {
    //Create
    Long addUser(AddUserForm addForm);
    //Read
    User findByIdUser(Long id);
    User findByEmail(String email);

    List<User> findByAllUsers();


    User login(LoginForm loginForm);
    //Update
    void editUser(EditUserForm editForm);

    //Delete
    void deleteUser(LoginForm deleteForm);

    //OAuth
    User findOrCreate(OAuthDto oAuthDto);
}
