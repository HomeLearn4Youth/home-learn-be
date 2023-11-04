package com.homelearn.ddubeok2.user;

import com.homelearn.ddubeok2.user.dto.AddUserForm;
import com.homelearn.ddubeok2.user.dto.EditUserForm;
import com.homelearn.ddubeok2.user.dto.LoginForm;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface UserMapper {
    //Create
    void addUser(AddUserForm addForm);
    //Read
    User findByIdUser(Long id);
    List<User> findByAllUsers() ;
    User login(LoginForm loginForm);
    //Update
    void editUser(EditUserForm editForm);
    //Delete
    void deleteUser(LoginForm deleteForm);
    User findPassword(AddUserForm findPasswordForm);

}
