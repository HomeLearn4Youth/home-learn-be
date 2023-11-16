package com.homelearn.back.user;


import com.homelearn.back.user.dto.AddUserForm;
import com.homelearn.back.user.dto.EditUserForm;
import com.homelearn.back.user.dto.LoginForm;
import com.homelearn.back.user.entity.User;

import java.util.List;

public interface UserService {
    //Create
    void addUser(AddUserForm addForm);
    //Read
    User findByIdUser(Long id);
    List<User> findByAllUsers();

    User login(LoginForm loginForm);
    //Update
    void editUser(EditUserForm editForm);

    //Delete
    void deleteUser(LoginForm deleteForm);
}
