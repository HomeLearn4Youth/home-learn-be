package com.homelearn.ddubeok2.user;

import com.homelearn.ddubeok2.user.dto.AddUserForm;
import com.homelearn.ddubeok2.user.dto.LoginForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import static org.junit.Assert.*;
@SpringBootTest
public class UserServiceTest {


    private final UserService service;

    @Autowired
    public UserServiceTest(UserService service) {
        this.service = service;
    }

//    @Test
//    public void 회원가입_테스트() throws Exception{
//        //given
//        AddUserForm addUserForm = new AddUserForm();
//        addUserForm.setEmail("test@test.com");
//        addUserForm.setName("테스트");
//        addUserForm.setPassword("P@ssw0rd");
//        //when
//        service.addUser(addUserForm);
//        //then
//    }

//    @Test
//    public void 회원조회_테스트() throws Exception{
//        //given
//        Long id = 3L;
//        //when
//        User user = service.findByIdUser(id);
//        //then
//        System.out.println("user = " + user);
//    }
    
    @Test
    public void 로그인_테스트() throws Exception{
        //given
        LoginForm loginForm = new LoginForm();
        loginForm.setEmail("test@test.com");
        loginForm.setPassword("P@ssw0rd");
        //when
        User loginUser = service.login(loginForm);
        String 로그인유저이름 = "테스트";
        //then
        Assertions.assertThat(loginUser.getName()).isEqualTo(로그인유저이름);
    }
}