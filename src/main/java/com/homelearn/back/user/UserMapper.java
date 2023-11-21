package com.homelearn.back.user;

import com.homelearn.back.user.dto.AddUserForm;
import com.homelearn.back.user.dto.EditUserForm;
import com.homelearn.back.user.dto.LoginForm;
import com.homelearn.back.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    //Create
    Long addUser(User user);
    //Read
    User findByIdUser(Long id);
    User findByEmail(String email);
    List<User> findByAllUsers() ;
    User login(LoginForm loginForm);
    //Update
    void editUser(EditUserForm editForm);
    //Delete
    void deleteUser(LoginForm deleteForm);
    User findPassword(AddUserForm findPasswordForm);

}
